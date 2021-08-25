package cn.chairc.blog.service.impl;

import cn.chairc.blog.BlogApplication;
import cn.chairc.blog.entity.friend.FriendEntity;
import cn.chairc.blog.mapper.friend.FriendMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther chairc
 * @date 2021/6/27 22:51
 */
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class FriendServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(FriendServiceImplTest.class);

    private FriendMapper friendMapper;

    @Autowired
    public FriendServiceImplTest(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }

    @Test
    void listFriend() {
    }

    @Test
    void updateFriend(){
        try {
            FriendEntity friendEntity = new FriendEntity();
            friendEntity.setFriendUserPrivateId("user_000000000000_00000");
//            friendEntity.setFriendName("123");
//            friendEntity.setFriendIntroduction("123");
            friendEntity.setFriendEmail("2222");
//            friendEntity.setFriendWebsite("123");
//            friendEntity.setFriendIsDelete(1);
            friendEntity.setFriendIsHide(1);
            log.info(friendEntity.toString());
            friendMapper.updateFriend(friendEntity);
            log.info("success");
        }catch (Exception e){
            log.error("error:{}",e.toString());
        }
    }
}