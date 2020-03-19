package com.cc.blog.dao;

import com.cc.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    /**
     * 获取所有用户
     *
     * @return
     */

    List<User> getUserAll();

    /**
     * 通过ID获取用户
     *
     * @param id
     * @return
     */

    User getUserById(int id);

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

    /**
     * 新增用户信息
     *
     * @param user
     */

    void insertUser(User user);

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


}
