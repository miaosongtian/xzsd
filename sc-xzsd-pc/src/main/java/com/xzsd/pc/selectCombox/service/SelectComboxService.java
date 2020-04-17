package com.xzsd.pc.selectCombox.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.selectCombox.dao.SelectComboxDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class SelectComboxService {
    @Resource
    private SelectComboxDao selectComboxDao;

    /**
     * 查询省市区下拉框
     * author:miaosongtian
     * time:2020-4-17
     */
    public AppResponse listArea(String areaId) {
        List<String> areaList = selectComboxDao.listArea(areaId);
        return AppResponse.success("查询成功！", areaList);
    }
}
