package cn.itcast.b2c.gciantispider.service;

import java.util.List;
import java.util.Map;

import cn.itcast.b2c.gciantispider.bean.PermissionBean;
import cn.itcast.b2c.gciantispider.model.Account;

public interface IAccountService {
    /**
     * 获取全部的个人信息
     * 
     * @param pageNo 当前页数
     * @param pageSize 每页显示多少
     * @param seaStr 搜索条件
     * @return
     */
    List<Account> getAllUser(int pageNo, int pageSize, String seaStr);

    Long getAllCount(String seaStr);

    /**
     * 根据账号获取账号信息，用户信息，部门，角色信息
     * 
     * @param account 账号
     * @return
     */
    Account getAccUserRoleDtByAccount(String account);

    /**
     * 根据账号查找账号信息
     * 
     * @param account账号名
     * @return
     */
    Account getByAccount(String account);

    /**
     * 根据账号名和密码查找账号信息
     * 
     * @param userName
     * @param password
     * @return
     */
    Account getByAccountAndPassword(String userName, String password);

    /**
     * 更新账号信息
     * 
     * @param account
     */
    void update(Account account);

    /**
     * 根据accountId获取该账号角色
     * 
     * @param accountId
     * @return
     */
    List<Map<String, Object>> getRoleByAccountId(String accountId);

    /**
     * 获取角色用户列表
     * 
     * @param ids
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Account> roleUser(String[] ids, int pageNo, int pageSize);

    /**
     * 角色用户总数
     * 
     * @param idArray
     * @return
     */
    Long roleUserCount(String[] idArray);

    /**
     * 根据账号查询当前账号在该系统所具有的权限
     * 
     * @param account
     * @param system
     * @return
     */
    List<PermissionBean> getAccUserPersByAccount(String account, String system);

}
