package com.xzsd.app.driverHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.driverHome.service.DriverHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("driverHome")
public class DriverHomeController {
    private static final Logger logger = LoggerFactory.getLogger(DriverHomeController.class);
    @Resource
    private DriverHomeService driverHomeService;

    /**
     *  查询司机端门店列表
     * author: miaosongtian
     * time:2020-04-29
     */
    @PostMapping("listDriverStores")
    public AppResponse listDriverStores() {
        try {
            //获取用户id
            String userCode = SecurityUtils.getCurrentUserId();
            AppResponse appResponse = driverHomeService.listDriverStores(userCode);
            return appResponse;
        } catch (Exception e) {
            logger.error("查询门店列表错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
