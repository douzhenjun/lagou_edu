package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.RoleResourceVo;

public interface ResourceService {
    
    /*分页条件查询资源*/
    PageInfo findAllResourceByPage(ResourceVo resourceVo);
    
    /*为角色分配资源*/
    void roleContextResource(RoleResourceVo roleResourceVo);
}
