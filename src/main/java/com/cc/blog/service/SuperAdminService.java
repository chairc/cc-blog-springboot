package com.cc.blog.service;

import com.cc.blog.dao.SuperAdminDao;
import com.cc.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {

    @Autowired
    SuperAdminDao superAdminDao;

    /**
     * 超级管理员登录
     *
     * @param username
     * @param password
     * @param privateId
     * @return
     */

    public Integer superAdminLogin(String username, String password, String privateId) {
        return superAdminDao.superAdminLogin(username, password, privateId);
    }

    /**
     * 通过ID删除用户信息
     *
     * @param id
     */

    public void deleteUserById(int id) {
        superAdminDao.deleteUserById(id);
    }

    /**
     * 更新用户信息
     *
     * @param user
     */

    public void updateUser(User user) {
        superAdminDao.updateUser(user);
    }
}
