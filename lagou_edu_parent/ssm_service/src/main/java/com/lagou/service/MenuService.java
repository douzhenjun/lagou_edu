package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {
    //查询所有的菜单及其关联的子菜单(包含嵌套查询和关联查询两种情况)
    List<Menu> findAllMenu(int pid);
    
    //查询所有菜单信息
    PageInfo findMenuList();
    
    /*新增菜单*/
    void saveMenu(Menu menu);
    
    /*根据id查询菜单*/
    Menu findMenuById(int mid);
    
    /*修改菜单*/
    void updateMenu(Menu menu);
}
