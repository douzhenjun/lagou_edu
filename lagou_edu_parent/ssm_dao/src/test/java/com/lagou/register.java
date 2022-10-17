package com.lagou;

import com.lagou.dao.UserMapper;
import com.lagou.domain.User;
import com.lagou.utils.Md5;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)//②使用spring创建测试对象
@ContextConfiguration("classpath:applicationContext_dao.xml")//③指定配置文件
public class register {
    
    @Autowired
    private UserMapper userMapper;

    @Test
    public void register(){
        User user = new User();
        user.setName("tom");
        user.setPhone("12312311231");
        String password = "123456";
        try {
            String password2 = Md5.md5(password, "lagou");
            user.setPassword(password2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);
        user.setStatus("0");
        user.setIs_del(0);
        userMapper.saveUser(user);
    }
    
}
