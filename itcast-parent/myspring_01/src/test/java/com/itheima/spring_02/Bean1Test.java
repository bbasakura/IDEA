package com.itheima.spring_02;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean1Test {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Bean1 bean1 = (Bean1) applicationContext.getBean("bean1");
        System.out.println("bean1 = " + bean1);
    }

}