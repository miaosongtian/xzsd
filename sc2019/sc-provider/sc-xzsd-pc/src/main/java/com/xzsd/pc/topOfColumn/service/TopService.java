package com.xzsd.pc.topOfColumn.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.pc.topOfColumn.dao.TopDao;
import com.xzsd.pc.topOfColumn.entity.TopInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TopService {
    @Resource
    private TopDao topDao;

    /**
     * 查询顶部栏
     * author:miaosongtian
     * time:2020-4-21
     */
    public AppResponse getTopOfColumn(String userCode){
         TopInfo topInfoResult = topDao.getTopOfColumn(userCode);
        return AppResponse.success("查询成功！",topInfoResult);
    }
}
