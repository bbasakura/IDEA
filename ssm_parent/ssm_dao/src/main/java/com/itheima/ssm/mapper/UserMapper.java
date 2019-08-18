package com.itheima.ssm.mapper;

import com.itheima.ssm.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**根据ID查询用户
     * @param id
     * @return
     */
    public User findUserById(@Param("id") long id);


    /**
     * @param id
     */
    public void deleteUserById(@Param("id") long id);

}
