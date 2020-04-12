package com.xzsd.pc.goods.service;



import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.xzsd.pc.goods.dao.GoodsDao;
import com.xzsd.pc.goods.entity.GoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        int countBookNum = goodsDao.countBookNum(goodsInfo);
        if(0 != countBookNum) {
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
        List<String> listId = Arrays.asList(goodsId.split(","));
        AppResponse appResponse = AppResponse.success("删除成功！");
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
        int countBookNum = goodsDao.countBookNum(goodsInfo);
        if(0 != countBookNum) {
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
    public AppResponse listGoodsClassify(String classfyId) {
        List<String> goodsClassifyList = goodsDao.listGoodsClassify(classfyId);
        return AppResponse.success("查询成功！", goodsClassifyList);
    }
}
