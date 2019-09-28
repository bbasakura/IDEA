package cn.itcast.b2c.gciantispider.service;

import java.util.List;

import cn.itcast.b2c.gciantispider.model.SystemRole;

public interface ISystemRoleService {
    /**
     * 分页获取所有角色
     * 
     * @param pageNo
     * @param pageSize
     * @param seaStr
     * @return
     */
    List<SystemRole> getAllRole(int pageNo, int pageSize, String seaStr);

    /**
     * 获取所有角色的总数
     * 
     * @param seaStr
     * @return
     */
    Long getAllCount(String seaStr);

    /**
     * 新增角色
     * 
     * @param systemRole
     */
    void add(SystemRole systemRole);

    /**
     * 更新角色
     * 
     * @param id
     * @param name
     * @param cnname
     * @param description
     */
    void modify(String id, String name, String cnname, String description);

    /**
     * 根据id获取角色
     * 
     * @param id
     * @return
     */
    SystemRole getRoleById(String id);

    /**
     * 删除角色
     * 
     * @param idArray
     */
    void delRole(String[] idArray);

    /**
     * 角色用户信息
     * 
     * @param idArray
     * @return
     */
    List<SystemRole> roleUser(String idArray, int pageNo, int pageSize);

    /**
     * 获取可选角色
     * 
     * @param seaStr
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<SystemRole> getAbleRole(String seaStr, int pageNo, int pageSize);

    /**
     * 获取可选角色数目
     * 
     * @param seaStr
     * @return
     */
    Long getAbleRoleCount(String seaStr);

    List<String> getRoleByUser(String id);

}
