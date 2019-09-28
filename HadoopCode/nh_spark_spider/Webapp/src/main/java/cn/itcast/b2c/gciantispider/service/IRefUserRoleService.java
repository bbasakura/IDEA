package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.RefUserRole;

public interface IRefUserRoleService {
    /**
     * 根据账号ID找出关联表中相关的记录
     * 
     * @param id
     * @return
     */
    List<RefUserRole> getByAccountId(String id);

    /**
     * 删除关联表中的记录
     * 
     * @param list
     */
    void delRefUserRoles(List<RefUserRole> list);

    /**
     * 给某账号分配角色
     * 
     * @param aids
     * @param roleIdArray
     */
    void configRolesByAccountId(String[] aids, String[] roleIdArray);

    /**
     * 根据id删除记录
     * 
     * @param id
     */
    void delRefUserRoleById(String id);
}
