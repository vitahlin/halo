package com.vitah.halo.controller;

import com.alibaba.fastjson.JSONObject;
import com.vitah.halo.constant.CodeEnum;
import com.vitah.halo.entity.App;
import com.vitah.halo.entity.User;
import com.vitah.halo.entity.UserByAnonymous;
import com.vitah.halo.exception.BusinessException;
import com.vitah.halo.repository.AppRepository;
import com.vitah.halo.repository.UserByAnonymousRepository;
import com.vitah.halo.repository.UserRepository;
import com.vitah.halo.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author vitah
 */
public class SignInController {

    @Autowired
    private UserByAnonymousRepository userByAnonymousRepository;

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
            user.setIsAnonymous(1);
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
}
