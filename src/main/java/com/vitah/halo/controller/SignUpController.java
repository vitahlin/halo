package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.halo.cache.EmailCache;
import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.service.UserService;
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
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailCache emailCache;

    /**
     * 用户邮箱注册
     *
     * @return
     */
    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "code") String code
    ) {
        String cacheCode = emailCache.getCode(EmailCache.SIGN_UP, appId, email);
        if (!cacheCode.equals(code)) {
            throw new BusinessException(CodeEnum.VERIFY_CODE_ERROR, HttpStatus.BAD_REQUEST);
        }

        String token = userService.newUser(appId, platform, email, password);
        JSONObject obj = new JSONObject();
        obj.put("token", token);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
