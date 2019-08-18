package com.itheima.mybatisDemo;

import com.itheima.pojo.ContactInfo;

import java.util.List;

public interface ContactInfoIDao {
    /**
     * 根据Id查询通讯录信息
     * @param id
     * @return
     */
    public ContactInfo queryContactById( int id);

    /**
     * 查询所有的通讯录
     * @return
     */
     public List<ContactInfo> queryContactAll( );

    /**
     * 插入通讯录
     *
     */
    public void  insertContact(ContactInfo contactInfo);

    /**
     * @param contactInfo
     */
    public void  updateContact(ContactInfo contactInfo);

    /**
     * 根据id删除通讯录
     * @param id
     */
    public void  deleteContactById(int id );
}
