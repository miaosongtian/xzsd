package com.xzsd.app.clientInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientInformation.service.ClientInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("clientInformation")
public class ClientInformationController {
    private static final Logger logger = LoggerFactory.getLogger(ClientInformationController.class);
    @Resource
    private ClientInformationService clientInformationService;


    /**
     * 修改个人邀请码
     * author:miaosongtian
     * time:2020-4-29
     */
    @PostMapping("updateClientInvite")
    public AppResponse addGoodsEvaluate(String inviteCode) {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            return clientInformationService.updateClientInvite(inviteCode,userCode);
        } catch (Exception e) {
            logger.error("修改邀请码错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
