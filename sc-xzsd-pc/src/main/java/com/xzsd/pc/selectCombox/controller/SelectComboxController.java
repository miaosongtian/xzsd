package com.xzsd.pc.selectCombox.controller;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.selectCombox.service.SelectComboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("selectCombox")
public class SelectComboxController {
    private static final Logger logger = LoggerFactory.getLogger(SelectComboxController.class);
    @Resource
    private SelectComboxService selectComboxService;

    /**
     * 查询省市区下拉框
     * author:miaosongtian
     * time:2020-4-17
     */
    @PostMapping("listArea")
    public AppResponse listArea(String areaId) {
        try {
            return selectComboxService.listArea(areaId);
        } catch (Exception e) {
            logger.error("查询省市区下拉框异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
