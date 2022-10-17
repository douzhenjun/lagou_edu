package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    
    /*查找所有资源分类*/
    List<ResourceCategory> findAllResourceCategory();
}
