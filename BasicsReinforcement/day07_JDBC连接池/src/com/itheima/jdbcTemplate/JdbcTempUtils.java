package com.itheima.jdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcTempUtils {
    private static final DataSource ds = new ComboPooledDataSource();
    public static JdbcTemplate getJdbcT(){
        //得到的是一个jdbcTemplate对象，相当于之前的Stmt 和pstmt,
//        他一端连接的是连接池的父类接口，ds,
        return  new JdbcTemplate(ds);
    }
}
