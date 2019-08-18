package com.itheima.jdbcTemplate;

import com.itheima.JDBCUtils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class jdbcTemplate {
    public static void main(String[] args) throws Exception{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        jdbcTemplate.execute("create table employee(" +
                "id int primary key auto_increment," +
                "name varchar(20) not null," +
                "gender varchar(2)," +
                "birthday date);");
    }
}
