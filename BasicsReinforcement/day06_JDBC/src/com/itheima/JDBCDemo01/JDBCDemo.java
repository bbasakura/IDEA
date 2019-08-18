package com.itheima.JDBCDemo01;

import java.sql.*;
//查询mydb2数据库中的testday06表

public class JDBCDemo {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //1.注册驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///mydb2", "root", "root");
            //3.基本操作
            String sql = "select * from testday06";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //按照位置获取内容
                int id = rs.getInt(1);
                String usename = rs.getString(2);
                String password = rs.getString(3);
                int age = rs.getInt(4);
                System.out.println(id + "---" + usename + "---" + password + "---" + age);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        //4.释放资源（就近原则）


    }
}
