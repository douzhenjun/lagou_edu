package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    /*返回持久层的用户列表对象添加分页功能*/
    @Override
    public PageInfo findAllUserByCondition(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());
        List<User> userList = userMapper.findAllUserByCondition(userVO);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return userPageInfo;
    }

    /*逻辑层登录, 是将用户传来的信息, 通过用户名查找用户, 确定是否存在, 若存在, 再对比密码是否相同*/
    /*verify参数, 第一个是待验证密码,还没有经过md5加密; 第二个是加密秘钥, 第三个是查到的用户的真实密码(经过md5加密过的)
    * 返回布尔类型*/
    @Override
    public User login(User user) {
        User user1 = userMapper.findUserByPhone(user);
        try {
            if(user1 != null && Md5.verify(user.getPassword(), "lagou", user1.getPassword())){
                return user1;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findRoleByUserId(int uid) {
        return userMapper.findRoleByUserId(uid);
    }

    /*为用户分配角色,和为角色分配菜单一样,首先清空用户角色中间表,然后遍历用户角色关系对象
    * 中的角色列表,为每个新建的用户角色关系表对象封装数据,执行新增操作*/
    @Override
    public void UserContextRole(UserRoleVo userRoleVo) {
        userMapper.deleteUserRoleRelation(userRoleVo.getUserId());
        for (Integer rid : userRoleVo.getRoleIdList()) {
            UserRoleRelation urr = new UserRoleRelation();
            urr.setRoleId(rid);
            urr.setUserId(userRoleVo.getUserId());
            Date date = new Date();
            urr.setCreatedTime(date);
            urr.setUpdatedTime(date);
            urr.setCreatedBy("system");
            urr.setUpdatedBy("system");
            userMapper.saveUserRoleRelation(urr);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer uid) {
        //1.根据用户id查询角色信息
        List<Role> roles = userMapper.findRoleByUserId(uid);
        //2.新建保存角色id的列表roleIds
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getId());
        }
        //3.根据角色id列表查询所有的父菜单信息
        List<Menu> pMenuList = userMapper.findParentMenuByRoleIds(roleIds);
        //4.遍历父菜单列表,获得子菜单信息
        for (Menu menu : pMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuList);
        }     
        //5.根据角色id列表查询所有的资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleIds(roleIds);
        //6.新建map对象封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", pMenuList);
        map.put("resourceList", resourceList);
        //6.返回ResponseResult对象
        return new ResponseResult(true, 1, "success", map);
    }
}
