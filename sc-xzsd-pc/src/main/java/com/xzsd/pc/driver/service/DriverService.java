package com.xzsd.pc.driver.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.driver.dao.DriverDao;
import com.xzsd.pc.driver.entity.DriverInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DriverService {
    @Resource
    private DriverDao driverDao;

    /**
     * 新增司机
     * author:miaosongtian
     * time:2020-4-19
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addDriver(DriverInfo driverInfo){
        // 校验账号是否存在
        int countUserAcct = driverDao.countUserAcct(driverInfo);
        if(0 != countUserAcct) {
            return AppResponse.bizError("司机账号已存在，请重新输入！");
        }
        driverInfo.setDriverId(StringUtil.getCommonCode(2));
        driverInfo.setIsDeleted(0);
        // 新增门店
        int count = driverDao.addDriver(driverInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 分页查询司机列表（按角色查数据）
     * author:miaosongtian
     * time:2020-04-19
     */
    public AppResponse listDrivers(DriverInfo driverInfo) {
        //当角色为0超级管理员或1管理员时，查询所有数据
        if (driverInfo.getRole().equals("1") || driverInfo.getRole().equals("0")){
            PageHelper.startPage(driverInfo.getPageNum(), driverInfo.getPageSize());
            List<DriverInfo> driverInfoList = driverDao.listDrivers(driverInfo);
            // 包装Page对象
            PageInfo<DriverInfo> pageData = new PageInfo<DriverInfo>(driverInfoList);
            return AppResponse.success("查询成功！", pageData);
        }
        //当角色为2店长时，查询该店长的数据
        else if (driverInfo.getRole().equals("2")){
            PageHelper.startPage(driverInfo.getPageNum(), driverInfo.getPageSize());
            List<DriverInfo> driverInfoRole2 = driverDao.listDriverRole2(driverInfo);
            // 包装Page对象
            PageInfo<DriverInfo> pageData = new PageInfo<DriverInfo>(driverInfoRole2);
            return AppResponse.success("查询成功！", pageData);
        }
        else return AppResponse.success("角色没有权限！");
    }
}
