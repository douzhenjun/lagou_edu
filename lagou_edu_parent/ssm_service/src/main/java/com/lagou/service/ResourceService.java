package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResourceVo;

public interface ResourceService {
    
    /*分页条件查询资源*/
    PageInfo findAllResourceByPage(ResourceVo resourceVo);
}
