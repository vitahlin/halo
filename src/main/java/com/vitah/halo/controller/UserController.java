package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.entity.UserByAccount;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.UserByAccountRepository;
import com.vitah.halo.service.UserService;
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
public class UserController {

    @Autowired
    private UserService userService;

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
        @RequestHeader(value = "X-Device-ID") String deviceId,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "code") String code
    ) {
        String token = userService.newUser(appId, platform, email, password);

        JSONObject obj = new JSONObject();
        obj.put("token", token);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * 重置密码
     *
     * @param appId
     * @param platform
     * @param deviceId
     * @param email
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = "/user/password/reset", method = RequestMethod.PUT)
    public ResponseEntity<Object> resetPassword(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password,
        @RequestParam(value = "code") String code
    ) {
        UserByAccount userByAccount = userByAccountRepository.findByEmail(email);
        if (userByAccount == null) {
            throw new BusinessException(CodeEnum.EMAIL_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        String secretPassword = PasswordUtil.secret(password);
        userByAccount.setPassword(secretPassword);
        userByAccountRepository.save(userByAccount);

        return new ResponseEntity<>(new JSONObject(), HttpStatus.OK);
    }
}
