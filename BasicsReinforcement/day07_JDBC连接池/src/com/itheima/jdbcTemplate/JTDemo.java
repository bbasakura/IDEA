package com.itheima.jdbcTemplate;

import com.itheima.JDBCUtils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class JTDemo {
    @Test//测试要什么static
    public void add() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into user values(null,?,?,?)";
        int num = jdbcTemplate.update(sql, "李四", "123", 18);
        if (num > 0) {
            System.out.println("添加成功");
        }
    }

    @Test
    public void delete() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

        String sql = "delete from user where id= ?";
        int i = jdbcTemplate.update(sql, 5);
        if (i > 0) {
            System.out.println("删除成功");

        }
    }

    @Test
    public void update() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update  user set usename = ? where id = ?";
        //update employee set name = ? where id = ?", "旺财", 1///sql语句语句语句、、、、
        int i = jdbcTemplate.update(sql, "小刘", 1);     if (i > 0) {
            System.out.println("修改成功");
        }
    }
//    1.查询一条数据存放至map
    @Test
    public  void s1(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
        Map<String, Object> map = jdbcT.queryForMap("select * from  user where id= ?", 1);
        System.out.println(map);
    }
//    2.查询一条记录封装成bean(对象)
    @Test
    public   void s2(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();

        User user = jdbcT.queryForObject("select * from user where id =? ",new BeanPropertyRowMapper<User>(User.class),1);
        System.out.println(user);
    }
//    3.查询多条记录封装List<Bean>（星星星）
    @Test
    public void  s3(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
        List<User> list = jdbcT.query("select * from user", new BeanPropertyRowMapper<>(User.class));
        System.out.println(list);
    }
//    4.查询一列数据
    @Test
    public  void s4(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
        List<String> list = jdbcT.queryForList("select usename from user", String.class);
        System.out.println(list);
        List<String> ps = jdbcT.queryForList("SELECT  password FROM USER ", String.class);

        System.out.println(ps);
    }
//    5.查询多列数据封装成List
    @Test
    public  void s5(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
        List<Map<String, Object>> list = jdbcT.queryForList("SELECT usename,password,age FROM USER ");
        System.out.println(list);
    }
    //    6.查询所有数据封装成List
    @Test
    public  void s6(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
        List<Map<String, Object>> list = jdbcT.queryForList("SELECT * FROM USER ");
        System.out.println(list);
    }
//    7.查询一共有多少个用户

    @Test
    public  void  s7(){
        JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
        Integer num = jdbcT.queryForObject("select count(*) from user", int.class);
        System.out.println("共有"+num+"个用户");
    }
}
