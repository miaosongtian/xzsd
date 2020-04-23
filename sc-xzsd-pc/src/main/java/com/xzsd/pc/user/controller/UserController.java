package com.xzsd.pc.user.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.xzsd.pc.user.entity.UserInfo;
import com.xzsd.pc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    /**
     *  新增用户
     * @param userInfo
     * @return AppResponse
     * @author miao
     * @Date 2020-03-25
     */
    @PostMapping("addUser")
    public AppResponse addUser(UserInfo userInfo) {
        try {
            //获取用户id
            String userCode = SecurityUtils.getCurrentUserId();
            userInfo.setCreateBy(userCode);
            AppResponse appResponse = userService.addUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }
    /**
     * 修改用户
     * @param userInfo
     * @return AppResponse
     * @author miaosongtian
     * @Date 2020-03-26
     */
    @PostMapping("updateUser")
    public AppResponse updateUser(UserInfo userInfo) {
        try {
            //更新用户信息
            String userCode = SecurityUtils.getCurrentUserId();
            userInfo.setCreateBy(userCode);
            userInfo.setLastModifiedBy(userCode);
            AppResponse appResponse = userService.updateUser(userInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("用户修改失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 删除用户
     * @param userId
     * @return AppResponse
     * @author miaosongtian
     * @Date 2020-03-26
     */
    @PostMapping("deleteUser")
    public AppResponse deleteUser(String userId) {
        try {
            //获取用户id
            String userCode = SecurityUtils.getCurrentUserId();
            return userService.deleteUser(userId,userCode);
        } catch (Exception e) {
            logger.error("用户删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询用户详情
     *
     * @param userId
     * @return AppResponse
     * @author miaosongtian
     * @Date 2020-03-26
     */
    @RequestMapping(value = "getUser")
    public AppResponse findUserById(String userId) {
        try {
            return userService.findUserById(userId);
        } catch (Exception e) {
            logger.error("用户查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 用户列表(分页)
     *
     * @param userInfo
     * @return AppResponse
     * @author miaosongtian
     * @Date 2020-03-26
     */
    @RequestMapping(value = "listUsers")
    public AppResponse listUsers(UserInfo userInfo) {
        try {
            return userService.listUsers(userInfo);
        } catch (Exception e) {
            logger.error("查询用户列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
