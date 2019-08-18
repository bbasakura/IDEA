package com.itheima.service;

import com.itheima.domain.ContactInfo;

import java.util.List;

public interface ContactInfoServiceImp {
    // 查询所有通讯录信息
    public List<ContactInfo> queryAll();

    //删除指定用户的通讯录，成功返回TRUE
    public boolean delById(String id);

    //添加新的通信录,成功为TRUE
    public boolean addContact(ContactInfo contactInfo);

    //查询记录的条数
    public int queryCount();

    //查询所有的通讯录 的信息（分页的方式）
    public List<ContactInfo> queryAllf(int offset, int size);

    //修改时的获取要修改的一个通讯录对象
    public ContactInfo getContact(String contactId);

    public boolean updateContact(ContactInfo contactInfo);
}
