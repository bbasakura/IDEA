package com.itheima.reflect;

import java.lang.reflect.Constructor;

public class ReflectCons {
    public static void main(String[] args) throws Exception {
        Class<?> c1 = Class.forName("com.itheima.reflect.Student");
        //参数列表的对应问题
     /*   步骤
        1. 获取字节码对象
        2. 获取对应的构造方法对象 -- getConstructor() 获取具体的某个构造方法
        3. 使用构造方法创建对象 -- newInstance() 创建对象  初始化参数
*/
        //公共带参构造
        Constructor<?> c2 = c1.getDeclaredConstructor(String.class);
        Student s = (Student) c2.newInstance("张三");
        System.out.println(s.getName());
        System.out.println("===================");


        Constructor<?> c3 = c1.getDeclaredConstructor(String.class,int.class);
        c3.setAccessible(true);
        Student s3 = (Student) c3.newInstance("张三", 25);
        System.out.println(s3.getName() + "---" + s3.getAge());

    }
}
