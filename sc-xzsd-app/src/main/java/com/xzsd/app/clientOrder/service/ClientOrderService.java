package com.xzsd.app.clientOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderOutput;
import com.xzsd.app.clientShopCart.dao.ClientShopCartDao;
import com.xzsd.app.clientShopCart.entity.ClientShopCartInfo;
import com.xzsd.app.clientShopCart.service.ClientShopCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientOrderService {
    @Resource
    private ClientOrderDao clientOrderDao;
    @Resource
    private ClientShopCartService clientShopCartService;

    /**
     * 新增订单
     * author:miaosongtian
     * time:2020-4-26
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addOrder(ClientOrderInfo clientOrderInfo){
        //生成订单编号
        clientOrderInfo.setOrderId(StringUtil.getCommonCode(2));
        clientOrderInfo.setIsDeleted(0);
        //如果从购物车中下单则购物车中的商品删除
        if (clientOrderInfo.getShopCartId() != null){
            //删除购物车中结算的商品
            clientShopCartService.deleteShoppingCart(clientOrderInfo.getShopCartId(),clientOrderInfo.getCreateBy());
        }
        //统计订单中商品数量
        int orderAllGoodsCount = 0;
        List<String> listGoodsNum = Arrays.asList(clientOrderInfo.getClientGoodsNum().split(","));
        for (int i = 0;i < listGoodsNum.size();i++){
            orderAllGoodsCount = orderAllGoodsCount + Integer.parseInt(listGoodsNum.get(i));
        }
        clientOrderInfo.setOrderAllGoodsCount(orderAllGoodsCount);
        //统计订单总价,价格保留两位小数
        double orderAllCost = 0;
        List<String> listAllPrice = Arrays.asList(clientOrderInfo.getGoodsPrice().split(","));
        for (int i = 0;i < listAllPrice.size();i++){
            orderAllCost = orderAllCost + Double.parseDouble(listGoodsNum.get(i)) * Double.parseDouble(listAllPrice.get(i));
        }
        //四舍五入
        BigDecimal bigDecimal = new BigDecimal(orderAllCost);
        double resultCost = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        clientOrderInfo.setOrderAllCost(String.valueOf(resultCost));
        // 新增订单
        int count = clientOrderDao.addOrder(clientOrderInfo);
        if(0 == count) {
            return AppResponse.bizError("新增订单失败，请重试！");
        }
        //新增订单详情，商品信息
        List<String> listGoodsId = Arrays.asList(clientOrderInfo.getGoodsId().split(","));
        List<String> listGoodsPrice = Arrays.asList(clientOrderInfo.getGoodsPrice().split(","));
        List<String> listNum = Arrays.asList(clientOrderInfo.getClientGoodsNum().split(","));
        //定义一个变量用于存放listGoodsId,listNum,listGoodsPrice
        List<ClientOrderInfo> listGoodsAdd = new ArrayList<>();
        for (int i = 0;i < listGoodsId.size();i++){
            ClientOrderInfo clientOrderInfo1 = new ClientOrderInfo();
            //生成订单详情编号
            clientOrderInfo1.setOrderDetailId(StringUtil.getCommonCode(3));
            clientOrderInfo1.setOrderId(clientOrderInfo.getOrderId());
            clientOrderInfo1.setCreateBy(clientOrderInfo.getCreateBy());
            clientOrderInfo1.setClientGoodsNum(listNum.get(i));
            clientOrderInfo1.setGoodsId(listGoodsId.get(i));
            clientOrderInfo1.setGoodsPrice(listGoodsPrice.get(i));
            clientOrderInfo1.setStoreId(clientOrderInfo.getStoreId());
            listGoodsAdd.add(clientOrderInfo1);
        }
        int countGoods = clientOrderDao.addOrderDetail(listGoodsAdd);
        if(0 == countGoods) {
            return AppResponse.bizError("新增订单中的商品失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 分页查询订单列表
     * author:miaosongtian
     * time:2020-04-27
     */
    public AppResponse listOrder(String orderStateId,String userCode) {
        ClientOrderInfo clientOrderInfo = new ClientOrderInfo();
        clientOrderInfo.setCreateBy(userCode);
        clientOrderInfo.setOrderStateId(orderStateId);
        PageHelper.startPage(clientOrderInfo.getPageNum(), clientOrderInfo.getPageSize());
        List<ClientOrderOutput> orderInfoList = clientOrderDao.listOrder(clientOrderInfo);
        // 包装Page对象
        PageInfo<ClientOrderOutput> pageData = new PageInfo<ClientOrderOutput>(orderInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 修改订单状态
     * author:miaosongtian
     * time:2020-04-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateOrderState(ClientOrderInfo clientOrderInfo) {
        //把字符串通过逗号分成list
        List<String> listOrderId = Arrays.asList(clientOrderInfo.getOrderId().split(","));
        List<String> listVersion = Arrays.asList(clientOrderInfo.getVersion().split(","));
        //定义一个变量用于存放listOrderId,listVersion
        List<ClientOrderInfo> listUpdata = new ArrayList<>();
        String orderStateId = clientOrderInfo.getOrderStateId();
        String userCode = clientOrderInfo.getLastModifiedBy();
        for (int i = 0;i < listOrderId.size();i++){
            ClientOrderInfo clientOrderInfo1 = new ClientOrderInfo();
            clientOrderInfo1.setOrderStateId(orderStateId);
            clientOrderInfo1.setLastModifiedBy(userCode);
            clientOrderInfo1.setVersion(listVersion.get(i));
            clientOrderInfo1.setOrderId(listOrderId.get(i));
            listUpdata.add(clientOrderInfo1);
        }
        AppResponse appResponse = AppResponse.success("状态修改成功！");
        int count = clientOrderDao.updateOrderState(listUpdata);
        if(0 == count) {
            appResponse = AppResponse.bizError("状态修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询订单详情
     * author:miaosongtian
     * time:2020-04-28
     */
    public AppResponse listOrderDeepen(String orderId){
        ClientOrderOutput clientOrderOutput = clientOrderDao.listOrderDeepen(orderId);
        clientOrderOutput.setGoodsList(clientOrderDao.listGoods(orderId));
        if (clientOrderOutput == null){
            return AppResponse.success("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功！",clientOrderOutput);
    }

    /**
     * 新增商品评价
     * author:miaosongtian
     * time:2020-4-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsEvaluate(ClientOrderInfo clientOrderInfo){
        //新增商品评价
        int count = 0;
        for (int i = 0;i < clientOrderInfo.getEvaluateList().size();i++){
            int cnt = clientOrderDao.addGoodsEvaluate(clientOrderInfo.getEvaluateList().get(i));
            //新增商品图片评价
//            clientOrderDao.addPicture(clientOrderInfo.getEvaluateList());
            count += cnt;
        }
        if (count == 0){
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }
}
