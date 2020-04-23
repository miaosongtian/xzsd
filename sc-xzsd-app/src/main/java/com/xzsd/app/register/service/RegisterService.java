package com.xzsd.app.register.service;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.register.dao.RegisterDao;
import com.xzsd.app.register.entity.RegisterInfo;
import com.xzsd.app.utils.PasswordUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class RegisterService {
    @Resource
    private RegisterDao registerDao;

    /**
     * 客户注册
     * author:miaosongtian
     * time:2020-4-22
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse clientRegister(RegisterInfo registerInfo){
        // 校验账号是否存在
        int countAcct = registerDao.countAcct(registerInfo);
        if(0 != countAcct) {
            return AppResponse.bizError("该账号已被注册，请重新输入！");
        }
        // 校验门店邀请码是否存在
        int countInviteCode = registerDao.countInviteCode(registerInfo);
        if(0 == countInviteCode) {
            return AppResponse.bizError("该邀请码不存在，请重新输入！");
        }
        registerInfo.setUserId(StringUtil.getCommonCode(2));
        // 密码加密
        String pwd = PasswordUtils.generatePassword(registerInfo.getUserPassword());
        registerInfo.setUserPassword(pwd);
        // 新增门店
        int count = registerDao.clientRegister(registerInfo);
        if(0 == count) {
            return AppResponse.bizError("注册失败，请重试！");
        }
        return AppResponse.success("注册成功！");
    }
}
