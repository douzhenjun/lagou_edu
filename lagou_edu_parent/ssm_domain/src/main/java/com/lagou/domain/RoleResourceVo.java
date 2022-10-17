package com.lagou.domain;

import java.util.List;

/*角色资源前端对象, 分配时使用*/
public class RoleResourceVo {
    
    private Integer roleId;
    
    private List<Integer> resourceIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
