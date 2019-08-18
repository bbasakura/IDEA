package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 例：查询男性用户，如果输入了用户名，按用户名模糊查询
     *
     * @param userName
     */

    public List<User> queryUserListLikeUserName(@Param("userName") String userName);

    /**
     * 查询男性用户，如果输入了用户名则按照用户名模糊查找，否则如果输入了年龄则按照年龄查找，
     * 否则查找用户名为“zhangsan”的用户
     *
     * @param userName
     * @param age
     * @return
     */
    public List<User> queryUserListLikeUserNameOrAge(@Param("userName") String userName, @Param("age") Integer age);


    /**
     * 查询所有用户，如果输入了用户名按照用户名进行模糊查询，如果输入年龄，按照
     * 年龄进行查询，如果两者都输入，两个条件都要成立。
     *
     * @param userName
     * @param age
     * @return
     */
    public List<User> queryUserListLikeUserNameAndAge(@Param("userName")String userName, @Param("age")Integer age);

    /**
     * 修改用户信息，如果参数user中的某个属性为null，则不修改。
     * @param user
     */
    public void updateUserSelective(User user);


    /**
     * 根据多个id查询用户信息
     * @param ids
     * @return
     */
    public List<User> queryUserListByIds(@Param("ids")Long[] ids);

}


