package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vitah
 */
@RestController
public class CheckController {
    @RequestMapping(value = "/auth/check", method = RequestMethod.POST)
    public ResponseEntity<Object> resetPassword(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId
    ) {
        // Todo: 加入JWT验证直接返回用户数据
        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
