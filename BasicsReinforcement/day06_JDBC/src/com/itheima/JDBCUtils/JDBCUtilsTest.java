package com.itheima.JDBCUtils;

import java.sql.Connection;
import java.sql.Statement;
public class JDBCUtilsTest {
    public static void main(String[] args) {
        //测试工具类
        Connection conn = JDBCUtils.getConnection();
        Statement stmt = null;
        String sql = "update testday06 set usename='旺财' where id = 4";
        try {


            stmt = conn.createStatement();
            int num = stmt.executeUpdate(sql);
            if (num > 0) {
                System.out.println("增加操作成功！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(stmt, conn);
        }
    }
}
