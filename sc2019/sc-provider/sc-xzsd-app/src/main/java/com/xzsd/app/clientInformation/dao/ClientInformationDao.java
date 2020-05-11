package com.xzsd.app.clientInformation.dao;

import com.xzsd.app.clientInformation.entity.ClientInfomationInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientInformationDao {
    /**
     * 修改个人邀请码
     */
    int updateClientInvite(ClientInfomationInfo clientInfomationInfo);
}
