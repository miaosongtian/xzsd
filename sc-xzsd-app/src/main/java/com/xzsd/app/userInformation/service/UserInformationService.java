package com.xzsd.app.userInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.userInformation.dao.UserInformationDao;
import com.xzsd.app.userInformation.entity.UpdatePassword;
import com.xzsd.app.userInformation.entity.UserInformationInfo;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInformationService {
    @Resource
    private UserInformationDao userInformationDao;

    /**
     * 查询用户个人信息
     * @Author miaosongtian
     * @Date 2020-4-22
     */
    public AppResponse getUser(String userCode){
        UserInformationInfo userInformationInfo = userInformationDao.getUser(userCode);
        return AppResponse.success("查询成功！", userInformationInfo);
    }

    /**
     * 修改用户个人密码
     * @Author miaosongtian
     * @Date 2020-4-22
     */
    public AppResponse updateUserPassword(UpdatePassword updatePassword){
        AppResponse appResponse = AppResponse.success("修改成功！");
        // 密码加密
        String pwd = PasswordUtils.generatePassword(updatePassword.getUserNewPassword());
        updatePassword.setUserNewPassword(pwd);
        //校验密码
        String findPwd = userInformationDao.findPwd(updatePassword.getUserCode());
        boolean ifTure = PasswordUtils.PasswordMacth(updatePassword.getUserPassword(),findPwd);
        //修改密码
        if (ifTure == true) {
            int count = userInformationDao.updateUserPassword(updatePassword);
            if (count == 0) {
                appResponse = AppResponse.success("修改失败，请重试！");
            }
        }else{
            appResponse = AppResponse.success("原密码错误，请重试！");
        }
        return appResponse;
    }
}
