package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {
    
    /*分页多条件查询用户*/
    PageInfo findAllUserByCondition(UserVO userVO);
    
    /*用户登录*/
    User login(User user);
    
    /*分配角色时的数据回显*/
    List<Role> findRoleByUserId(int uid);
    
    /*根据用户分配角色*/
    void UserContextRole(UserRoleVo userRoleVo);
    
    /*动态获取资源信息并返回*/
    ResponseResult getUserPermissions(Integer uid);
}
