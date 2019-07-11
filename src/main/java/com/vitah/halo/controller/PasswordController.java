package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.halo.cache.EmailCache;
import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.entity.UserByAccount;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.UserByAccountRepository;
import com.vitah.halo.util.PasswordUtil;
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
     * @param appId
     * @param platform
     * @param deviceId
     * @param passwordNew
     * @param passwordOld
     * @return
     */
    @RequestMapping(value = "/user/password/modify", method = RequestMethod.PUT)
    public ResponseEntity<Object> modify(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId,
        @RequestParam(value = "password_new") String passwordNew,
        @RequestParam(value = "password_old") String passwordOld
    ) {
        // Todo: userId改为真实读取
        Integer userId = 1;
        UserByAccount userByAccount = userByAccountRepository.findByUserId(userId);

        String oldPassword = PasswordUtil.secret(passwordOld);

        if (oldPassword != passwordOld) {
            throw new BusinessException(CodeEnum.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
        }

        userByAccount.setPassword(PasswordUtil.secret(passwordNew));
        userByAccountRepository.save(userByAccount);

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
