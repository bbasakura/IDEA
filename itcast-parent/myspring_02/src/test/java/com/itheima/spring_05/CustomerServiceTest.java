package com.itheima.spring_05;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerServiceTest {
    @Test
    public void test(){


        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

        CustomerService bean = (CustomerService) ac.getBean("customerService");

        bean.save();




    }

}