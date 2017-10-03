package com.destiny.work.dao;

import com.destiny.work.model.TblUserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Destiny_hao on 2017/6/3.
 */

@Repository("loginDao")
public class LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

//    /***
//     * 注册
//     * @param hql
//     * @throws Exception
//     */
//    public TblUserEntity addUser(String hql) throws Exception {
//
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        Query query = session.createQuery(hql);
//        TblUserEntity userEntity = (TblUserEntity) query.setMaxResults(1).uniqueResult();
//        session.getTransaction().commit();
//        return userEntity;
//    }

    /***
     * 登录
     * @param hql
     * @return
     * @throws Exception
     */
    public TblUserEntity login(final String hql) throws Exception {
        System.out.println("login");
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hql);
        TblUserEntity userEntity = (TblUserEntity)query.setMaxResults(1).uniqueResult();
        session.getTransaction().commit();

        return userEntity ;
    }
}
