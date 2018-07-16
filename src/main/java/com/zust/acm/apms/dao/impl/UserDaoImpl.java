package com.zust.acm.apms.dao.impl;

import com.zust.acm.apms.dao.UserDao;
import com.zust.acm.apms.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public int updateUser(UserEntity user) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            session.save(user);
            ts.commit();
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public int addUser(UserEntity user) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            session.save(user);
            ts.commit();
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public UserEntity getUserById(String userId) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        UserEntity userEntity;
        try {
            userEntity = session.get(UserEntity.class, userId);
            ts.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return userEntity;
    }


    @Override
    public List<String> getUserIdList(){
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        List<String> userIdList;
        try {
            Query query = session.createQuery("select userId from UserEntity ");
            userIdList = query.list();
            ts.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return userIdList;
    }
}
