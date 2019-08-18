package com.itheima.BeanUtils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;

public class BeanUtilsDemo {
    public static void main(String[] args) throws Exception  {

        //创建对象，
        //学生类中一定要有getset方法！！！！
        //在设置属性的时候是一个一个设置
        Student student = new Student();
        BeanUtils.setProperty(student , "name","张三");
        BeanUtils.setProperty(student ,"age",18);
        String   age = BeanUtils.getProperty(student,"age");
        String   name = BeanUtils.getProperty(student,"name");
        System.out.println(name+"---"+age);
        //以上的两种方法不常用
        //最主要的是下面的这种方法
        //map集合一般是传递过来的，不是我们自己生成的
        Map<String, Object> map = new HashMap<>();
        //封装对象的属性，各种属性
        map.put("name","王五");
        map.put(age,83);
        //创建对象，
        Student student1 = new Student();
        //调用工具类中的populate方法。传入的参数是对象和集合
        BeanUtils.populate(student1,map);
        System.out.println(student1);
    }

}
