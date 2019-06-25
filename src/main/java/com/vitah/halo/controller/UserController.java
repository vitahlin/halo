package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
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
public class UserController {

    @Autowired
    private UserByAccountRepository userByAccountRepository;

    /**
     * 新用户注册
     *
     * @return
     */
    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") Integer devideId,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "code") String code
    ) {
        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
