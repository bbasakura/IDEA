package com.itheima.spring_01;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceImpTest {

    @Test
    public void loigin() {


        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //第一种反方法获得Bean对象--------通过唯一的ID
        IUserService serviceImp1 = (IUserService) applicationContext.getBean("userServiceImp");
        //获得Bean对象的第二种方法---------通过对象的类名，
        //如果是单类单对象----不会报错
//        如果是一个类中有多个对象，会报错
        IUserService serviceImp2 = (IUserService) applicationContext.getBean(UserServiceImp.class);

        serviceImp1.loigin();
        serviceImp2.loigin();
    }
}

