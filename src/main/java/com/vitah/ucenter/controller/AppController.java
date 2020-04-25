package com.vitah.ucenter.controller;

import com.vitah.ucenter.dto.AppSummaryDTO;
import com.vitah.ucenter.entity.App;
import com.vitah.ucenter.mapper.AppMapper;
import com.vitah.ucenter.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return
     */
    @RequestMapping(value = "/apps", method = RequestMethod.GET)
    public List<AppSummaryDTO> getAllApps(
    ) {
        List<App> appList = appRepository.findAll();
        return AppMapper.INSTANCE.appsToDTOs(appList);
    }
}
