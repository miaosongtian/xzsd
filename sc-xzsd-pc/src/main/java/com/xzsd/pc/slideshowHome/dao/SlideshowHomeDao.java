package com.xzsd.pc.slideshowHome.dao;

import com.xzsd.pc.goods.entity.GoodsInfo;
import com.xzsd.pc.slideshowHome.entity.SlideshowHome;
import com.xzsd.pc.slideshowHome.entity.SlideshowHomeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SlideshowHomeDao {
    /**
     * 新增轮播图
     */
    int addSlideshowHome(SlideshowHomeInfo slideshowHomeInfo);
    /**
     * 判断轮播图商品是否存在
     */
    int countGoodsId(SlideshowHomeInfo slideshowHomeInfo);
    /**
     * 判断轮播图序号是否存在
     */
    int countSort(SlideshowHomeInfo slideshowHomeInfo);
    /**
     * 删除轮播图
     */
    int deleteSlideshowHome(@Param("listId") List<String> listId, @Param("userCode") String userCode);
    /**
     * 分页查询轮播图
     */
    List<SlideshowHomeInfo> listSlideshowHome(SlideshowHomeInfo goodsInfo);
    /**
     * 分页查询商品列表
     */
    List<GoodsInfo> listGoods(GoodsInfo goodsInfo);
    /**
     * 修改录播图状态
     */
    int updateSlideshowHomeState(@Param("listUpdata") List<SlideshowHome> listUpdata);
}
