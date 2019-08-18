package com.itheima.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

public class OrderMapperTest {
    private OrderMapper orderMapper ;
    SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();

        //此处的空指针异常是因为！！！！！！！最上面的一个userMapper和此处的是同一个！！
//        你在此处的在创建一个新的 是不是傻傻~~~~~~~~~·
        orderMapper = sqlSession.getMapper(OrderMapper.class);
    }


//    根据订单号查询订单信息，并且查询出下单人信息
    @Test
    public void queryOrderWithUser() {
        System.out.println(orderMapper.queryOrderWithUser("20140921001"));
    }


    @Test
    public void queryOrderWithUserDetail() {
        System.out.println(orderMapper.queryOrderWithUserDetail("20140921001"));
    }

    @Test
    public void queryOrderWithUserDetailItem() {
        System.out.println(orderMapper.queryOrderWithUserDetailItem("20140921001"));
    }
}