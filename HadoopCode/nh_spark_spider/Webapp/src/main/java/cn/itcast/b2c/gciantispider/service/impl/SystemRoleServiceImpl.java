package cn.itcast.b2c.gciantispider.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.ISystemRoleDao;
import cn.itcast.b2c.gciantispider.model.SystemRole;
import cn.itcast.b2c.gciantispider.service.ISystemRoleService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service
public class SystemRoleServiceImpl implements ISystemRoleService {

    @Autowired
    ISystemRoleDao systemRoleDao;

    public List<SystemRole> getAllRole(int pageNo, int pageSize, String seaStr) {
        String hql = "from SystemRole";
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Common.isEmpty(seaStr)) {
            hql += " where name like :name order by name";
            params.put("name", "%" + seaStr + "%");
        }
        else {
            hql += " order by name";
        }
        return systemRoleDao.find(hql, params, pageNo, pageSize);
    }

    public Long getAllCount(String seaStr) {
        String hql = "select count(*) from SystemRole";
        Map<String, Object> params = new HashMap<String, Object>();
        if (!Common.isEmpty(seaStr)) {
            hql += " where name like :name";
            params.put("name", "%" + seaStr + "%");
        }
        return systemRoleDao.count(hql, params);
    }

    public void add(SystemRole systemRole) {
        systemRoleDao.save(systemRole);
    }

    public void modify(String id, String name, String cnname, String description) {
        SystemRole systemRole = systemRoleDao.get(SystemRole.class, id);
        systemRole.setName(name);
        systemRole.setCnname(cnname);
        systemRole.setDescription(description);
        systemRoleDao.update(systemRole);
    }

    public SystemRole getRoleById(String id) {
        String hql = "from SystemRole where id =:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return systemRoleDao.get(hql, params);
    }

    public void delRole(String[] idArray) {
        for (String id : idArray) {
            if (!Common.isEmpty(id)) {
                String hql = "from SystemRole s left join fetch s.refRolePermissions rp left join fetch s.refUserRoles ur where s.id =:id";
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", id);
                SystemRole role = systemRoleDao.get(hql, params);
                systemRoleDao.delete(role);
            }
        }
    }

    public List<SystemRole> roleUser(String idArray, int pageNo, int pageSize) {
        String hql = "from SystemRole s left join fetch s.refUserRoles ur left join fetch ur.account a where FIND_ID_SET(a.id,:idArray)";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idArray", idArray);
        return systemRoleDao.find(hql, params, pageNo, pageSize);
    }

    public List<SystemRole> getAbleRole(String seaStr, int pageNo, int pageSize) {
        String hql = "from SystemRole s where s.state = :state ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", "0");
        if (!Common.isEmpty(seaStr)) {
            hql += "and lower(s.name) like :name order by s.name";
            params.put("name", "%" + seaStr.toLowerCase() + "%");
        }
        else {
            hql += " order by s.name ";
        }
        return systemRoleDao.find(hql, params, pageNo, pageSize);
    }

    public Long getAbleRoleCount(String seaStr) {
        String hql = "select count(*) from SystemRole s where s.state = :state ";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", "0");
        if (!Common.isEmpty(seaStr)) {
            hql += "and lower(s.name) like :name ";
            params.put("name", "%" + seaStr.toLowerCase() + "%");
        }
        return systemRoleDao.count(hql, params);
    }

    public List<String> getRoleByUser(String id) {
        return systemRoleDao.getRoleByUser(id);
    }

}
