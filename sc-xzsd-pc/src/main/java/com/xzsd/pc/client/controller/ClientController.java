package com.xzsd.pc.client.controller;

import com.neusoft.core.restful.AppResponse;


import com.xzsd.pc.client.entity.ClientInfo;
import com.xzsd.pc.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("client")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Resource
    private ClientService clientService;
    /**
     * 分页查询客户信息
     * author:miaosongtian
     * time:2020-4-10
     */
    @PostMapping("listClients")
    public AppResponse listClients(ClientInfo clientInfo){
        try {
            return clientService.listClients(clientInfo);
        } catch (Exception e) {
            logger.error("查询客户信息列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
