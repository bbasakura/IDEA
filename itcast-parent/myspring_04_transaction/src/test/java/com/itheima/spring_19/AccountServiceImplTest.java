package com.itheima.spring_19;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")

public class AccountServiceImplTest {

   /* @Autowired

    private IAccountService accountService;

    @Test
    public void test() {

        accountService.transfer("jack","rose",1200d);
    }*/

    //注入测试的service
    @Autowired
    private IAccountService accountService;

    //需求：账号转账，Tom账号取出1000元，存放到Jack账号上
    @Test
    public void testTransfer() {
        accountService.transfer("Tom", "Jack", 1000d);
        System.out.println("转账成功！");

    }
}
