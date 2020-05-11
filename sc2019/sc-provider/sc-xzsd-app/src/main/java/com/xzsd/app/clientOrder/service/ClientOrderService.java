package com.xzsd.app.clientOrder.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.app.clientOrder.dao.ClientOrderDao;
import com.xzsd.app.clientOrder.entity.ClientOrderInfo;
import com.xzsd.app.clientOrder.entity.ClientOrderOutput;
import com.xzsd.app.clientOrder.entity.EvaluateList;
import com.xzsd.app.clientOrder.entity.GoodsEvaluateScoreAndSales;
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
        //定义一个list用于装goodsId
        List<ClientOrderInfo> listGoodsInventory = new ArrayList<>();
        for (int i = 0;i < listGoodsId.size();i++){
            ClientOrderInfo clientOrderInfo1 = new ClientOrderInfo();
            clientOrderInfo1.setGoodsId(listGoodsId.get(i));
            listGoodsInventory.add(clientOrderInfo1);
        }
        //更新库存量
        clientOrderDao.setGoodsInventory(listGoodsInventory);
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
        //如果订单状态为取消订单“1”，则同时删除对应订单详情中的商品信息
        if (clientOrderInfo.getOrderStateId().equals("1")){
            clientOrderDao.deleteOrderGoods(clientOrderInfo);
        }
        AppResponse appResponse = AppResponse.success("状态修改成功！");
        int count = clientOrderDao.updateOrderState(clientOrderInfo);
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
     * 新增订单商品评价
     * author:miaosongtian
     * time:2020-4-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoodsEvaluate(ClientOrderInfo clientOrderInfo){
        //新增商品评价
        List<EvaluateList> listEvaluateAdd = new ArrayList<>();
        for (int i = 0;i < clientOrderInfo.getEvaluateList().size();i++){
            //生成评价编号
            clientOrderInfo.getEvaluateList().get(i).setEvaluateId(StringUtil.getCommonCode(2));
            clientOrderInfo.getEvaluateList().get(i).setOrderId(clientOrderInfo.getOrderId());
            clientOrderInfo.getEvaluateList().get(i).setCreateBy(clientOrderInfo.getCreateBy());
            listEvaluateAdd.add(clientOrderInfo.getEvaluateList().get(i));
        }
        int count = clientOrderDao.addGoodsEvaluate(listEvaluateAdd);
        if (count == 0){
            return AppResponse.bizError("新增失败，请重试！");
        }
        //修改订单状态为5已完成已评价
        ClientOrderInfo clientOrderInfo1 = new ClientOrderInfo();
        clientOrderInfo1.setOrderId(clientOrderInfo.getOrderId());
        clientOrderInfo1.setLastModifiedBy(clientOrderInfo.getCreateBy());
        clientOrderDao.updateOrderStateTo5(clientOrderInfo1);
        //定义一个listGet用于保存从数据库中获取平均评价等级和销售次数
        List<GoodsEvaluateScoreAndSales> listGet = clientOrderDao.getGoodsEvaluateScoreAndSales(clientOrderInfo.getOrderId());
        //定义一个listSet用于把更新后的平均评价等级和销售次数传入数据库
        List<GoodsEvaluateScoreAndSales> listSet = new ArrayList<>();
        //通过运算得到最新平均评价等级(最新评价等级 = （评价等级*销售次数+当前商品评分）/（销售次数+1）)
        for (int i = 0;i < clientOrderInfo.getEvaluateList().size();i++){
            String buffer = clientOrderInfo.getEvaluateList().get(i).getGoodsId();
            for (int j = 0;j < listGet.size();j++){
                if (listGet.get(j).getGoodsId().equals(buffer) ){
                    double evaluateScore = (listGet.get(j).getGoodsEvaluateScore()*listGet.get(j).getGoodsSales()+clientOrderInfo.getEvaluateList().get(i).getEvaluateScore())/(listGet.get(j).getGoodsSales()+1);
                    GoodsEvaluateScoreAndSales goodsEvaluateScoreAndSales = new GoodsEvaluateScoreAndSales();
                    //四舍五入
                    BigDecimal bigDecimal = new BigDecimal(evaluateScore);
                    double resultScore = bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    goodsEvaluateScoreAndSales.setGoodsEvaluateScore(resultScore);
                    goodsEvaluateScoreAndSales.setGoodsSales(listGet.get(j).getGoodsSales()+1);
                    goodsEvaluateScoreAndSales.setGoodsId(listGet.get(j).getGoodsId());
                    listSet.add(goodsEvaluateScoreAndSales);
                }
            }
        }
        //将listSet传入数据库
        clientOrderDao.setGoodsSalesAndEvaluate(listSet);
        return AppResponse.success("新增成功！");
    }

    /**
     * 查询订单评价商品信息列表
     * author:miaosongtian
     * time:2020-04-29
     */
    public AppResponse listGoodsForEvaluate(String orderId) {
        ClientOrderOutput clientOrderOutput = new ClientOrderOutput();
        clientOrderOutput.setGoodsList(clientOrderDao.listGoodsForEvaluate(orderId));
        if (clientOrderOutput == null){
            return AppResponse.success("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功！",clientOrderOutput);
    }
}
