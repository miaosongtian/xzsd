package com.xzsd.pc.selectCombox.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.selectCombox.dao.SelectComboxDao;
import com.xzsd.pc.selectCombox.entity.AreaInfo;
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
        AreaInfo areaList = new AreaInfo();
        areaList.setAreaList(selectComboxDao.listArea(areaId));
        if (areaList == null){
            return AppResponse.success("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功！", areaList);
    }
}
