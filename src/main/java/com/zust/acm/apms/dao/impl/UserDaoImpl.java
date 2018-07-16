package com.zust.acm.apms.dao.impl;

import com.zust.acm.apms.dao.UserDao;
import com.zust.acm.apms.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
    UserEntity getUserById(int userId){

    }
}
