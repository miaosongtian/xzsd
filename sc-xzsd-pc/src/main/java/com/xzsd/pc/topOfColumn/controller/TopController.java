package com.xzsd.pc.topOfColumn.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.topOfColumn.entity.TopInfo;
import com.xzsd.pc.topOfColumn.service.TopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("topOfColumn")
public class TopController {
    private static final Logger logger = LoggerFactory.getLogger(TopController.class);
    @Resource
    private TopService topService;

    /**
     *  查询顶部栏
     * author: miaosongtian
     * time:2020-04-21
     */
    @PostMapping("getTopOfColumn")
    public AppResponse getTopOfColumn() {
        try {
            //获取当前登录用户id
            String userCode = SecurityUtils.getCurrentUserId();
            //查询顶部栏
            AppResponse appResponse = topService.getTopOfColumn(userCode);
            return appResponse;
        } catch (Exception e) {
            logger.error("顶部栏查询异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
