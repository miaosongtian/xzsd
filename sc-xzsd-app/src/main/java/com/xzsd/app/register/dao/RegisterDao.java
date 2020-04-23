package com.xzsd.app.register.dao;

import com.xzsd.app.register.entity.RegisterInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterDao {
    /**
     * 注册客户
     */
    int clientRegister(RegisterInfo registerInfo);
    /**
     * 统计账号数量
     */
    int countAcct(RegisterInfo registerInfo);
    /**
     * 统计邀请码数量
     */
    int countInviteCode(RegisterInfo registerInfo);
}
