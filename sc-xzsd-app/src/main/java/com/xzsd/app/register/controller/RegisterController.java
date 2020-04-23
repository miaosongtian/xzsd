package com.xzsd.app.register.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.register.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Resource
    private RegisterService registerService;

    /**
     *  新增门店
     * author: miaosongtian
     * time:2020-04-22
     */
    @PostMapping("clientRegister")
    public AppResponse clientRegister(RegisterInfo registerInfo) {
        try {
            //获取用户id
            String userCode =  "客户注册账号";
            registerInfo.setCreateBy(userCode);
            AppResponse appResponse = registerService.clientRegister(registerInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("客户注册异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
