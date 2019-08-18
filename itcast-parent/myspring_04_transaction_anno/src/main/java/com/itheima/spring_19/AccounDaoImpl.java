package com.itheima.spring_19;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


@Repository("accountDao")
public class AccounDaoImpl extends JdbcDaoSupport implements IAccountDao {


    //当初始化dao的时候，会调用该方法啊，通过set方法的形参注入数据源
    //方法名无所谓
    @Autowired
    public void setSuperDataSource(DataSource dataSource){
        //调用父类的方法
        super.setDataSource(dataSource);
    }


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
