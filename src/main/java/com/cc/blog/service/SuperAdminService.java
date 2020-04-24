package com.cc.blog.service;

import com.cc.blog.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    void deleteUserById(int id, HttpServletRequest request);

    /**
     * 更新用户信息
     *
     * @param user
     */

    void updateUser(User user, HttpServletRequest request);

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */

    Map superAdminShowUser(String userId, HttpServletRequest request);

}
