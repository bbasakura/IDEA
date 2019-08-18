package com.itheima.dao;

import com.itheima.Utils.JdbcTempUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDao {
    JdbcTemplate jdbcT = JdbcTempUtils.getJdbcT();
    String sql = "select * from t_user";

    //3. 使用JdbcTemplate对象的queryForList()方法查询结果
    public List<Map<String, Object>> queryAll() {
        List<Map<String, Object>> maps = jdbcT.queryForList(sql);
        return maps;
    }

}
