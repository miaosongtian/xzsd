package com.xzsd.app.managerInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.managerInformation.service.ManagerInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("managerInformation")
public class ManagerInformationController {
    private static final Logger logger = LoggerFactory.getLogger(ManagerInformationController.class);
    @Resource
    private ManagerInformationService managerInformationService;

    /**
     * 查询门店下司机列表
     * author:miaosongtian
     * time:2020-4-29
     */
    @PostMapping("listManagerDrivers")
    public AppResponse listManagerDrivers() {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            return managerInformationService.listManagerDrivers(userCode);
        } catch (Exception e) {
            logger.error("查询司机列表错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
