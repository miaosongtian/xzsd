package com.xzsd.pc.menu.dao;

import com.xzsd.pc.menu.entity.MenuInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {
    /**
     * 新增菜单
     */
    int addMenu(MenuInfo menuInfo);
    /**
     * 删除菜单
     */
    int deleteMenu(@Param("menuId") String menuId, @Param("userId") String userId);
    /**
     * 修改菜单
     */
    int updateMenu(MenuInfo menuInfo);
    /**
     * 查询商品分类详情
     */
    MenuInfo getMenu(@Param("menuId") String menuId);
    /**
     * 查询商品分类列表（查全部）
     */
    List<MenuInfo> listMenu();
    /**
     * 查询商品分类列表（根据角色查）
     */
    List<MenuInfo> listMenuHome(@Param("role") String role);
}
