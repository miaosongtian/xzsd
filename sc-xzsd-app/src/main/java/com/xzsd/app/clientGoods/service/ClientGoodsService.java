package com.xzsd.app.clientGoods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.xzsd.app.clientGoods.dao.ClientGoodsDao;
import com.xzsd.app.clientGoods.entity.ClientGoodsInfo;
import com.xzsd.app.clientGoods.entity.EvaluatesIn;
import com.xzsd.app.clientGoods.entity.EvaluatesOut;
import com.xzsd.app.clientGoods.entity.GetGoodsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.neusoft.core.page.PageUtils.getPageInfo;

@Service
public class ClientGoodsService {
    @Resource
    private ClientGoodsDao clientGoodsDao;

    /**
     * 查询一级分类
     * author:miaosongtian
     * time:2020-4-24
     */
    public AppResponse listOneGoodsClassify(){
        ClientGoodsInfo oneClassifyList = new ClientGoodsInfo();
        oneClassifyList.setOneClassifyList(clientGoodsDao.listOneGoodsClassify());
        if (oneClassifyList == null) {
            return AppResponse.bizError("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功",oneClassifyList);
    }

    /**
     * 查询二级分类和商品
     * author:miaosongtian
     * time:2020-4-24
     */
    public AppResponse listGetClassGoods(String classifyId) {
        ClientGoodsInfo twoClassifyList = new ClientGoodsInfo();
        twoClassifyList.setTwoClassifyList(clientGoodsDao.listGetClassGoods(classifyId));
        if (twoClassifyList == null) {
            return AppResponse.bizError("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功",twoClassifyList);
    }

    /**
     * 查询商品信息详情
     * author:miaosongtian
     * time:2020-4-25
     */
    public AppResponse getGoods(String goodsId) {
        //查询商品信息
        GetGoodsVO goods = clientGoodsDao.getGoods(goodsId);
        //获取当前登录用户id，查询门店名称
        String userCode = SecurityUtils.getCurrentUserId();
        goods.setStoreName(clientGoodsDao.getGoodsStoreName(userCode));
        return AppResponse.success("查询成功",goods);
    }

    /**
     * 查询商品评价列表
     * author:miaosongtian
     * time:2020-4-25
     */
    public AppResponse listGoodsEvaluates(EvaluatesIn evaluatesIn){
        PageHelper.startPage(evaluatesIn.getPageNum(), evaluatesIn.getPageSize());
        List<EvaluatesIn> list = clientGoodsDao.listGoodsEvaluatesByPage(evaluatesIn);
        // 包装Page对象
        PageInfo<EvaluatesIn> pageData = new PageInfo<EvaluatesIn>(list);
//        return AppResponse.success("查询成功！", getPageInfo(list));
        return AppResponse.success("查询成功！", pageData);
    }
}
