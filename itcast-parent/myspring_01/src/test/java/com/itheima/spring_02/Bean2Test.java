package com.itheima.spring_02;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean2Test {

    @Test
     public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Bean2 bean2 = (Bean2) applicationContext.getBean("bean2");

        System.out.println("bean2 = " + bean2);
    }

}