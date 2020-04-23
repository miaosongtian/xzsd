package com.xzsd.app.userInformation.dao;

import com.xzsd.app.userInformation.entity.UpdatePassword;
import com.xzsd.app.userInformation.entity.UserInformationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInformationDao {
    /**
     * 查询用户个人信息
     */
    UserInformationInfo getUser(@Param("userCode") String userCode);
    /**
     * 修改个人密码
     */
    int updateUserPassword(UpdatePassword updatePassword);
    /**
     * 查找数据库密码
     */
    String findPwd(@Param("userCode") String userCode);
}
