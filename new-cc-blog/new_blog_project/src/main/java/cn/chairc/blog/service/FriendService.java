package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.friend.FriendEntity;
import cn.chairc.blog.entity.friend.FriendInsertEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/12 19:52
 */
public interface FriendService {

    /**
     * 查询友人帐列表
     *
     * @param type       查询类型，index或admin
     * @param search     搜索
     * @param searchType 搜索类型
     * @return 友人帐列表
     */

    List<FriendEntity> listFriend(String type, String search, String searchType);

    ResultSet insertFriend(FriendInsertEntity friendInsertEntity, BindingResult bindingResult);
}
