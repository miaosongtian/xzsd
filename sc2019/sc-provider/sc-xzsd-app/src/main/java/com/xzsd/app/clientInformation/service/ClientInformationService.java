package com.xzsd.app.clientInformation.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientInformation.dao.ClientInformationDao;
import com.xzsd.app.clientInformation.entity.ClientInfomationInfo;
import com.xzsd.app.register.dao.RegisterDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientInformationService {
    @Resource
    private ClientInformationDao clientInformationDao;
    @Resource
    private RegisterDao registerDao;

    /**
     * 修改个人邀请码
     * authot:miaosongtian
     * time:2020-4-29
     */
    public AppResponse updateClientInvite(String inviteCode,String userCode){
        AppResponse appResponse = AppResponse.success("修改成功！");
        //校验邀请码是否存在
        int countInviteCode = registerDao.countInviteCode(inviteCode);
        if (countInviteCode == 0){
            return AppResponse.success("该邀请码不存在，请重新输入！");
        }
        ClientInfomationInfo clientInfomationInfo = new ClientInfomationInfo();
        clientInfomationInfo.setInviteCode(inviteCode);
        clientInfomationInfo.setUserId(userCode);
        int count = clientInformationDao.updateClientInvite(clientInfomationInfo);
        if (count == 0){
            appResponse = AppResponse.success("修改失败，请重试！");
        }
        return appResponse;
    }
}
