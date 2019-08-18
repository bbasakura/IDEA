package com.itheima.logindemo.loginweb;

import com.itheima.jdbcTemplate.User;
import com.itheima.logindemo.loginservices.loginservices;

import java.util.Scanner;

public class loginweb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入账号：");
        String usename = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();
        loginservices ls = new loginservices();
        User user = ls.login(usename, password);
        if (user != null) {
            System.out.println(usename + "欢迎访问");
        } else {
            System.out.println("访问失败");
        }
    }
}
