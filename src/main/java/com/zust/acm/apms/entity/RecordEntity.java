package com.zust.acm.apms.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "record", schema = "apms")
public class RecordEntity {
    private UserEntity user;
    private Timestamp time;
    private String ip;
    private String recordId;

    public RecordEntity(){

    }

    public RecordEntity(UserEntity user, Timestamp time, String ip) {
        this.user = user;
        this.time = time;
        this.ip = ip;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "recordId")
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
