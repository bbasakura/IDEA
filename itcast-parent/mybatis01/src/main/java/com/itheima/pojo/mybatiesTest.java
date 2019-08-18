package com.itheima.pojo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class mybatiesTest {
    public static void main(String[] args) {

        SqlSession sqlSession = null;

        String resource = "mybatis-config.xml";

        try {
            InputStream stream = Resources.getResourceAsStream(resource);


            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(stream);

            sqlSession = factory.openSession();

            ContactInfo contactInfo = sqlSession.selectOne("UserMapper.queryUserById", 1L);

            System.out.println(contactInfo);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            if (sqlSession != null) {
                sqlSession.close();

            }
        }
    }
}