package com.cc.blog.service;

import com.cc.blog.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    /**
     * 获取所有用户
     *
     * @return
     */

    List<User> getUserAll(HttpServletRequest request);

    /**
     * 通过ID获取用户
     *
     * @param id
     * @return
     */
    User getUserById(int id,HttpServletRequest request);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    Integer loginUser(String username, String password);

    /**
     * 查找私有ID获取用户
     *
     * @param privateId
     * @return
     */
    Integer getUserPrivateId(String privateId,HttpServletRequest request);

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username,HttpServletRequest request);

    /**
     * 新增用户信息
     *
     * @param user
     */
    void insertUser(User user,HttpServletRequest request);

    /**
     * 通过ID删除用户信息（暂时停用此方法）
     *
     * @param id
     */
    void deleteUserById(int id,HttpServletRequest request);

    /**
     * 更新用户信息（暂时停用此方法）
     *
     * @param user
     */
    void updateUser(User user,HttpServletRequest request);

    /**
     * 用户名唯一性验证
     *
     * @param username
     * @return
     */
    Integer usernameValidate(String username);

    /**
     * 通过QQ快速登录来验证openId
     *
     * @param openId
     * @return
     */
    Integer openIdValidate(String openId);

    /**
     * 获取用户数
     *
     * @return
     */

    Integer getUserCount();
}
