package com.cc.blog.service.impl;

import com.cc.blog.mapper.UserDao;
import com.cc.blog.model.Permission;
import com.cc.blog.model.Role;
import com.cc.blog.model.User;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     *
     * @return
     */

    @Override
    public List<User> getUserAll(HttpServletRequest request) {
        if (Tools.usernameSessionIsAdminValidate(request)) {
            return userDao.getUserAll();
        }
        return null;
    }

    /**
     * 通过ID获取用户
     *
     * @param id
     * @return
     */

    @Override
    public User getUserById(int id, HttpServletRequest request) {
        if (Tools.usernameSessionIsAdminValidate(request)) {
            return userDao.getUserById(id);
        }
        return null;
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */

    @Override
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

    @Override
    public Integer getUserPrivateId(String privateId, HttpServletRequest request) {
        if (Tools.usernameSessionIsAdminValidate(request)) {
            return userDao.getUserPrivateId(privateId);
        }
        return null;

    }

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return
     */

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    /**
     * 通过OpenId获取用户名
     *
     * @param openId
     * @return
     */

    @Override
    public User getUserByOpenId(String openId) {
        return userDao.getUserByOpenId(openId);
    }

    /**
     * 新增用户信息
     *
     * @param user
     */

    @Override
    public void insertUser(User user, HttpServletRequest request) {
        Integer flag = userService.getUserPrivateId(user.getUser_common_private_id(), request);
        while (flag == 1) {
            user.setUser_common_private_id(Tools.CreateUserRandomPrivateId());
            flag = userService.getUserPrivateId(user.getUser_common_private_id(), request);
        }
        userDao.insertUser(user);
    }

    /**
     * 通过ID删除用户信息（暂时停用此方法）
     *
     * @param id
     */

    @Override
    public void deleteUserById(int id, HttpServletRequest request) {
        if (Tools.usernameSessionIsAdminValidate(request)) {
            userDao.deleteUserById(id);
        }

    }

    /**
     * 更新用户信息（暂时停用此方法）
     *
     * @param user
     */

    @Override
    public void updateUser(User user, HttpServletRequest request) {
        if (Tools.usernameSessionIsAdminValidate(request)) {
            userDao.updateUser(user);
        }

    }

    /**
     * 用户名唯一性验证
     *
     * @param username
     * @return
     */

    @Override
    public Integer usernameValidate(String username) {
        return userDao.usernameValidate(username);
    }

    /**
     * 通过QQ快速登录来验证openId
     *
     * @param openId
     * @return
     */

    @Override
    public Integer openIdValidate(String openId) {
        return userDao.openIdValidate(openId);
    }

    /**
     * 获取用户数
     *
     * @return
     */

    @Override
    public Integer getUserCount() {
        return userDao.getUserCount();
    }

    /**
     * 获取用户权限
     *
     * @return
     */

    @Override
    public Role getUserRole(String role) {
        return userDao.getUserRole(role);
    }

    @Override
    public Permission getUserPermission(String permission) {
        return userDao.getUserPermission(permission);
    }

}
