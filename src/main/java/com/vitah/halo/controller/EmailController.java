package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.UserByAccountRepository;
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

    @RequestMapping(value = "/email/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId,
        @RequestParam(value = "email") String email
    ) {
        // 判断邮箱是否已经存在
        if (!userByAccountRepository.existsByEmail(email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        // Redis写入验证码

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }

    @RequestMapping(value = "/email/reset_password", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId,
        @RequestParam(value = "email") String email
    ) {
        // 判断邮箱是否已经存在
        if (!userByAccountRepository.existsByEmail(email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        // Redis写入验证码

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
