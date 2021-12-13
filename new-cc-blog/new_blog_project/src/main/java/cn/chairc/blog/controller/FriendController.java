package cn.chairc.blog.controller;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.friend.FriendInsertEntity;
import cn.chairc.blog.service.FriendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author chairc
 * @date 2021/5/12 19:51
 */
@Controller
@RequestMapping("/api/friend")
public class FriendController {

    private static final Logger log = LoggerFactory.getLogger(FriendController.class);

    private FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    public ResultSet insertFriendApplication(@Valid @RequestBody FriendInsertEntity friendInsertEntity,
                                             BindingResult bindingResult) {
        return friendService.insertFriend(friendInsertEntity, bindingResult);
    }
}
