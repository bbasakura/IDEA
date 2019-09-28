package cn.itcast.b2c.gciantispider.service;

import java.util.List;
import java.util.Map;

import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.User;

public interface IUserService {

    public List<User> queryUserInfo(String id);

    /**
     * 根据id查询用户
     * 
     * @param id
     * @return
     */
    public User queryById(String id);

    /**
     * 保存用户
     * 
     * @param user
     */
    public void update(User user);

    /**
     * 根据用户姓名查找用户
     * 
     * @param name
     */
    public List<Map<String, Object>> searchUser(String name);

    /**
     * 新增用户
     * 
     * @param user
     * @param account
     * @param refUserDept
     */
    public void newUser(User user, Account account);

    /**
     * 删除用户
     * 
     * @param id 账号id
     */
    public void delUser(String[] idArray);

    /**
     * 重置密码
     * 
     * @param idArray
     */
    public void resetPwd(String[] idArray);

}
