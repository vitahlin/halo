package com.vitah.halo.controller;

import com.vitah.halo.dto.AppSummaryDTO;
import com.vitah.halo.entity.App;
import com.vitah.halo.mapper.AppMapper;
import com.vitah.halo.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author vitah
 */
@RestController
public class AppController {

    @Autowired
    private AppRepository appRepository;

    /**
     * 获取支持的App
     *
     * @param appId
     * @param platform
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    public List<AppSummaryDTO> getAllApps(
        @RequestHeader(value = "X-APP-ID") Integer appId,
        @RequestHeader(value = "X-Platform") Integer platform,
        @RequestHeader(value = "X-Device-ID") String deviceId
    ) {
        List<App> appList = appRepository.findAll();
        return AppMapper.INSTANCE.appsToDTOs(appList);
    }
}
