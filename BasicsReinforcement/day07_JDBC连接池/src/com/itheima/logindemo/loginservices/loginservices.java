package com.itheima.logindemo.loginservices;

import com.itheima.jdbcTemplate.User;
import com.itheima.logindemo.loginDao.loginDao;

public class loginservices {


    public static User login(String usename, String password) {
        loginDao ld = new loginDao();
        User user = ld.login(usename, password);
        return user;
    }
}
