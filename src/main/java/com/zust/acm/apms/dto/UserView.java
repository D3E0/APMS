package com.zust.acm.apms.dto;

import com.zust.acm.apms.entity.UserEntity;

public class UserView {
    private UserEntity userEntity;
    private String inTime;
    private String outTime;

    public UserView(UserEntity userEntity, String inTime, String outTime) {
        this.userEntity = userEntity;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public UserView() {
    }
    public UserView(UserEntity user) {
        this.userEntity = user;
    }


    @Override
    public String toString() {
        return "UserView{" +
                "userEntity=" + userEntity +
                ", inTime='" + inTime + '\'' +
                ", outTime='" + outTime + '\'' +
                '}';
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
