package com.vitah.ucenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.ucenter.cache.EmailCache;
import com.vitah.ucenter.constant.CodeEnum;
import com.vitah.ucenter.exception.BusinessException;
import com.vitah.ucenter.repository.AppRepository;
import com.vitah.ucenter.repository.UserByAccountRepository;
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
public class EmailController {

    @Autowired
    private UserByAccountRepository userByAccountRepository;

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private EmailCache emailCache;

    /**
     * 邮箱用户注册的时候，向邮箱发送验证码
     *
     * @param appId
     * @param email
     * @return
     */
    @RequestMapping(value = "/email/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestParam(value = "email") String email
    ) {
        if (!appRepository.existsById(appId)) {
            throw new BusinessException(CodeEnum.APP_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        if (userByAccountRepository.existsByAppIdAndEmail(appId, email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        emailCache.setCode(EmailCache.SIGN_UP, appId, email);

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }

    /**
     * 重置密码验证
     *
     * @param appId
     * @param email
     * @return
     */
    @RequestMapping(value = "/email/reset_password", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestParam(value = "email") String email
    ) {
        if (!userByAccountRepository.existsByAppIdAndEmail(appId, email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        emailCache.setCode(EmailCache.RESET_PASSWORD, appId, email);
        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
