package com.itheima.JDBCUtils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private JDBCUtils() {
    }
//
    private static final String DRIVER_CLASS;
    private static final String URL;
    private static final String PASSWORD;
    private static final String USER;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//
        DRIVER_CLASS = properties.getProperty("driverclass");
        URL = properties.getProperty("url");
        USER = properties.getProperty("user");
        PASSWORD = properties.getProperty("password");
    }

    //1.注册驱动
    public static void loadDriver() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.获取链接
    public static Connection getConnection() {
        loadDriver();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //4.释放资源
    //增删改
    public static void release(Statement stmt, Connection conn) {
        if (stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //查的（重载）
    public static void release(ResultSet rs,Statement stmt,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        release(stmt,conn);
    }

}