package com.itheima.C3p0DEmo;

import com.itheima.JDBCUtils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3p0Demo {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql:///day07");
        cpds.setUser("root");
        cpds.setPassword("root");
        cpds.setInitialPoolSize(5);
        cpds.setMaxPoolSize(15);
        cpds.setMinPoolSize(10);
        cpds.setCheckoutTimeout(1000);
        //通过代理获得以一个连接对象
        Connection conn = cpds.getConnection();
        //测试连接 System.out.println(conn);
        //拿到conn，为所欲为
        //基本操作
        String sql = "select * from user";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("usename"));
        }
        //归还资源，池化技术，关联线程池工厂
        JDBCUtils.release(rs,pstmt,conn);
    }
}

