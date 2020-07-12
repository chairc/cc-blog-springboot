package cn.chairc.blog.mapper;

import cn.chairc.blog.model.FriendLink;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendLinkDao {

    /**
     * 主页获取文章
     *
     */

    List<FriendLink> getFriendLinkAllToIndex();

    /**
     * 管理员获取所有友链
     */

    List<FriendLink> getFriendLinkAllByAdmin();

    /**
     * 普通获取所有友链
     */

    List<FriendLink> getFriendLinkAll();

    /**
     * 通过私有id获取用户
     */

    FriendLink getFriendLink(String privateId);

    /**
     * 新增友链
     */

    void insertFriendLink(FriendLink friendLink);

    /**
     * 更新友链
     */

    void updateFriendLink(FriendLink friendLink);

    /**
     * 删除友链
     */

    void deleteFriendLink(String privateId);

}
