package com.xzsd.app.managerInformation.dao;

import com.xzsd.app.managerInformation.entity.ManagerInformationInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManagerInformationDao {
    /**
     * 查询司机列表
     */
    List<ManagerInformationInfo> listManagerDrivers(String userCode);
}
