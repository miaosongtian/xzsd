package com.xzsd.app.managerOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderOutput;
import com.xzsd.app.managerOrder.dao.ManagerOrderDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerOrderService {
    @Resource
    private ManagerOrderDao managerOrderDao;
    @Resource
    private ClientOrderDao clientOrderDao;

    /**
     * 分页查询订单列表
     * author:miaosongtian
     * time:2020-04-29
     */
    public AppResponse listManagerOrders(String orderStateId, String userCode) {
        ClientOrderInfo clientOrderInfo = new ClientOrderInfo();
        clientOrderInfo.setCreateBy(userCode);
        clientOrderInfo.setOrderStateId(orderStateId);
        PageHelper.startPage(clientOrderInfo.getPageNum(), clientOrderInfo.getPageSize());
        List<ClientOrderOutput> orderInfoList = managerOrderDao.listManagerOrders(clientOrderInfo);
        // 包装Page对象
        PageInfo<ClientOrderOutput> pageData = new PageInfo<ClientOrderOutput>(orderInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 修改订单状态
     * author:miaosongtian
     * time:2020-04-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateManagerOrderState(ClientOrderInfo clientOrderInfo) {
        AppResponse appResponse = AppResponse.success("状态修改成功！");
        int count = managerOrderDao.updateManagerOrderState(clientOrderInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("状态修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询订单详情
     * author:miaosongtian
     * time:2020-04-29
     */
    public AppResponse listManagerOrderDeepen(String orderId){
        ClientOrderOutput clientOrderOutput = managerOrderDao.listManagerOrderDeepen(orderId);
        clientOrderOutput.setGoodsList(clientOrderDao.listGoods(orderId));
        if (clientOrderOutput == null){
            return AppResponse.success("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功！",clientOrderOutput);
    }
}
