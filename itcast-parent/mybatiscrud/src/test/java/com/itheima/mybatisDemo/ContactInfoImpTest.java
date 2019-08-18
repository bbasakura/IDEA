package com.itheima.mybatisDemo;

import com.itheima.pojo.ContactInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class ContactInfoImpTest {
    private ContactInfoIDao contactInfoIDao;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 构建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 初始化ContactInfoDao
        contactInfoIDao= new ContactInfoImp(sqlSession);
    }

    @Test
    //测试方法不用返回值，直接输出就可以了！！！
    public void  queryContactBYId() {
        ContactInfo contactInfo = contactInfoIDao.queryContactById(5);
        System.out.println(contactInfo);
    }
    @Test
    public void queryContactAll() {
        List<ContactInfo> contactInfos = contactInfoIDao.queryContactAll();
        for (ContactInfo contactInfo : contactInfos) {
            System.out.println(contactInfo);
        }
    }
    @Test
    public void insertContact() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setName("haha");
        contactInfo .setBirthday(1982-12-12) ;
        contactInfo.setBirthplace("普陀山");
        contactInfo.setEmail("122121212");
        contactInfo.setGender("m");
        contactInfo.setMobile("199999999999");
        contactInfoIDao.insertContact(contactInfo);
    }

    @Test
    public void updateContact() {
        ContactInfo contactInfo = contactInfoIDao.queryContactById(44);
        contactInfo.setName("哈哈哈");
        contactInfoIDao.updateContact(contactInfo);

    }

    @Test
    public void deleteContactById() {
        contactInfoIDao.deleteContactById(1);
    }
}