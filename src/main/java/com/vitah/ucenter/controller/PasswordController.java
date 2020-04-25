package com.vitah.ucenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.ucenter.cache.EmailCache;
import com.vitah.ucenter.constant.CodeEnum;
import com.vitah.ucenter.entity.User;
import com.vitah.ucenter.entity.UserByAccount;
import com.vitah.ucenter.exception.BusinessException;
import com.vitah.ucenter.repository.UserByAccountRepository;
import com.vitah.ucenter.security.IAuthenticationFacade;
import com.vitah.ucenter.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vitah
 */
@RestController
public class PasswordController {

    @Autowired
    private UserByAccountRepository userByAccountRepository;

    @Autowired
    private EmailCache emailCache;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    /**
     * 重置密码
     *
     * @param appId
     * @param email
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = "/user/password/reset", method = RequestMethod.PUT)
    public ResponseEntity<Object> reset(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "code") String code
    ) {
        if (!emailCache.getCode(EmailCache.RESET_PASSWORD, appId, email).equals(code)) {
            throw new BusinessException(CodeEnum.VERIFY_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }

        UserByAccount userByAccount = userByAccountRepository.findByAppIdAndEmail(appId, email);
        if (userByAccount == null) {
            throw new BusinessException(CodeEnum.EMAIL_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        String secretPassword = PasswordUtil.secret(password);
        userByAccount.setPassword(secretPassword);
        userByAccountRepository.save(userByAccount);

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }

    /**
     * 密码修改
     *
     * @param passwordNew
     * @param passwordOld
     * @return
     */
    @RequestMapping(value = "/user/password/modify", method = RequestMethod.PUT)
    public ResponseEntity<Object> modify(
        @RequestParam(value = "password_new") String passwordNew,
        @RequestParam(value = "password_old") String passwordOld
    ) {
        User me = authenticationFacade.currentUser();
        UserByAccount userByAccount = userByAccountRepository.findByUserId(me.getId());

        String oldPassword = PasswordUtil.secret(passwordOld);
        if (oldPassword != passwordOld) {
            throw new BusinessException(CodeEnum.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
        }

        userByAccount.setPassword(PasswordUtil.secret(passwordNew));
        userByAccountRepository.save(userByAccount);

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
