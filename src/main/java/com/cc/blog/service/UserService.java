package com.cc.blog.service;

import com.cc.blog.model.Permission;
import com.cc.blog.model.Role;
import com.cc.blog.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    User getUserById(int id, HttpServletRequest request);

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

    Integer getUserPrivateId(String privateId);

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */

    User getUserByUsername(String username);

    /**
     * 通过openId获得用户名
     *
     * @param openId
     * @return
     */

    User getUserByOpenId(String openId);

    /**
     * 新增用户信息
     *
     * @param user
     */

    void insertUser(User user);

    /**
     * 通过ID删除用户信息（暂时停用此方法）
     *
     * @param id
     */
    void deleteUserById(int id);

    /**
     * 更新用户信息（暂时停用此方法）
     *
     * @param user
     */
    void updateUser(User user);

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

    /**
     * 获取用户权限
     *
     * @return
     */

    Role getUserRole(String role);

    Permission getUserPermission(String permission);
}
