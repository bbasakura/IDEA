package com.itheima.spring_19;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AccounDaoImpl extends JdbcDaoSupport implements IAccountDao {
    @Override
    public void in(String name, Double money) {

        String sql = "update account set money=money+? where name =?";
        super.getJdbcTemplate().update(sql, money, name);

    }

    @Override
    public void out(String name, Double money) {
        String sql = "update account set money=money-? where name =?";
        super.getJdbcTemplate().update(sql, money, name);
    }
}
