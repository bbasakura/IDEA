package cn.itcast.b2c.gciantispider.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.bean.PermissionBean;
import cn.itcast.b2c.gciantispider.dao.IAccountDao;
import cn.itcast.b2c.gciantispider.dao.IUserDao;
import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.service.IAccountService;
import cn.itcast.b2c.gciantispider.util.Common;
import cn.itcast.b2c.gciantispider.util.PasswordEncoderUtil;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    IAccountDao accountDao;

    @Autowired
    IUserDao userDao;

    public List<Account> getAllUser(int pageNo, int pageSize, String seaStr) {
        String hql = "from Account a left join fetch a.user u left join fetch a.refUserRoles ur left join fetch ur.systemRole s ";
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Common.isEmpty(seaStr)) {
            hql += " where u.name like :name ";
            params.put("name", "%" + seaStr + "%");
        }
        hql += " order by a.name, s.name ";
        return accountDao.find(hql, params, pageNo, pageSize);
    }

    public Long getAllCount(String seaStr) {
        String hql = "select count(*) from Account a left join a.user u";
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Common.isEmpty(seaStr)) {
            hql += " where u.name like :name";
            params.put("name", "%" + seaStr + "%");
        }
        return accountDao.count(hql, params);
    }

    @Override
    public Account getAccUserRoleDtByAccount(String account) {
        String hql = "from Account a left join fetch a.user u left join fetch a.refUserDepts ud left join fetch ud.department left join fetch a.refUserRoles ur left join fetch ur.systemRole";
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Common.isEmpty(account)) {
            hql += " where a.name=:name";
            params.put("name", account);
        }

        Account acc = accountDao.get(hql, params);
        return acc;
    }

    @Override
    public List<PermissionBean> getAccUserPersByAccount(String account, String system) {
        String sql = "SELECT DISTINCT * FROM (SELECT p.NAME pname,p.NUM,p.PARENTID,p.DESCRIPTION,p.URL,p.ID " + " FROM account a LEFT JOIN user_info u ON a.ID=u.ID "
                + " LEFT JOIN ref_user_role ur ON a.ID=ur.ACCOUNT_ID " + " LEFT JOIN system_role sr ON ur.ROLE_ID=sr.ID  " + " LEFT JOIN ref_role_permission rp ON sr.ID =rp.ROLE_ID "
                + " LEFT JOIN permission p ON rp.PERMISSION_ID=p.ID " + " WHERE a.NAME=:account) ss "
                + " WHERE FIND_IN_SET(ss.ID ,(select getChildLst((select pp.id from permission pp where pp.name =:system)))) ";
        Map<String, Object> params = new HashMap<String, Object>();
        List<Object[]> objList = new ArrayList<Object[]>();
        if (!Common.isEmpty(account)) {
            params.put("account", account);
            params.put("system", system);
            objList = accountDao.findBySql(sql, params);
        }
        List<PermissionBean> permissionBeans = new ArrayList<PermissionBean>();
        int size = objList.size();
        if (null != objList && size >= 0) {
            for (int i = 0; i < size; i++) {
                Object[] o = objList.get(i);
                PermissionBean permissionBean = new PermissionBean();
                permissionBean.setId((String) o[0]);
                permissionBean.setPerName((String) o[1]);
                permissionBean.setPerNum((String) o[2]);
                permissionBean.setPerParentId((String) o[3]);
                permissionBean.setPerUrl((String) o[4]);
                permissionBean.setPerDesr((String) o[5]);
                permissionBeans.add(permissionBean);
            }
            return permissionBeans;
        }
        return null;
    }

    @Override
    public Account getByAccount(String account) {
        String hql = "from Account a where a.name=:account";
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Common.isEmpty(account)) {
            params.put("account", account);
            return accountDao.get(hql, params);
        }
        return null;
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public List<Map<String, Object>> getRoleByAccountId(String accountId) {
        String sql = "SELECT sr.CNNAME cnname, sr.NAME name, sr.DESCRIPTION, sr.ID FROM account a LEFT JOIN ref_user_role ur ON a.ID=ur.ACCOUNT_ID "
                + "LEFT JOIN system_role sr ON sr.ID=ur.ROLE_ID WHERE a.ID=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        List<Object[]> objlist = new ArrayList<Object[]>();
        if (!Common.isEmpty(accountId)) {
            params.put("id", accountId);
            objlist = accountDao.findBySql(sql, params);
        }

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (objlist != null) {
            for (Object[] o : objlist) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("aname", o[0]);
                map.put("uname", o[1]);
                map.put("desc", o[2]);
                map.put("id", o[3]);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    public List<Account> roleUser(String[] ids, int pageNo, int pageSize) {
        return accountDao.roleUser(ids, pageNo, pageSize);
    }

    public Long roleUserCount(String[] ids) {
        return accountDao.roleUserCount(ids);
    }

    public Account getByAccountAndPassword(String userName, String password) {
        PasswordEncoderUtil passwordEncoder = new PasswordEncoderUtil("SHA1");
        password = passwordEncoder.encode(password);
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "FROM Account a WHERE a.name=:userName AND a.password=:password";
        if (!Common.isEmpty(userName) && !Common.isEmpty(password)) {
            params.put("userName", userName);
            params.put("password", password);
            return accountDao.get(hql, params);
        }
        return null;
    }
}
