package cn.itcast.b2c.gciantispider.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.b2c.gciantispider.model.Permission;

public interface IPermissionService {

    /**
     * 获取所有权限点信息
     * 
     * @return
     */
    List<Permission> getAllPermission();

    /**
     * 获取符合搜索条件的id
     * 
     * @param seaStr
     * @return
     */
    List<Object[]> searchPermission(String seaStr);

    /**
     * 新增权限点
     * 
     * @param per
     */
    Serializable newPermission(Permission per);

    /**
     * 根据id获取权限点
     * 
     * @param id
     * @return
     */
    Permission getPermissionById(String id);

    /**
     * 修改权限点
     * 
     * @param per
     */
    void modifyPermission(Permission per);

    /**
     * 删除权限点
     * 
     * @param idArray
     */
    void delPermission(String[] idArray);

    /**
     * 根据roleid获取权限列表
     * 
     * @param id
     * @return
     */
    List<Permission> getRolePermission(String id);

    /**
     * 根据权限名查找权限
     * 
     * @param name 权限名称
     * @return
     */
    Permission getPermissionByName(String name);

    /**
     * 根据系统名称查询该系统所拥有的全部权限
     * 
     * @param sysName
     * @return
     */
    List<String> getSystemPerBySysName(String sysName);
}
