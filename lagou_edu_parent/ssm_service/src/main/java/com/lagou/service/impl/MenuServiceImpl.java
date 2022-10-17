package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    
    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public List<Menu> findAllMenu(int pid) {
        return menuMapper.findAllMenu(pid);
//        return menuMapper.findAllMenu2(pid);
    }

    @Override
    public PageInfo findMenuList() {
        PageHelper.startPage(1, 5);
        List<Menu> menuList =  menuMapper.findMenuList();
        PageInfo<Menu> menuPageInfo = new PageInfo<>(menuList);
        return menuPageInfo;
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    @Override
    public Menu findMenuById(int mid) {
        return menuMapper.findMenuById(mid);
    }

    @Override
    public void updateMenu(Menu menu) {
        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setUpdatedBy("system");
        menuMapper.updateMenu(menu);
    }
}
