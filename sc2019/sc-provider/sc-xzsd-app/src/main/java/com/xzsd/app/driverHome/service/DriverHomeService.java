package com.xzsd.app.driverHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.driverHome.dao.DriverHomeDao;
import com.xzsd.app.driverHome.entity.DriverHomeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DriverHomeService {
    @Resource
    private DriverHomeDao driverHomeDao;

    /**
     * 查询司机端门店列表
     * author:miaosongtian
     * time:2020-4-29
     */
    public AppResponse listDriverStores(String userCode){
        DriverHomeVO driverHomeVO = new DriverHomeVO();
        driverHomeVO.setList(driverHomeDao.listDriverStores(userCode));
        if (driverHomeVO == null){
            return AppResponse.bizError("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功",driverHomeVO);
    }
}
