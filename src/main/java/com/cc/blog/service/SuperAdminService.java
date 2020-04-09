package com.cc.blog.service;

import com.cc.blog.model.User;

public interface SuperAdminService {

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
