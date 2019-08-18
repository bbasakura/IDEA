package com.itheima.SQlLogin;

import com.itheima.JDBCUtils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class prepareLogin {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请您输入账号:");
        String use = sc.nextLine();
        System.out.println("请您输入密码:");
        String pass = sc.nextLine();
//        1.获取链接
        Connection conn = JDBCUtils.getConnection();
//        2.基本操作
//        将需要的参数用？占位符代替
        String sql = "select * from testday06 where usename = ? and password = ?";
//        预编译参数
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        设置参数，就是设置占位符的值
        pstmt.setString(1,use);//本身就是一个字符串，就别带双引号了，服了。。。
        pstmt.setString(2,pass);
//        执行语句
        ResultSet rs = pstmt.executeQuery();
        //通过结果判断是否登陆成功
        if(rs.next()){
            System.out.println(rs.getString("usename") + ":欢迎您访问阿里巴巴!");
        }else{
            System.out.println("没钱上什么淘宝!!!");
        }
        JDBCUtils.release(rs,pstmt,conn);
    }
}
