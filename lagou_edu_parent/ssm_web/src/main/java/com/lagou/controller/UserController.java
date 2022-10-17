package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /*分页查询用户*/
    @RequestMapping("findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo<User> userPageInfo = userService.findAllUserByCondition(userVO);
        Map<String, Object> content = new HashMap<>();
        content.put("pageNum", userPageInfo.getPageNum());
        content.put("pageSize", userPageInfo.getPageSize());
        content.put("list", userPageInfo.getList());
        ResponseResult responseResult = new ResponseResult(content);
        return responseResult;
    }
    
    /*用户登录*/
    @RequestMapping("login")
    public ResponseResult login(User user, HttpServletRequest request){
        User user1 = userService.login(user);
        //判断返回值是否为null,是则登录失败,否则登录成功
        if(user1 != null){
            Map<String, Object> map = new HashMap<>();
            String access_token = UUID.randomUUID().toString();
            map.put("access_token", access_token);
            map.put("user_id", user1.getId());

            HttpSession session = request.getSession();
            session.setAttribute("user_id", user1.getId());
            session.setAttribute("access_token", access_token);
            return new ResponseResult(true, 1, "响应成功", map);
        }else{
            return new ResponseResult(true, 1, "用户名密码错误", null);
        }
    }
    
    /*分配角色(回显)*/
    @RequestMapping("findRoleByUserId")
    public ResponseResult findRoleByUserId(int id){
        List<Role> roleList = userService.findRoleByUserId(id);
        return new ResponseResult(roleList);
    }
    
    /*分配角色*/
    @RequestMapping("userContextRole")
    public ResponseResult userContextRole(@RequestBody UserRoleVo userRoleVo){
        userService.UserContextRole(userRoleVo);
        return new ResponseResult(null);
    }
    
    /*获取用户权限,进行菜单动态展示*/
    @RequestMapping("getUserPermissons")
    public ResponseResult getUserPermissons(HttpServletRequest request){
        //1.获取请求头的token
        String headerToken = request.getHeader("Authorization");
        //2.获取session的token
        String sessionToken = (String)request.getSession().getAttribute("access_token");
        //3.判断token是否一致
        if(headerToken.equals(sessionToken)){
            //若一致,调用服务层同名方法,返回封装的结果
            Integer userId = (Integer)request.getSession().getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(userId);
            return responseResult;
        }else{
            ResponseResult responseResult = new ResponseResult(false, 400, "获取菜单信息失败", null);
            return responseResult;
        }
    }
}
