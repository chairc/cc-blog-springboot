package com.cc.blog.service;

import com.cc.blog.model.ResultSet;
import com.cc.blog.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SuperAdminService {

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
     * @return resultSet
     */

    ResultSet superAdminShowUser(String userId, HttpServletRequest request);

}
