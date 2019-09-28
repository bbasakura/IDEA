package cn.itcast.b2c.gciantispider.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.b2c.gciantispider.dao.IUserDao;
import cn.itcast.b2c.gciantispider.model.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

    @Override
    public List<User> queryUserInfo(String id) {
        List<User> users  = super.find(id);
        return users;
    }

}
