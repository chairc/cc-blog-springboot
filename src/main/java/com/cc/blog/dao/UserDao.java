package com.cc.blog.dao;

import com.cc.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    List<User> getUserAll();

    User getUserById(int id);

    Integer loginUser(String username, String password);

    Integer getUserPrivateId(String privateId);

    User getUserByUsername(String username);

    void deleteUserById(int id);

    void updateUser(User user);

    void insertUser(User user);
}
