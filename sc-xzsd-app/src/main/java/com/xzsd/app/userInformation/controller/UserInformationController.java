package com.xzsd.app.userInformation.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.userInformation.entity.UpdatePassword;
import com.xzsd.app.userInformation.service.UserInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("userInformation")
public class UserInformationController {
    private static final Logger logger = LoggerFactory.getLogger(UserInformationController.class);
    @Resource
    private UserInformationService userInformationService;

    /**
     * 查询用户个人信息
     * @author miaosongtian
     * @Date 2020-4-22
     */
    @PostMapping("getUser")
    public AppResponse getUser() {
        try {
            String userCode = SecurityUtils.getCurrentUserId();
            return userInformationService.getUser(userCode);
        } catch (Exception e) {
            logger.error("用户个人信息查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改用户个人密码
     * @author miaosongtian
     * @Date 2020-4-22
     */
    @PostMapping("updateUserPassword")
    public AppResponse updateUserPassword(UpdatePassword updatePassword) {
        try {
            String userCode = SecurityUtils.getCurrentUserId();
            updatePassword.setUserCode(userCode);
            return userInformationService.updateUserPassword(updatePassword);
        } catch (Exception e) {
            logger.error("用户个人密码修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
