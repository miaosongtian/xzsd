package com.xzsd.app.clientHome.service;

import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientHome.dao.ClientHomeDao;
import com.xzsd.app.clientHome.entity.ClientHomeInfo;
import com.xzsd.app.clientHome.entity.ClientVO;
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
        ClientVO slideshowList = new ClientVO();
        slideshowList.setSlideshowList(clientHomeDao.listRotationCharHome());
        return AppResponse.success("查询成功",slideshowList);
    }

    /**
     * 查询热门商品
     * author:miaosongtian
     * time:2020-4-24
     */
    public AppResponse listHotGoods(){
        ClientVO list = new ClientVO();
        list.setList(clientHomeDao.listHotGoods());
        return AppResponse.success("查询成功",list);
    }
}
