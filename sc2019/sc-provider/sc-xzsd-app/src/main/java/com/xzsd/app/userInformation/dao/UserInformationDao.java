package com.xzsd.app.userInformation.dao;

import com.xzsd.app.userInformation.entity.UpdatePassword;
import com.xzsd.app.userInformation.entity.UserInformationInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInformationDao {
    /**
     * 获取用户角色
     */
    String getRole(String userCode);
    /**
     * 查询用户个人信息（4客户或3司机）
     */
    UserInformationInfo getUser4Or3(@Param("userCode") String userCode);
    /**
     * 查询用户个人信息（2店长）
     */
    UserInformationInfo getUser2(@Param("userCode") String userCode);
    /**
     * 修改个人密码
     */
    int updateUserPassword(UpdatePassword updatePassword);
    /**
     * 查找数据库密码
     */
    String findPwd(@Param("userCode") String userCode);
}
