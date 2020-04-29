package com.xzsd.app.clientShopCart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientShopCartService {
    @Resource
    private ClientShopCartDao clientShopCartDao;

    /**
     * 新增购物车
     * author:miaosongtian
     * time:2020-4-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addShoppingCart(ClientShopCartInfo clientShopCartInfo){
        clientShopCartInfo.setShopCartId(StringUtil.getCommonCode(2));
        clientShopCartInfo.setIsDeleted(0);
        // 新增购物车
        int count = clientShopCartDao.addShoppingCart(clientShopCartInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询购物车列表
     * author:miaosongtian
     * time:2020-4-26
     */
    public AppResponse listShoppingCarts(ClientShopCartInfo clientShopCartInfo){
        PageHelper.startPage(clientShopCartInfo.getPageNum(), clientShopCartInfo.getPageSize());
        List<ClientShopCartInfo> list = clientShopCartDao.listShoppingCarts(clientShopCartInfo);
        // 包装Page对象
        PageInfo<ClientShopCartInfo> pageData = new PageInfo<ClientShopCartInfo>(list);
//        return AppResponse.success("查询成功！", getPageInfo(list));
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 修改购物车商品数量
     * author:miaosongtian
     * time:2020-4-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateShoppingCart(ClientShopCartInfo clientShopCartInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        // 修改门店
        int count = clientShopCartDao.updateShoppingCart(clientShopCartInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 删除购物车商品
     * author:miaosongtian
     * time:2020-4-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteShoppingCart(String shopCartId,String userCode) {
        List<String> listId = Arrays.asList(shopCartId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
        int count = clientShopCartDao.deleteShoppingCart(listId,userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }
}
