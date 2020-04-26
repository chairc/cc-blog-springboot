package com.cc.blog.service.impl;

import com.cc.blog.mapper.SuperAdminDao;
import com.cc.blog.model.User;
import com.cc.blog.service.SuperAdminService;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private SuperAdminDao superAdminDao;

    @Autowired
    private UserService userService;

    /**
     * 通过ID删除用户信息
     *
     * @param id
     */

    public void deleteUserById(int id,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            superAdminDao.deleteUserById(id);
        }

    }

    /**
     * 更新用户信息
     *
     * @param user
     */

    public void updateUser(User user,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            superAdminDao.updateUser(user);
        }

    }

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return
     */

    public Map superAdminShowUser(String userId, HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        try {
            int getUserId = Integer.parseInt(userId);
            if (getUserId >= 0 && getUserId <= userService.getUserCount()) {
                User user = userService.getUserById(getUserId,request);
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
