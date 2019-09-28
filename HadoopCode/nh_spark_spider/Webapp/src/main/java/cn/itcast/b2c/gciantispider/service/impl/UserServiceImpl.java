package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.IAccountDao;
import cn.itcast.b2c.gciantispider.dao.IRefUserRoleDao;
import cn.itcast.b2c.gciantispider.dao.ISystemRoleDao;
import cn.itcast.b2c.gciantispider.dao.IUserDao;
import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.User;
import cn.itcast.b2c.gciantispider.service.IUserService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.DateFormatter;
import cn.itcast.b2c.gciantispider.util.PasswordEncoderUtil;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IAccountDao accountDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRefUserRoleDao refUserRoleDao;

    @Autowired
    private ISystemRoleDao systemRoleDao;

    public List<User> queryUserInfo(String id) {
        List<User> list = userDao.queryUserInfo(id);
        return list;
    }

    @Override
    public User queryById(String id) {
        String hql = "from User u where u.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return userDao.get(hql, params);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<Map<String, Object>> searchUser(String name) {
        String sql = "SELECT a.id,a.NAME aname,a.STATE,a.MODIFYPASSTIME,u.NAME uname,u.EMAIL,u.MOBILE,sr.CNNAME,d.NAME dname " + "FROM user_info u LEFT JOIN account a ON u.ID=a.ID "
                + "LEFT JOIN ref_user_dept up ON up.ACCOUNT_ID=a.ID " + "LEFT JOIN department d ON d.ID=up.DEPT_ID " + "LEFT JOIN ref_user_role ur ON ur.ACCOUNT_ID=a.ID "
                + "LEFT JOIN system_role sr ON ur.ROLE_ID=sr.ID " + "WHERE u.NAME LIKE :name";
        Map<String, Object> params = new HashMap<String, Object>();
        List<Object[]> objlist = new ArrayList<Object[]>();
        if (!Common.isEmpty(name)) {
            params.put("name", "%" + name + "%");
            objlist = userDao.findBySql(sql, params);
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (objlist != null) {
            for (Object[] o : objlist) {
                Map<String, Object> map = new HashMap<String, Object>();
                String passtime = null;
                // 判断是否过期
                if (null != (Date) o[3]) {
                    Date date1 = (Date) o[3];
                    Date date2 = new Date();
                    int days = DateFormatter.getIntervalDays(date1, date2);
                    if (days > 30) {
                        passtime = "过期";
                    }
                    else {
                        passtime = "有效";
                    }
                }

                map.put("id", o[0]);
                map.put("aname", o[1]);
                map.put("state", o[2]);
                map.put("passtime", passtime);
                map.put("uname", o[4]);
                map.put("email", o[5]);
                map.put("mobile", o[6]);
                map.put("rname", o[7]);
                map.put("dname", o[8]);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @Override
    public void newUser(User user, Account account) {
        accountDao.save(account);
        userDao.save(user);
    }

    @Override
    public void delUser(String[] idArray) {
        for (String id : idArray) {
            if (!Common.isEmpty(id)) {
                String hql = "from Account a left join fetch a.refUserRoles ur left join fetch a.user u where a.id =:id ";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", id);
                Account account = accountDao.get(hql, params);
                accountDao.delete(account);
            }
        }
    }

    @Override
    public void resetPwd(String[] idArray) {
        // 重置密码统一为 000000，经过SHA1加密后存入数据库中
        String password = "000000";
        password = new PasswordEncoderUtil("SHA1").encode(password);
        for (String id : idArray) {
            if (!Common.isEmpty(id)) {
                String hql = "from Account a where a.id=:id";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", id);
                Account accobj = accountDao.get(hql, params);
                accobj.setPassword(password);
                accountDao.update(accobj);
            }
        }

    }

    // @Override
    // public void ableUser(String flag, String[] idArray) {
    // for(String id : idArray){
    // if(!Common.isEmpty(id)){
    // String hql = "from Account a where a.id=:id";
    // Map<String,Object> params = new HashMap<String,Object>();
    // params.put("id", id);
    // Account accobj = accountDaoI.get(hql, params);
    // if ("0".equals(flag)) { //启用
    // accobj.setState("0");
    // }else if("1".equals(flag)){ //禁用
    // accobj.setState("1");
    // }
    // accountDaoI.update(accobj);
    // }
    // }
    //
    // }

}
