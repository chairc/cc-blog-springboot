package com.cc.blog.service.impl;

import com.cc.blog.mapper.UserDao;
import com.cc.blog.model.User;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserServiceImpl userService;

    /**
     * 获取所有用户
     *
     * @return
     */

    public List<User> getUserAll() {
        return userDao.getUserAll();
    }

    /**
     * 通过ID获取用户
     *
     * @param id
     * @return
     */

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */

    public Integer loginUser(String username, String password) {
        Integer flag = userDao.loginUser(username, password);
        System.out.println("登陆状态（1:success/2:failure）：" + flag);
        return flag;
    }

    /**
     * 查找私有ID获取用户
     *
     * @param privateId
     * @return
     */

    public Integer getUserPrivateId(String privateId) {
        return userDao.getUserPrivateId(privateId);
    }

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * 新增用户信息
     *
     * @param user
     */

    public void insertUser(User user) {
        Integer flag = userService.getUserPrivateId(user.getUser_common_private_id());
        while (flag == 1) {
            user.setUser_common_private_id(Tools.CreateUserRandomPrivateId());
            flag = userService.getUserPrivateId(user.getUser_common_private_id());
        }
        userDao.insertUser(user);
    }

    /**
     * 通过ID删除用户信息（暂时停用此方法）
     *
     * @param id
     */

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    /**
     * 更新用户信息（暂时停用此方法）
     *
     * @param user
     */

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /**
     * 用户名唯一性验证
     *
     * @param username
     * @return
     */

    public Integer usernameValidate(String username) {
        return userDao.usernameValidate(username);
    }

    /**
     * 通过QQ快速登录来验证openId
     *
     * @param openId
     * @return
     */

    public Integer openIdValidate(String openId) {
        return userDao.openIdValidate(openId);
    }

    /**
     * 获取用户数
     *
     * @return
     */

    public Integer getUserCount() {
        return userDao.getUserCount();
    }

}