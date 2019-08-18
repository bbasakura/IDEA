package com.itheima.service;

import com.itheima.dao.UserDao;
import java.util.List;
import java.util.Map;


public class UserService {
    private UserDao userDao = new UserDao();
    public List<Map<String, Object>> queryAll() {
        return userDao.queryAll();
    }
}
