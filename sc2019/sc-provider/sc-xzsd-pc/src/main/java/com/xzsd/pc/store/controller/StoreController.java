package com.xzsd.pc.store.controller;

import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.pc.store.entity.StoreInfo;
import com.xzsd.pc.store.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("store")
public class StoreController {
    private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
    @Resource
    private StoreService storeService;

    /**
     *  新增门店
     * author: miaosongtian
     * time:2020-04-18
     */
    @PostMapping("addStore")
    public AppResponse addStore(StoreInfo storeInfo) {
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            storeInfo.setCreateBy(userCode);
            AppResponse appResponse = storeService.addStore(storeInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("门店新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 分页查询门店列表
     * author:miaosongtian
     * time:2020-4-19
     */
    @PostMapping("listStores")
    public AppResponse listStores(StoreInfo storeInfo){
        try {
            //获取用户id
            String userCode =  SecurityUtils.getCurrentUserId();
            storeInfo.setCreateBy(userCode);
            return storeService.listStores(storeInfo);
        } catch (Exception e) {
            logger.error("查询门店列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询门店详情
     * author:miaosongtian
     * time:2020-4-19
     */
    @PostMapping("getStore")
    public AppResponse getStore (String storeId){
        try{
            //查询门店详情
            return storeService.getStore(storeId);
        }catch (Exception e) {
            logger.error("门店查询详情错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 修改门店
     * author:miaosongtian
     * time:2020-04-19
     */
    @PostMapping("updateStore")
    public AppResponse updateStore (StoreInfo storeInfo){
        try{
            //修改门店
            String userCode = SecurityUtils.getCurrentUserId();
            storeInfo.setLastModifiedBy(userCode);
            return storeService.updateStore(storeInfo);
        }catch (Exception e) {
            logger.error("门店修改错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除门店
     * author:miaosongtian
     * time:2020-4-19
     */
    @PostMapping("deleteStore")
    public AppResponse deleteStore (String storeId){
        try{
            //删除门店
            String userCode =  SecurityUtils.getCurrentUserId();
            StoreInfo storeInfo = new StoreInfo();
            storeInfo.setLastModifiedBy(userCode);
            return storeService.deleteStore(storeId,userCode);
        }catch (Exception e) {
            logger.error("门店删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
