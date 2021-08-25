package cn.chairc.blog.service;

import cn.chairc.blog.entity.friend.FriendEntity;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/12 19:52
 */
public interface FriendService {

    List<FriendEntity> listFriend(String type);
}
