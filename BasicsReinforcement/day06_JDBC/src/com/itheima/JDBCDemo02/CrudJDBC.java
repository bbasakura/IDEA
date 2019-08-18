package com.itheima.JDBCDemo02;

import org.junit.Test;

import java.sql.*;

//用java语句进行数据库的CRUD,用到了@Test,方便了测试，不用main（）。
public class CrudJDBC {
    @Test
    public void add() {
        Connection conn = null;
        Statement stmt = null;
//        1.注册驱动
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
//        2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql:///mydb2", "root", "root");
//        3.基本操作
            String sql = "insert into testday06 values(null,'全意','124',26)";
            stmt = conn.createStatement();
            int num = stmt.executeUpdate(sql);
            if (num > 0) {
                System.out.println("添加操作成功！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }

    @Test
    public void delet() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");

            conn = DriverManager.getConnection("jdbc:mysql:///mydb2", "root", "root");

            String sql = "delete from testday06 where id =6";//delete 怎么写？？？？？？？

            stmt = conn.createStatement();

            int i = stmt.executeUpdate(sql);

            if (i > 0) {
                System.out.println("删除操作成功！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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


    }

    @Test
    public void update() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///mydb2", "root", "root");
            String sql = "UPDATE  testday06 SET usename = '张三' WHERE  id =5"; //增删改查的sql怎么写？？？？？？？？
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            if (i > 0) {
                System.out.println("修改数据成功！！");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    }
}


