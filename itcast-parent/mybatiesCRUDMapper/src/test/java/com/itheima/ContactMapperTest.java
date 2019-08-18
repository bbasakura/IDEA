package com.itheima;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ContactMapperTest {

    private ContactMapper contactMapper;
    SqlSession sqlSession;
        @Before
        public void setUp () throws Exception {

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);

            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            sqlSession = sqlSessionFactory.openSession();

            contactMapper = sqlSession.getMapper(ContactMapper.class);
        }

        @Test
        public void queryContactById () {
            ContactInfo contactInfo= contactMapper.queryContactById(2);
            System.out.println(contactInfo);
        }

    @Test
    public void queryContactAll() {
        List<ContactInfo> list =contactMapper.queryContactAll();
        for (ContactInfo contactInfo : list) {
            System.out.println(contactInfo);
        }
    }

    @Test
    public void insertContact() {
    }

    @Test
    public void updateContact() {
    }

    @Test
    public void deleteContactById() {
            contactMapper.deleteContactById(44);

    }
}
