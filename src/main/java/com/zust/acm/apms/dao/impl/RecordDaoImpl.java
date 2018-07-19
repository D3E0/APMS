package com.zust.acm.apms.dao.impl;

import com.zust.acm.apms.dao.RecordDao;
import com.zust.acm.apms.entity.RecordEntity;
import com.zust.acm.apms.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class RecordDaoImpl implements RecordDao {

    @Autowired
    private SessionFactory factory;

    @Override
    public Integer addRecord(RecordEntity record) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            session.save(record);
            ts.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Override
    public List getRecordList(Timestamp time) {
        Session session = factory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from RecordEntity where to_days(time)= to_days(:A)");
        query.setParameter("A", time);
        List list = query.list();
        session.getTransaction().commit();
        return list;
    }

//    @Override
//    public List getRecords(Timestamp time) {
//        Session session = factory.openSession();
//        session.beginTransaction();
//        Query query = session.createQuery("select username, userId, ifNull() from RecordEntity where to_days(time)= to_days(:A)");
//        query.setParameter("A", time);
//        List list = query.list();
//        return list;
//        return null;
//    }

    @Override
    public Integer verifyRecordByUserIdInDayTime(Timestamp time, String userId) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT COUNT(*) FROM RecordEntity AS r " +
                    "WHERE to_days(r.time)= to_days(:A) and hour(r.time) between 0 AND 8 "
                    + " AND r.user.userId = :B");
            query.setParameter("A", time);
            query.setParameter("B", userId);
            ts.commit();
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public Integer verifyRecordByUserIdInNight(Timestamp time, String userId) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT COUNT(*) FROM RecordEntity AS r " +
                    "WHERE to_days(r.time)= to_days(:A) and hour(r.time) between 21 AND 23 "
                    + " AND r.user.userId = :B");
            query.setParameter("A", time);
            query.setParameter("B", userId);
            ts.commit();
        } catch (Exception e) {
            return -1;
        }
        return 1;
    }

    @Override
    public List<String> findAlreadyInByDay(Timestamp timestamp) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        Query query = session.createQuery("SELECT u.userName FROM UserEntity AS u, RecordEntity AS r" +
                " WHERE to_days(r.time)= to_days(:A) and hour(r.time) between 0 AND 8" +
                " AND u.userId = r.user.userId");
        query.setParameter("A", timestamp);
        List<String> userNames = query.list();
        ts.commit();
        return userNames;
    }

    @Override
    public List<String> findAlreadyOutByDay(Timestamp timestamp) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        Query query = session.createQuery("SELECT u.userName FROM UserEntity AS u, RecordEntity AS r" +
                " WHERE to_days(r.time)= to_days(:A) and hour(r.time) between 21 AND 23" +
                " AND u.userId = r.user.userId");
        query.setParameter("A", timestamp);
        List<String> userNames = query.list();
        ts.commit();
        return userNames;
    }

    @Override
    public List<String> findAlreadyOutTooEarlyByDay(Timestamp timestamp) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        Query query = session.createQuery("SELECT userName FROM UserEntity AS u WHERE userId NOT IN (" +
                "SELECT r.user.userId From RecordEntity AS r WHERE to_days(r.time)= to_days(:A) " +
                "AND hour(r.time) between 21 AND 23  AND u.userId = r.user.userId)");
        query.setParameter("A", timestamp);
        List<String> userNames = query.list();
        ts.commit();
        return userNames;
    }

    @Override
    public List<String> findAlreadyInTooLateByDay(Timestamp timestamp) {
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        Query query = session.createQuery("SELECT userName FROM UserEntity AS u WHERE userId NOT IN (" +
                "SELECT r.user.userId From RecordEntity AS r WHERE to_days(r.time)= to_days(:A) " +
                "AND hour(r.time) between 0 AND 8  AND u.userId = r.user.userId)");
        query.setParameter("A", timestamp);
        List<String> userNames = query.list();
        ts.commit();
        return userNames;
    }
}
