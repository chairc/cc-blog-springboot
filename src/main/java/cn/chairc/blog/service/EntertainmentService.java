package cn.chairc.blog.service;

import cn.chairc.blog.model.Entertainment;
import cn.chairc.blog.model.ResultSet;

import java.util.List;

public interface EntertainmentService {

    /**
     * 分主页获取娱乐功能
     *
     * @return List<Entertainment>
     */

    List<Entertainment> getEntertainmentAllToIndex();

    /**
     * 分页获取所有娱乐功能
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
