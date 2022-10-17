package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.RoleResourceRelation;
import com.lagou.domain.RoleResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public PageInfo findAllResourceByPage(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVo);
        PageInfo<Resource> resourcePageInfo = new PageInfo<>(resourceList);
        return resourcePageInfo;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {
        resourceMapper.deleteRoleResource(roleResourceVo);
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            Date date = new Date();
            roleResourceRelation.setUpdatedTime(date);
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedBy("system");
            roleResourceRelation.setCreatedBy("system");
            resourceMapper.saveRoleResource(roleResourceRelation);
        }
    }
}
