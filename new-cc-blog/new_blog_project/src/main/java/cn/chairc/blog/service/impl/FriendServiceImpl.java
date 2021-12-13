package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.friend.FriendEntity;
import cn.chairc.blog.entity.friend.FriendInsertEntity;
import cn.chairc.blog.mapper.friend.FriendMapper;
import cn.chairc.blog.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairc
 * @date 2021/5/12 19:52
 */
@Service
public class FriendServiceImpl implements FriendService {

    private static final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

    private static final String ADMIN_STRING = "admin";

    private FriendMapper friendMapper;

    @Autowired
    public FriendServiceImpl(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }

    /**
     * 查询友人帐列表
     *
     * @param type       查询类型，index或admin
     * @param search     搜索
     * @param searchType 搜索类型
     * @return 友人帐列表
     */

    @Override
    public List<FriendEntity> listFriend(String type, String search, String searchType) {
        List<FriendEntity> friendEntityList = new ArrayList<>();
        try {
            if (ADMIN_STRING.equals(type)) {
                friendEntityList = friendMapper.listFriendByAdmin(search, searchType);
            } else {
                friendEntityList = friendMapper.listFriendByIndex();
            }
        } catch (Exception e) {
            log.error("获取友人帐列表失败，原因：{}", e.toString());
        }
        return friendEntityList;
    }

    @Override
    public ResultSet insertFriend(FriendInsertEntity friendInsertEntity, BindingResult bindingResult) {
        return null;
    }
}
