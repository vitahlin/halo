package com.vitah.halo.service;

import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.entity.User;
import com.vitah.halo.entity.UserByAccount;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.UserByAccountRepository;
import com.vitah.halo.repository.UserRepository;
import com.vitah.halo.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author vitah
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserByAccountRepository userByAccountRepository;

    @Transactional(rollbackOn = Exception.class)
    public void newUser(Integer appId, Integer platform, String email, String password) {
        if (userByAccountRepository.existsByEmail(email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        password = PasswordUtil.gen(password);

        User user = User.builder().appId(appId).platform(platform).email(email).build();

        System.out.println(user.toString());
        userRepository.saveAndFlush(user);

        UserByAccount userByAccount = new UserByAccount();
        userByAccount.setAppId(appId);
        userByAccount.setEmail(email);
        userByAccount.setPassword(password);
        userByAccount.setUserId(user.getId());
        userByAccountRepository.saveAndFlush(userByAccount);
    }
}
