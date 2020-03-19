package com.cc.blog.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuperAdminDao {

    /**
     * 超级管理员登录
     *
     * @param username
     * @param password
     * @param privateId
     * @return
     */

    Integer superAdminLogin(String username, String password, String privateId);
}
