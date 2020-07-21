package cn.chairc.blog.service;


import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.model.FriendLink;

import java.util.List;

public interface FriendLinkService {

    /**
     * 主页获取文章
     *
     * @return List<FriendLink>
     */

    List<FriendLink> getFriendLinkAllToIndex();

    /**
     * 获取所有友链
     *
     * @return List<FriendLink>
     */

    List<FriendLink> getFriendLinkAll();

    /**
     * 管理员获取所有友链
     *
     * @return DataResultSet
     */

    DataResultSet getFriendLinkAllByAdmin(int pageNum);

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

    /**
     * 通过id获取友链
     *
     * @param id
     * @return
     */

    FriendLink getUserFriendLink(int id);

}
