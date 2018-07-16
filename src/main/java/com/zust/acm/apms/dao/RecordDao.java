package com.zust.acm.apms.dao;

import com.zust.acm.apms.entity.RecordEntity;
import com.zust.acm.apms.entity.UserEntity;

import java.sql.Timestamp;
import java.util.List;

public interface RecordDao {

    /**
     * 添加记录
     * ("INSERT INTO record( userId, time, ip) " +
     * "VALUES(#{record.userId}, #{record.time}, #{record.ip})")
     *
     * @return
     */
    Integer addRecord(RecordEntity record);

    List getRecordList(Timestamp time);


    /**
     * 判断是否有签到记录
     * ("SELECT COUNT(*) FROM record AS r WHERE " +
     * "to_days(r.time)= to_days(#{time}) and hour(r.time) between 0 AND 8 " +
     * " AND r.userId = #{userId}")
     *
     * @param time
     * @param userId
     * @return
     */

    Integer verifyRecordByUserIdInDayTime(Timestamp time, String userId);

    /**
     * 判断是否有签退记录
     * ("SELECT COUNT(*) FROM record AS r WHERE " +
     * "to_days(r.time)= to_days(#{time}) and hour(r.time) between 21 AND 23 " +
     * " AND r.userId = #{userId}")
     *
     * @return
     */

    Integer verifyRecordByUserIdInNight(Timestamp time, String userId);

    /**
     * ("SELECT * FROM user WHERE userId NOT IN (SELECT userId FROM ")
     *
     * @return
     */
    List<String> findAlreadyInByDay(Timestamp timestamp);

    List<String> findAlreadyOutByDay(Timestamp timestamp);

    List<String> findAlreadyOutTooEarlyByDay(Timestamp timestamp);

    List<String> findAlreadyInTooLateByDay(Timestamp timestamp);
}
