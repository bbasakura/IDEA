package cn.itcast.b2c.gciantispider.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.b2c.gciantispider.dao.IBaseDao;

@Repository
public class BaseDaoImpl<T> implements IBaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获得当前事物的session
     * 
     * @return org.hibernate.Session
     */
    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T o) {
        if (o != null) {
            return this.getCurrentSession().save(o);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(Class<T> c, Serializable id) {
        return (T) this.getCurrentSession().get(c, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        }
        return null;
    }

    @Override
    public void delete(T o) {
        if (o != null) {
            this.getCurrentSession().delete(o);
        }
    }

    @Override
    public void update(T o) {
        if (o != null) {
            this.getCurrentSession().update(o);
        }
    }

    @Override
    public void saveOrUpdate(T o) {
        if (o != null) {
            this.getCurrentSession().saveOrUpdate(o);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {

            for (String key : params.keySet()) {
                Object obj = params.get(key);
                // 拓展可以 传list 和 array进来 chenqinze
                // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (obj instanceof Collection<?>) {
                    q.setParameterList(key, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    q.setParameterList(key, (Object[]) obj);
                } else {
                    q.setParameter(key, obj);
                }
            }

        }
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, Map<String, Object> params, int page,
            int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(String hql, int page, int rows) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T> List<T> find(String hqlString, Object... parameter) {
        Query query = getCurrentSession().createQuery(hqlString);
        if (parameter != null && parameter.length > 0) {
            for (int i = 0; i < parameter.length; i++) {
                query.setParameter(i, parameter[i]);
            }
        }
        return query.list();
    }

    @Override
    public Long count(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return (Long) q.uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (Long) q.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {
        Query q = this.getCurrentSession().createQuery(hql);
        return q.executeUpdate();
    }

    @Override
    public int executeHql(String hql, Map<String, Object> params) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findBySql(String sql, int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                // 拓展可以 传list 和 array进来 chenqinze
                // 这里考虑传入的参数是什么类型，不同类型使用的方法不同
                if (obj instanceof Collection<?>) {
                    q.setParameterList(key, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    q.setParameterList(key, (Object[]) obj);
                } else {
                    q.setParameter(key, obj);
                }
            }
        }
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findBySql(String sql, Map<String, Object> params,
            int page, int rows) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public int executeSql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return q.executeUpdate();
    }

    @Override
    public int executeSql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return q.executeUpdate();
    }

    @Override
    public Long countBySql(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        try {
            Long count = ((BigDecimal) q.uniqueResult()).longValue();
            return count;
        } catch (Exception e){
            throw new RuntimeException("The sql can't be count, sql is:"+sql,e);
        }
    }
    
    @Override
    public BigInteger countBySql1(String sql) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public Long countBySql(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        try {
            Long count = ((BigDecimal) q.uniqueResult()).longValue();
            return count;
        } catch (Exception e){
            throw new RuntimeException("The sql can't be count, sql is:"+sql,e);
        }
    }
    
    @Override
    public BigInteger countBySql1(String sql, Map<String, Object> params) {
        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
        }
        return (BigInteger) q.uniqueResult();
    }

    @Override
    public String createSeq(String name) {

        String sql = "select seq('" + name + "')";

        SQLQuery q = this.getCurrentSession().createSQLQuery(sql);

        return (String) (q.list().get(0));

    }

}