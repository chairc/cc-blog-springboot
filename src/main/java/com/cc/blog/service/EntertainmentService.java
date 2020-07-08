package com.cc.blog.service;

import com.cc.blog.model.Entertainment;
import com.cc.blog.model.ResultSet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EntertainmentService {

    /**
     * 主页获取所有娱乐功能
     *
     * @return List<Entertainment>
     */

    List<Entertainment> getEntertainmentAll();

    /**
     * 管理员获取所有娱乐功能
     *
     * @return resultSet
     */

    ResultSet getEntertainmentAllByAdmin();

    /**
     * wps自动邀请
     *
     * @param uid
     * @return resultSet
     */

    ResultSet wpsAutoInvite(String uid);
}
