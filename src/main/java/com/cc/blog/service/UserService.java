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

    public List<User> getUserAll() {
        return userDao.getUserAll();
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public Integer loginUser(String username, String password) {
        Integer flag = userDao.loginUser(username, password);
        System.out.println("登陆状态（1:success/2:failure）：" + flag);
        return flag;
    }

    public void deleteUserById(int id) {
        userDao.deleteUserById(id);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    public Integer getUserPrivateId(String privateId) {
        Integer flag = userDao.getUserPrivateId(privateId);
        return flag;
    }

}
