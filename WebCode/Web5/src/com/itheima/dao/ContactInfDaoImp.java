package com.itheima.dao;
//通讯录Dao，为什么面向接口？

import com.itheima.domain.ContactInfo;

import java.util.List;

public interface ContactInfDaoImp {

    // 查询所有通讯录信息
    public List<ContactInfo> queryAll();
    //逻辑删除通讯录
    public int delById (String id);
    //添加通信录
    public int addContact (ContactInfo contactInfo);
    //查询记录的条数
    public int queryCount ();
    //查询所有的通讯录 的信息（分页的方式）
    public List<ContactInfo> queryAllf(int offset,int size);

}
