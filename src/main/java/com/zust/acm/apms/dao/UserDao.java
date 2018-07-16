package com.zust.acm.apms.dao;

import com.zust.acm.apms.entity.UserEntity;

public interface UserDao {
    int updateUser(UserEntity user);

    int addUser(UserEntity user);


    UserEntity getUserById(int userId);
}
