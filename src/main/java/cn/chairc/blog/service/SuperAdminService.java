package cn.chairc.blog.service;

import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.model.User;

public interface SuperAdminService {

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
     * 根据ID查询用户信息
     * @param userId
     * @return resultSet
     */

    ResultSet superAdminShowUser(String userId);

}
