package com.xzsd.app.clientHome.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.service.ClientHomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("clientHome")
public class ClientHomeController {
    private static final Logger logger = LoggerFactory.getLogger(ClientHomeController.class);
    @Resource
    private ClientHomeService clientHomeService;

    /**
     * 查询轮播图
     * author:miaosongtian
     * time:2020-4-22
     */
    @PostMapping("listRotationCharHome")
    public AppResponse listRotationCharHome() {
        try {
            return clientHomeService.listRotationCharHome();
        } catch (Exception e) {
            logger.error("轮播图查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
