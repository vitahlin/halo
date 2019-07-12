package com.vitah.halo.service;

import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.entity.App;
import com.vitah.halo.entity.User;
import com.vitah.halo.entity.UserByAccount;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.AppRepository;
import com.vitah.halo.repository.UserByAccountRepository;
import com.vitah.halo.repository.UserRepository;
import com.vitah.halo.util.JWTUtil;
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

    @Autowired
    private AppRepository appRepository;

    /**
     * 生成新的注册用户
     *
     * @param appId    应用ID
     * @param platform 平台（IOS、Android）
     * @param email    用户邮箱
     * @param password 密码
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public String newUser(Integer appId, Integer platform, String email, String password) {
        if (userByAccountRepository.existsByEmail(email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        // 判断app key是否存在
        App app = appRepository.findById(appId).get();
        if (app == null) {
            throw new BusinessException(CodeEnum.APP_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        password = PasswordUtil.secret(password);

        User user = new User();
        user.setAppId(appId);
        user.setPlatform(platform);
        user.setEmail(email);
        userRepository.saveAndFlush(user);

        UserByAccount userByAccount = new UserByAccount();
        userByAccount.setAppId(appId);
        userByAccount.setEmail(email);
        userByAccount.setPassword(password);
        userByAccount.setUserId(user.getId());
        userByAccountRepository.saveAndFlush(userByAccount);

        return JWTUtil.genToken(app.getAppKey(), user.getId());
    }
}
