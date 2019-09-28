package cn.itcast.b2c.gciantispider.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.b2c.gciantispider.dao.IPermissionDao;
import cn.itcast.b2c.gciantispider.dao.IRefRolePermissionDao;
import cn.itcast.b2c.gciantispider.dao.ISystemRoleDao;
import cn.itcast.b2c.gciantispider.model.Permission;
import cn.itcast.b2c.gciantispider.model.RefRolePermission;
import cn.itcast.b2c.gciantispider.model.SystemRole;
import cn.itcast.b2c.gciantispider.service.IRefRolePermissionService;
import cn.itcast.b2c.gciantispider.util.Common;

@Service
public class RefRolePermissionServiceImpl implements IRefRolePermissionService {

    @Autowired
    private ISystemRoleDao systemRoleDao;

    @Autowired
    private IPermissionDao permissionDao;

    @Autowired
    private IRefRolePermissionDao refRolePermissionDao;

    @Override
    public void saveRolePermission(String roleId, String[] idArray) {
        this.delRolePermission(roleId);
        if (null != idArray) {
            // 新增权限
            for (String id : idArray) {
                if (!Common.isEmpty(id)) {
                    RefRolePermission rp = new RefRolePermission();
                    SystemRole systemRole = systemRoleDao.get(SystemRole.class, roleId);
                    Permission permission = permissionDao.get(Permission.class, id);
                    rp.setRrId(UUID.randomUUID().toString());
                    rp.setSystemRole(systemRole);
                    rp.setPermission(permission);
                    refRolePermissionDao.save(rp);
                }
            }
        }

    }

    public void delRolePermission(String roleId) {
        String hql = "delete from RefRolePermission r where r.systemRole.id =:roleId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        refRolePermissionDao.executeHql(hql, params);
    }

}
