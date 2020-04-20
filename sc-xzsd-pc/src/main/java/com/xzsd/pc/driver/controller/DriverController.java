package com.xzsd.pc.driver.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.driver.entity.DriverInfo;
import com.xzsd.pc.driver.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("driver")
public class DriverController {
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);
    @Resource
    private DriverService driverService;

    /**
     *  新增司机
     * author: miaosongtian
     * time:2020-04-19
     */
    @PostMapping("addDriver")
    public AppResponse addDriver(DriverInfo driverInfo) {
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            driverInfo.setCreateBy(userCode);
            AppResponse appResponse = driverService.addDriver(driverInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("司机新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询司机列表
     * author:miaosongtian
     * time:2020-4-19
     */
    @PostMapping("listDrivers")
    public AppResponse listDrivers(DriverInfo driverInfo){
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            driverInfo.setCreateBy(userCode);
            return driverService.listDrivers(driverInfo);
        } catch (Exception e) {
            logger.error("查询司机列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
