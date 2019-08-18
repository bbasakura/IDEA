package com.itheima.ssm.service;

import com.itheima.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface IUserService {

    public User fiundUserById(@Param("id") long id);

    void deleteUserById(long id);
}
