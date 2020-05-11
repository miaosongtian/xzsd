package com.xzsd.pc.driver.dao;

import com.xzsd.pc.driver.entity.DriverInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriverDao {
    /**
     * 统计司机账号数量
     */
    int countUserAcct(DriverInfo driverInfo);
    /**
     * 新增司机
     */
    int addDriver(DriverInfo driverInfo);
    /**
     * 分页查询司机列表（管理员）
     */
    List<DriverInfo> listDrivers(DriverInfo driverInfo);
    /**
     * 查询司机列表（店长）
     */
    List<DriverInfo> listDriverRole2(DriverInfo driverInfo);
    /**
     * 查询司机详情
     */
    DriverInfo getDriver(@Param("driverId") String driverId);
    /**
     * 修改门店
     */
    int updateDriver(DriverInfo driverInfo);
    /**
     * 删除司机
     */
    int deleteDriver(@Param("listId") List<String> listId,@Param("userCode") String userCode);
}
