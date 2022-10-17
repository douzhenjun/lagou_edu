package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

/*资源模块*/
public interface ResourceMapper {
    
    /*分页条件查询资源*/
    List<Resource> findAllResourceByPage(ResourceVo resourceVo);
}
