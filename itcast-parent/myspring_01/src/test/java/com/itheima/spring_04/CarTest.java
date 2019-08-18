package com.itheima.spring_04;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Car car = (Car) applicationContext.getBean("car");

        System.out.println("car = " + car);


    }


}