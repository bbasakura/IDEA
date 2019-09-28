package cn.itcast.b2c.gciantispider.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.itcast.b2c.gciantispider.dao.IAccountDao;
import cn.itcast.b2c.gciantispider.model.Account;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<Account> implements IAccountDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Account> roleUser(String[] ids, int pageNo, int pageSize) {
        String hql="from Account a left join fetch a.user left join fetch a.refUserRoles ur left join fetch ur.systemRole s "
              + " where s.id in(:ids) ";  
        Query q = this.getCurrentSession().createQuery(hql);  
        q.setParameterList("ids", ids);
        return q.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
    }

    @Override
    public Long roleUserCount(String[] ids) {
        String sql="select count(*) from Account a inner join  a.user  inner join a.refUserRoles ur inner join  ur.systemRole s where s.id in(:ids)";   
        Query q = this.getCurrentSession().createQuery(sql);  
        q.setParameterList("ids", ids);
        return (Long) q.uniqueResult();
    }

}
