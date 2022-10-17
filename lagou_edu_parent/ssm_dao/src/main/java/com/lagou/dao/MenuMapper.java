package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

/*菜单模块*/
public interface MenuMapper {
    
    /*查询所有的父菜单及其对应的子菜单(关联查询)*/
    List<Menu> findAllMenu(int pid);
    
    /*查询所有的父菜单及其对应的子菜单(嵌套查询)*/
    List<Menu> findAllMenu2(int pid);
    
    /*查询所有的菜单信息*/
    List<Menu> findMenuList();
    
    /*新增菜单*/
    void saveMenu(Menu menu);
    
    /*回显菜单信息*/
    Menu findMenuById(int mid);
    
    /*修改菜单*/
    void updateMenu(Menu menu);
}
