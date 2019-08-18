package com.itheima.utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
//得到JDbctemplate对象，操作数据库，
public class JdbcTempUtils {
    private static  DataSource ds = new ComboPooledDataSource();
    public static JdbcTemplate getJdbcT(){
        //得到的是一个jdbcTemplate对象，相当于之前的Stmt 和pstmt,
//        他一端连接的是连接池的父类接口，ds,
        return  new JdbcTemplate(ds);
    }

}
