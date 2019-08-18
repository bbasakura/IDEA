package com.itheima.ssm.mapper;

import com.itheima.ssm.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml", "classpath:spring/applicationContext-mybatis.xml"})

public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectUSerById() {
        User user = userMapper.findUserById(3l);
        System.out.println("user = " + user);

    }
}