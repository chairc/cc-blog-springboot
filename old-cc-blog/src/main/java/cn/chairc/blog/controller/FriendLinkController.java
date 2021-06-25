package cn.chairc.blog.controller;

import cn.chairc.blog.model.FriendLink;
import cn.chairc.blog.service.FriendLinkService;
import cn.chairc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    @RequestMapping("/friendLink")
    public String showFriendLinkPageByPageHelper(Model model){
        List<FriendLink> list = friendLinkService.getFriendLinkAll();
        model.addAttribute("friendLink", list);
        return "friend_link";
    }
}
