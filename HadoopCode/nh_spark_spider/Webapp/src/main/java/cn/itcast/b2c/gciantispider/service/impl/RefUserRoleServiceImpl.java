package cn.itcast.b2c.gciantispider.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.IAccountDao;
import cn.itcast.b2c.gciantispider.dao.IRefUserRoleDao;
import cn.itcast.b2c.gciantispider.dao.ISystemRoleDao;
import cn.itcast.b2c.gciantispider.model.Account;
import cn.itcast.b2c.gciantispider.model.RefUserRole;
import cn.itcast.b2c.gciantispider.model.SystemRole;
import cn.itcast.b2c.gciantispider.service.IRefUserRoleService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service
public class RefUserRoleServiceImpl implements IRefUserRoleService {

    @Autowired
    IRefUserRoleDao refUserRoleDao;

    @Autowired
    IAccountDao accountDao;

    @Autowired
    ISystemRoleDao systemRoleDao;

    @Override
    public List<RefUserRole> getByAccountId(String id) {
        String hql = "from RefUserRole ur where ur.account.id =:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return refUserRoleDao.find(hql, params);
    }

    @Override
    public void delRefUserRoles(List<RefUserRole> list) {
        for (RefUserRole refUserRole : list) {
            refUserRoleDao.delete(refUserRole);
        }

    }

    @Override
    public void configRolesByAccountId(String[] aids, String[] roleIdArray) {
        if (aids != null && aids.length == 1) {
            // 先删除，再插入
            String sql = "delete from ref_user_role where account_id = :id";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", aids[0]);
            refUserRoleDao.executeSql(sql, params);
            if (roleIdArray != null) {
                for (String str : roleIdArray) {
                    RefUserRole ur = new RefUserRole();
                    Account account = accountDao.get(Account.class, aids[0]);
                    SystemRole role = systemRoleDao.get(SystemRole.class, str);
                    ur.setArId(UUID.randomUUID().toString());
                    ur.setAccount(account);
                    ur.setSystemRole(role);
                    refUserRoleDao.save(ur);
                }
            }
        }
        else if (aids != null && aids.length > 1) {
            for (String id : aids) {
                if (!Common.isEmpty(id)) {
                    String hql = "from SystemRole s left join fetch s.refUserRoles ur left join fetch ur.account a where a.id = :id";
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("id", id);
                    List<SystemRole> list = systemRoleDao.find(hql, params);
                    Set<String> set = new HashSet<String>();
                    if (list != null) {
                        for (SystemRole s : list) {
                            set.add(s.getId());
                        }
                    }
                    if (roleIdArray != null) {
                        for (String r : roleIdArray) {
                            set.add(r);
                        }
                    }
                    // 先删除，再插入
                    String sql = "delete from ref_user_role where account_id = :id";
                    refUserRoleDao.executeSql(sql, params);
                    if (set != null && set.size() > 0) {
                        for (String str : set) {
                            RefUserRole ur = new RefUserRole();
                            Account account = accountDao.get(Account.class, id);
                            SystemRole role = systemRoleDao.get(SystemRole.class, str);
                            ur.setArId(UUID.randomUUID().toString());
                            ur.setAccount(account);
                            ur.setSystemRole(role);
                            refUserRoleDao.save(ur);
                        }
                    }
                }
            }
        }
    }

    public void delRefUserRoleById(String id) {
        RefUserRole ur = refUserRoleDao.get(RefUserRole.class, id);
        refUserRoleDao.delete(ur);
    }

}
