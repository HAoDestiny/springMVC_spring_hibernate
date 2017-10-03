package com.destiny.work.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by .D on 2017/5/25.
 */

@Repository("userDao")
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

//
//    public List<User> getAllUser(String sql) {
//        List<User> userAll = new ArrayList<User>();
//        userAll.add(new User("1", "tom","destinyhao@foxmail.com"));
//        userAll.add(new User("2", "cat","destinyhao@foxmail.com"));
//        userAll.add(new User("3", "lxk","destinyhao@foxmail.com"));
//        userAll.add(new User("4", "cms","destinyhao@foxmail.com"));
//
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        SQLQuery sqlQuery = session.createSQLQuery(sql);
//        session.getTransaction().commit();
//
//        List<User> list = sqlQuery.list();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getName());
//        }
//        return userAll;
//    }
}
