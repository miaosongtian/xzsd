package com.xzsd.pc.goods.service;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.Goods;
import com.xzsd.pc.goods.entity.GoodsClassifyVO;
import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.goods.entity.GoodsState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    private GoodsDao goodsDao;
    /**
     * 新增商品
     * auother:miaosongtian
     * time:2020-3-27
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse addGoods(GoodsInfo goodsInfo){
        // 校验书号是否存在
        int countIsbn = goodsDao.countIsbn(goodsInfo);
        if(0 != countIsbn) {
            return AppResponse.bizError("商品书号已存在，请重新输入！");
        }
        goodsInfo.setGoodsId(StringUtil.getCommonCode(2));
        // 新增商品
        int count = goodsDao.addGoods(goodsInfo);
        if(0 == count) {
            return AppResponse.bizError("新增失败，请重试！");
        }
        return AppResponse.success("新增成功！");
    }

    /**
     * 删除商品
     * author:miaosongtian
     * time:2020-03-28
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String goodsId,String userCode) {
        List<String> listId = Arrays.asList(goodsId.split(", "));
        AppResponse appResponse = AppResponse.success("删除成功！");
        //查看商品是否存在于热门商品或轮播图中，存在不可删
        int countHotGoods = goodsDao.countHotGoods(listId);
        if (countHotGoods != 0){
            return AppResponse.success("该商品存在于热门商品中，不可删除！");
        }
        int countSlideshow = goodsDao.countSlideshow(listId);
        if (countSlideshow != 0){
            return AppResponse.success("该商品存在于轮播图中，不可删除！");
        }
        int count = goodsDao.deleteGoods(listId,userCode);
        if(0 == count) {
            appResponse = AppResponse.bizError("删除失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 修改商品
     * author:miaosongtian
     * time:2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoods(GoodsInfo goodsInfo) {
        AppResponse appResponse =  AppResponse.success("修改成功！");
        int countIsbn = goodsDao.countIsbn(goodsInfo);
        if(0 != countIsbn) {
            return AppResponse.bizError("商品书号已存在，请重新输入！");
        }
        // 修改商品
        int count = goodsDao.updateGoods(goodsInfo);
        if(0 == count) {
            appResponse = AppResponse.bizError("修改失败，请重试！");
        }
        return appResponse;
    }

    /**
     * 查询商品详情
     * author:miaosongtian
     * time:2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse getGoods(String goodsId){
        GoodsInfo goodsInfo = goodsDao.getGoods(goodsId);
        return AppResponse.success("查询成功！",goodsInfo);
    }

    /**
     * 分页查询商品列表
     * author:miaosongtian
     * time:2020-03-29
     */
    public AppResponse listGoods(GoodsInfo goodsInfo) {
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfoList = goodsDao.listGoods(goodsInfo);
        // 包装Page对象
        PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     * 查询商品分类下拉框
     * author:miaosongtian
     * time:2020-4-9
     */
    public AppResponse listGoodsClassify(String classifyId) {
        GoodsClassifyVO goodsClassifyList = new GoodsClassifyVO();
        goodsClassifyList.setGoodsClassifyList(goodsDao.listGoodsClassify(classifyId));
        if (goodsClassifyList == null) {
            return AppResponse.success("查询数据为空，请重试！");
        }
        return AppResponse.success("查询成功！", goodsClassifyList);
    }

    /**
     * 修改商品状态
     * author:miaosongtian
     * time:2020-04-14
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoodsShelfState(GoodsState goodsState) {
        //把字符串通过逗号分成list
        List<String> listGoodsId = Arrays.asList(goodsState.getGoodsId().split(","));
        List<String> listVersion = Arrays.asList(goodsState.getVersion().split(","));
        List<String> listGoodsInventories = Arrays.asList(goodsState.getGoodsInventories().split(","));
        //定义一个变量用于存放listGoodsId,listVersion,listGoodsInventories
        List<Goods> listUpdata = new ArrayList<>();
        String goodsStateId = goodsState.getGoodsStateId();
        String userCode = goodsState.getLastModifiedBy();
        for (int i = 0;i < listGoodsId.size();i++){
            Goods goods = new Goods();
            goods.setGoodsStateId(goodsStateId);
            goods.setUserCode(userCode);
            goods.setVersion(listVersion.get(i));
            goods.setGoodsId(listGoodsId.get(i));
            goods.setGoodsInventories(listGoodsInventories.get(i));
            listUpdata.add(goods);
        }
        AppResponse appResponse = AppResponse.success("状态修改成功！");
        int count = goodsDao.updateGoodsShelfState(listUpdata);
        if(0 == count) {
            appResponse = AppResponse.bizError("状态修改失败，请重试！");
        }
        return appResponse;
    }
}
