package com.xzsd.app.managerInformation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.managerInformation.dao.ManagerInformationDao;
import com.xzsd.app.managerInformation.entity.ManagerInformationInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerInformationService {
    @Resource
    private ManagerInformationDao managerInformationDao;

    /**
     * 分页查询司机列表
     * author:miaosongtian
     * time:2020-04-29
     */
    public AppResponse listManagerDrivers(String userCode) {
        ManagerInformationInfo managerOrderInfo = new ManagerInformationInfo();
        PageHelper.startPage(managerOrderInfo.getPageNum(), managerOrderInfo.getPageSize());
        List<ManagerInformationInfo> driverList = managerInformationDao.listManagerDrivers(userCode);
        // 包装Page对象
        PageInfo<ManagerInformationInfo> pageData = new PageInfo<ManagerInformationInfo>(driverList);
        return AppResponse.success("查询成功！", pageData);
    }
}
