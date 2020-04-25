package com.vitah.ucenter.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.ucenter.constant.CodeEnum;
import com.vitah.ucenter.entity.App;
import com.vitah.ucenter.entity.User;
import com.vitah.ucenter.entity.UserByAccount;
import com.vitah.ucenter.entity.UserByAnonymous;
import com.vitah.ucenter.exception.BusinessException;
import com.vitah.ucenter.repository.AppRepository;
import com.vitah.ucenter.repository.UserByAccountRepository;
import com.vitah.ucenter.repository.UserByAnonymousRepository;
import com.vitah.ucenter.repository.UserRepository;
import com.vitah.ucenter.util.JWTUtil;
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
public class SignInController {

    @Autowired
    private UserByAnonymousRepository userByAnonymousRepository;

    @Autowired
    private UserByAccountRepository userByAccountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppRepository appRepository;

    /**
     * 匿名用户登录接口
     *
     * @param appId
     * @param platform
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/user/signin/anonymous", method = RequestMethod.POST)
    public ResponseEntity<Object> anonymous(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId
    ) {
        App app = appRepository.findById(appId).orElse(null);
        if (app == null) {
            throw new BusinessException(CodeEnum.APP_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        UserByAnonymous userByAnonymous = userByAnonymousRepository.findByAppIdAndDeviceId(appId, deviceId);
        User user;
        if (userByAnonymous != null) {
            user = userRepository.findById(userByAnonymous.getUserId()).orElse(null);
        } else {
            user = new User();
            user.setAppId(appId);
            user.setPlatform(platform);
            user.setAnonymous(1);
            userRepository.saveAndFlush(user);

            userByAnonymous = new UserByAnonymous();
            userByAnonymous.setAppId(appId);
            userByAnonymous.setDeviceId(deviceId);
            userByAnonymous.setUserId(user.getId());
            userByAnonymousRepository.save(userByAnonymous);
        }

        if (user == null) {
            throw new BusinessException(CodeEnum.USER_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        String token = JWTUtil.genToken(app.getAppKey(), user.getId());

        JSONObject obj = new JSONObject();
        obj.put("token", token);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * 注册用户登录
     *
     * @param appId
     * @param platform
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/user/signin/account", method = RequestMethod.POST)
    public ResponseEntity<Object> account(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId,
        @RequestParam(value = "email") String email,
        @RequestParam(value = "password") String password
    ) {
        App app = appRepository.findById(appId).orElse(null);
        if (app == null) {
            throw new BusinessException(CodeEnum.APP_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        UserByAccount userByAccount = userByAccountRepository.findByAppIdAndEmail(appId, email);
        if (userByAccount == null) {
            throw new BusinessException(CodeEnum.USER_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findById(userByAccount.getUserId()).orElse(null);
        if (user == null) {
            throw new BusinessException(CodeEnum.USER_NOT_EXIST, HttpStatus.BAD_REQUEST);
        }

        if (userByAccount.getPassword() != PasswordUtil.secret(password)) {
            throw new BusinessException(CodeEnum.PASSWORD_ERROR, HttpStatus.BAD_REQUEST);
        }

        String token = JWTUtil.genToken(app.getAppKey(), userByAccount.getUserId());

        JSONObject obj = new JSONObject();
        obj.put("token", token);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
