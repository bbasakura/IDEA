package cn.itcast.b2c.gciantispider.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.IPermissionDao;
import cn.itcast.b2c.gciantispider.model.Permission;
import cn.itcast.b2c.gciantispider.service.IPermissionService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    public List<Permission> getAllPermission() {
        String hql = "from Permission  order by perlevel asc,num desc";
        return permissionDao.find(hql);
    }

    public List<Object[]> searchPermission(String seaStr) {
        String sql = "select id from permission where name like :name or num like :number";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "%" + seaStr + "%");
        params.put("number", "%" + seaStr + "%");
        List<Object[]> obj = permissionDao.findBySql(sql, params);
        return obj;
    }

    @Override
    public Serializable newPermission(Permission per) {
        return permissionDao.save(per);
    }

    @Override
    public Permission getPermissionById(String id) {
        String hql = "from Permission p left join fetch p.refRolePermissions rp where p.id =:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return permissionDao.get(hql, params);
    }

    @Override
    public void modifyPermission(Permission per) {
        permissionDao.update(per);
    }

    @Override
    public void delPermission(String[] idArray) {
        for (String id : idArray) {
            if (!Common.isEmpty(id)) {
                Permission per = permissionDao.get(Permission.class, id);
                if (per != null) {
                    permissionDao.delete(per);
                }
            }
        }

    }

    public List<Permission> getRolePermission(String id) {
        String hql = "from Permission p left join fetch p.refRolePermissions rp left join fetch rp.systemRole s where s.id =:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return permissionDao.find(hql, params);
    }

    @Override
    public Permission getPermissionByName(String name) {
        String hql = "from Permission p where p.name =:name";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        return permissionDao.get(hql, params);
    }

    @Override
    public List<String> getSystemPerBySysName(String sysName) {
       String sql = "SELECT p.URL, p.ID FROM permission p " + "WHERE FIND_IN_SET(p.ID,(select getChildLst((select pp.id from permission pp where pp.name=:name))))";
        Map<String, Object> params = new HashMap<String, Object>();
        List<Object[]> objlist = new ArrayList<Object[]>();
        params.put("name", sysName);
        objlist = permissionDao.findBySql(sql, params);

        List<String> perUrllList = new ArrayList<String>();
        if (null != objlist && objlist.size()>0) {
            for (Object[] o : objlist) {
                perUrllList.add((String) o[0]);
            }

            return perUrllList;
        }

        return null;
    }

}
