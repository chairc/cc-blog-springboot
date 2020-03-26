package com.cc.blog.dao;

import com.cc.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuperAdminDao {

    /**
     * 超级管理员登录
     *
     * @param username
     * @param password
     * @param privateId
     * @return
     */

    Integer superAdminLogin(String username, String password, String privateId);

    /**
     * 通过ID删除用户信息
     *
     * @param id
     */

    void deleteUserById(int id);

    /**
     * 更新用户信息
     *
     * @param user
     */

    void updateUser(User user);
}
