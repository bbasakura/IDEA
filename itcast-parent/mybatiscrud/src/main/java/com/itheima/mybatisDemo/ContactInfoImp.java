package com.itheima.mybatisDemo;

import com.itheima.pojo.ContactInfo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ContactInfoImp implements ContactInfoIDao {

    private SqlSession sqlSession;

    /**
     * ·
     * 通过有参构造获取sqlsession，
     *
     * @param sqlSession
     */
    public ContactInfoImp(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public ContactInfo queryContactById( int id) {
        return
                sqlSession.selectOne("ContactMapper.queryContactById", id );
    }

    @Override
    public List<ContactInfo> queryContactAll() {
        return
                sqlSession.selectList("ContactMapper.queryContactAll");
    }

    @Override
    public void insertContact(ContactInfo contactInfo) {
        sqlSession.insert("ContactMapper.insertContact",contactInfo);//注意是两个参数，后面一个是bean
        sqlSession.commit();// 别忘了commit~~~~~~~~~~~~~~~~~~~~
    }

    @Override
    public void updateContact(ContactInfo contactInfo) {
        sqlSession.update("ContactMapper.updateContact",contactInfo);
        sqlSession.commit();
    }

    @Override
    public void deleteContactById(int id) {
        sqlSession.delete("ContactMapper.deleteContactById",id );
        sqlSession.commit();
    }
}
