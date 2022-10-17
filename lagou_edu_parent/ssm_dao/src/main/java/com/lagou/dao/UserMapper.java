package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

/*用户模块*/
public interface UserMapper {
    
    /*用户条件查询*/
    List<User> findAllUserByCondition(UserVO userVO);
    
    /*新增用户*/
    void saveUser(User user);
    
    /*根据电话号码获得用户信息*/
    User findUserByPhone(User user);
    
    /*根据用户id查询对应的角色信息*/
    List<Role> findRoleByUserId(int uid);
    
    /*根据用户id清空中间表(user_role_relation)*/
    void deleteUserRoleRelation(int uid);
    
    /*新增中间表(user_role_relation)*/
    void saveUserRoleRelation(UserRoleRelation userRoleRelation);
    
    /*根据角色id列表查询对应的父菜单信息*/
    List<Menu> findParentMenuByRoleIds(List<Integer> rids);
    
    /*根据父菜单id查询所有的子菜单信息*/
    List<Menu> findSubMenuByPid(int pid);
    
    /*获取用户的资源权限信息*/
    List<Resource> findResourceByRoleIds(List<Integer> rids);
    
}
