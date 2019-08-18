package com.itheima.spring_02;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bean3Test {



    @Test
    public  void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");


        Bean3 bean3 = (Bean3) applicationContext.getBean("bean3");

        System.out.println("bean3 = " + bean3);

    }

}