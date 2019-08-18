package com.itheima.loginDemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Scanner;

public class LoginTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        List<User> users = UserDao.getUserList();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String inputname = sc.next();
        System.out.println("请输入密码");
        String inputpassword = sc.next();
        User u = null;
        for (User useron : users) {
            if (useron.getUsername().equals(inputname) && useron.getPassword().equals(inputpassword))
                u = useron;
        }
        if (u != null) {
            System.out.println(u.getNickname() + "欢迎登陆QQ空间");

            功能:
            while (true) {
                System.out.println("请选择功能，look：查看空间，photo：访问照片，plu:发表说说，message：留言，exit:退出");
                String choose = sc.next();
                QQqFuction qq = new QQqFuction();
                if (choose.equals("exit")) {
                    break 功能;
                }
                try {
                    Class<?> aClass = Class.forName("com.itheima.loginDemo.QQqFuction");
                    Object o = aClass.newInstance();
                    Method method = aClass.getMethod(choose);
                    if (method.isAnnotationPresent(Jurisdiction.class)) {
//                          1.获取方法上注解的
                        Jurisdiction anno = method.getAnnotation(Jurisdiction.class);
//                          2.获取注解的value值(权限)
                        String value = anno.value();
//                           3.与User对象具备的权限进行对比
                         if (u.getList().contains(value)) {
                            method.invoke(qq);
                        } else {
                            System.out.println("你已经被拉黑!!");
                        }
                    }
                } catch (NoSuchMethodException e) {
                    System.out.println("没有对应的功能");
                }
            }
        } else {
            System.out.println("你不存在此服务器上");
        }
    }
}


