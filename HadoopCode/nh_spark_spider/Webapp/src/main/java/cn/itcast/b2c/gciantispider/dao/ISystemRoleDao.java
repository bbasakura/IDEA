package cn.itcast.b2c.gciantispider.dao;

import cn.itcast.b2c.gciantispider.model.SystemRole;

import java.util.List;

public interface ISystemRoleDao extends IBaseDao<SystemRole>{

    public List<String> getRoleByUser(String id);
    
}