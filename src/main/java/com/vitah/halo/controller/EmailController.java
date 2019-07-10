package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.UserByAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * 邮箱用户注册验证
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/email/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(
        @RequestParam(value = "email") String email
    ) {
        if (userByAccountRepository.existsByEmail(email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        // Todo: Redis写入验证码并且发送验证码邮件

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }

    /**
     * 重置密码验证
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/email/reset_password", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(
        @RequestParam(value = "email") String email
    ) {
        if (!userByAccountRepository.existsByEmail(email)) {
            throw new BusinessException(CodeEnum.EMAIL_IS_EXIST, HttpStatus.BAD_REQUEST);
        }

        // Todo: Redis写入验证码并且发送验证码邮件

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
