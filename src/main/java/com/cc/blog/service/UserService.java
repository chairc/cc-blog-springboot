package com.cc.blog.service;

import com.cc.blog.dao.UserDao;
import com.cc.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

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
        Integer flag = userDao.getUserPrivateId(privateId);
        return flag;
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
        userDao.insertUser(user);
    }

    /**
     * 通过QQ快速登录新增用户信息
     *
     * @param user
     */

    public void insertUserByQQ(User user) {
        userDao.insertUserByQQ(user);
    }

    /**
     * 通过ID删除用户信息
     *
     * @param id
     */

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

}
