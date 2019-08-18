package com.itheima.spring_04;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
    @Test
    public void test(){
        //spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取人
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);


        Person person2 = (Person) applicationContext.getBean("person2");

        System.out.println("person2 = " + person2);

        Person person3 = (Person) applicationContext.getBean("person3");

        System.out.println("person3 = " + person3);
    }


}