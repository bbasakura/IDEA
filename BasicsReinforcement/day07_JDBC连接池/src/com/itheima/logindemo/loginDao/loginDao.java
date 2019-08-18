package com.itheima.logindemo.loginDao;

import com.itheima.jdbcTemplate.JdbcTempUtils;
import com.itheima.jdbcTemplate.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class loginDao {
    public User login(String usename, String password) {

        User user = null;
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();

        try {
            user = jdbcT.queryForObject("select * from user where usename= ? and password = ?", new BeanPropertyRowMapper<>(User.class), usename, password);
        } catch (DataAccessException e) {
        }
        return user;
    }
}
