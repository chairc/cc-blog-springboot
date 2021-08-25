package cn.chairc.blog.mapper.friend;

import cn.chairc.blog.entity.friend.FriendEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/6/27 22:05
 */
@Mapper
public interface FriendMapper {
    List<FriendEntity> listFriendByIndex();

    List<FriendEntity> listFriendByAdmin();

    FriendEntity getFriendByEmail(String userEmail);

    FriendEntity getFriendByWebsite(String userWebsite);

    boolean getFriendIsExist(String userPrivateId);

    void insertFriend(FriendEntity friendEntity);

    void deleteFriend(String userPrivateId);

    void updateFriend(FriendEntity friendEntity);
}
