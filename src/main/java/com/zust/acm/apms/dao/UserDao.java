package com.zust.acm.apms.dao;

import com.zust.acm.apms.entity.UserEntity;

import java.util.List;

public interface UserDao {
    int updateUser(UserEntity user);

    int addUser(UserEntity user);

    List<String> getUserIdList();

    UserEntity getUserById(String userId);
}
