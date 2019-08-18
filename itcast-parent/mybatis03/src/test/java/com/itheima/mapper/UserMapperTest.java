package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserMapperTest {
    private UserMapper userMapper;
    SqlSession sqlSession;

    @Before
    public void setUp() throws Exception {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();

        //此处的空指针异常是因为！！！！！！！最上面的一个userMapper和此处的是同一个！！
//        你在此处的在创建一个新的 是不是傻傻~~~~~~~~~·
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void queryUserListLikeUserName() {
        List<User> users = userMapper.queryUserListLikeUserName("l");
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void queryUserListLikeUserNameOrAge() {
        List<User> users = userMapper.queryUserListLikeUserNameOrAge("", null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void queryUserListLikeUserNameAndAge() {
        List<User> users = this.userMapper.queryUserListLikeUserNameAndAge(null, 30);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void updateUserSelective() {
        User user = new User();
        user.setAge(18);
        user.setName("柳岩");
        user.setPassword("123456");
        user.setUserName("yanyan2");
//		user.setSex(3);
        user.setBirthday(new Date());
        user.setId(7l);
        userMapper.updateUserSelective(user);

    }

    //根据多个id查询用户信息
    @Test
    public void queryUserListByIds() {
        List<User> users = userMapper.queryUserListByIds(new Long[]{1l, 2l, 3l, 4l});
        for (User user : users) {
            System.out.println(user);
        }
    }
}