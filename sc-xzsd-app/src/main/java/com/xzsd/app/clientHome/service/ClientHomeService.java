package com.xzsd.app.clientHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.dao.ClientHomeDao;
import com.xzsd.app.clientHome.entity.ClientHomeInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientHomeService {
    @Resource
    private ClientHomeDao clientHomeDao;

    /**
     * 查询首页轮播图
     * author:miaosongtian
     * time:2020-4-22
     */
    public AppResponse listRotationCharHome(){
        List<ClientHomeInfo> rotationCharHomeList = clientHomeDao.listRotationCharHome();
        return AppResponse.success("查询成功",rotationCharHomeList);
    }
}
