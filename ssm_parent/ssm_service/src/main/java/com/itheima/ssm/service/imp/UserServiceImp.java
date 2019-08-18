package com.itheima.ssm.service.imp;


import com.itheima.ssm.mapper.UserMapper;
import com.itheima.ssm.pojo.User;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImp implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User fiundUserById(long id) {

        return userMapper.findUserById(id);
    }

    @Override
    public void deleteUserById(long id) {
        userMapper.deleteUserById(id);


    }
}
