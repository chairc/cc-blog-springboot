package com.cc.blog.service;


import com.cc.blog.model.FriendLink;
import com.cc.blog.model.ResultSet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FriendLinkService {

    /**
     * 获取所有友链
     *
     * @return ResultSet
     */

    List<FriendLink> getFriendLinkAll();

    /**
     * 管理员获取所有友链
     *
     * @return ResultSet
     */

    ResultSet getFriendLinkAllByAdmin(int pageNum);

    /**
     * 通过私有id获取友链
     *
     * @return ResultSet
     */

    ResultSet getFriendLinkByPrivateId(String privateId);

    /**
     * 新增友链
     *
     * @return ResultSet
     */

    ResultSet insertFriendLink(FriendLink friendLink);

    /**
     * 更新友链
     *
     * @return ResultSet
     */

    ResultSet updateFriendLink(FriendLink friendLink);

    /**
     * 通过私有id删除友链
     */
    void deleteFriendLink(String privateId);

}
