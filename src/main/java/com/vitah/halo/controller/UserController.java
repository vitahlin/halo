package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vitah
 */
@RestController
public class UserController {

    /**
     * 新用户注册
     * @return
     */
    @RequestMapping(value = "/user/signup",method = RequestMethod.POST)
    public ResponseEntity<Object> signUp() {
        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
