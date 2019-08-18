package com.itheima.ssm.service.imp;

import com.itheima.ssm.service.IUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class UserServiceImpTest {


    @Autowired
    private IUserService userService;

//    @Test
//    public void fiundUserById() {
//
//        System.out.println(userService.fiundUserById(1l));
//    }
}