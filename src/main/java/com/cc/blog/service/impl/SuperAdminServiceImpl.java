package com.cc.blog.service.impl;

import com.cc.blog.mapper.SuperAdminDao;
import com.cc.blog.model.User;
import com.cc.blog.service.SuperAdminService;
import com.cc.blog.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private SuperAdminDao superAdminDao;

    @Autowired
    private UserService userService;

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

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */

    public Map superAdminShowUser(String userId){
        Map<String, String> map = new HashMap<>();
        try {
            int getUserId = Integer.parseInt(userId);
            if (getUserId >= 0 && getUserId <= userService.getUserCount()) {
                User user = userService.getUserById(getUserId);
                String username = user.getUser_common_username();
                System.out.println(username);
                ObjectMapper mapper = new ObjectMapper();
                String userToJson = mapper.writeValueAsString(user);
                map.put("result", userToJson);
                map.put("code", "1");
            } else if (getUserId < 0) {
                map.put("code", "-1");
            } else if (getUserId > userService.getUserCount()) {
                map.put("code", "-2");
            } else {
                map.put("code", "error");
            }

        }catch (Exception e){
            map.put("code", "error");
        }
        return map;
    }
}
