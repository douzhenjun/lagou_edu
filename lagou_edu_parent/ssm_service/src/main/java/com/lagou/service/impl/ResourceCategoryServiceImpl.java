package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;
    
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    @Override
    public void deleteResourceCategory(int id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }

    @Override
    public List<ResourceCategory> findAllResourceCategoryByRoleId(int id) {
        return resourceCategoryMapper.findAllResourceCategoryByRoleId(id);
    }
}
