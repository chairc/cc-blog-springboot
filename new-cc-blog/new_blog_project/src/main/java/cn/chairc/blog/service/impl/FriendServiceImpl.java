package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.friend.FriendEntity;
import cn.chairc.blog.mapper.friend.FriendMapper;
import cn.chairc.blog.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<FriendEntity> listFriend(String type) {
        List<FriendEntity> friendEntityList = new ArrayList<>();
        try {
            if(ADMIN_STRING.equals(type)){
                friendEntityList = friendMapper.listFriendByAdmin();
            }else {
                friendEntityList = friendMapper.listFriendByIndex();
            }
        }catch (Exception e){
            log.error("");
        }
        return friendEntityList;
    }
}
