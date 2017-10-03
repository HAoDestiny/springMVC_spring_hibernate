package com.destiny.work.dao;

import com.destiny.work.model.PageEntity;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Destiny_hao on 2017/6/4.
 */

@Repository("baseDao")
public class BaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    /***
     * 保存
     * @param entity
     * @throws Exception
     */
    public void save(T entity) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    /**
     * 查找一个
     * @param hql
     * @return object
     * @throws Exception
     */
    public T findOne(final String hql) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        T object = (T)query.setMaxResults(1).uniqueResult();
        session.getTransaction().commit();

        return object ;
    }

    /***
     * 分页查询
     * @param hql
     * @param pageCoed
     * @param pageSize
     * @return
     */
    public PageEntity<T> pageFind(final String hql, int pageCoed, int pageSize) {
        PageEntity<T> pageEntity = new PageEntity<>();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        Query query = session.createQuery(hql);
        ScrollableResults scrollableResults = query.scroll();
        scrollableResults.last();
        int totalRecord = scrollableResults.getRowNumber() + 1;

        pageEntity.setPageCode(pageCoed);
        pageEntity.setPageSize(pageSize);
        pageEntity.setTotalRecord(totalRecord);

        query.setFirstResult((pageCoed-1)*pageSize);
        query.setMaxResults(pageSize);

        pageEntity.setEntityList(query.list());
        session.getTransaction().commit();
        return pageEntity;
    }

    /***
     * 查询所有
     * @param hql
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(final String hql) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        List<T> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    /***
     * 使用hql语句更新
     * @param hql
     * @throws Exception
     */
    public void upDate(final String hql) throws Exception {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
    }

    /**
     * 更新整个对象
     * @param entity
     */
    public void upDate(T entity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();

    }

    /***
     * 删除实体
     * @param entity
     * @throws Exception
     */
    public void delete(T entity) throws Exception{
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(entity);
        session.getTransaction().commit();
    }

    public boolean delete(final String hql) throws Exception{
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            assert session != null;
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
