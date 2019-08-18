package com.itheima.service;

import com.itheima.dao.ContactInfoDao;
import com.itheima.domain.ContactInfo;
import java.util.List;

public class ContactInfoService implements ContactInfoServiceImp {
//这是与dao层交互的，所以创建一个dao对象（频繁的用到），放在成员变量的位置
    private ContactInfoDao dao = new ContactInfoDao();

    @Override
    public List<ContactInfo> queryAll() {
        return dao .queryAll();
    }

    @Override
    //删除一行数据的时候，成功时返回值为1；否则删除失败？？？？
    public boolean delById(String id) {
        int count = dao.delById(id);
        return count==1;
    }

    @Override
    public boolean addContact(ContactInfo contactInfo) {
        //同上，添加一个数据，成功时影响一行，否则失败？
        int count = dao.addContact(contactInfo);
        return count==1;
    }

    @Override
    public int queryCount() {
        return dao.queryCount();
    }

    @Override
    public List<ContactInfo> queryAllf(int offset, int size) {
        return dao.queryAll();
    }
}
