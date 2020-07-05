package com.cc.blog.controller;

import com.cc.blog.model.FriendLink;
import com.cc.blog.model.ResultSet;
import com.cc.blog.service.FriendLinkService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FriendLinkController {

    @Autowired
    FriendLinkService friendLinkService;

    @RequestMapping("/friendLink/{pageNum}")
    public String showFriendLinkPageByPageHelper(Model model,
                                                 @PathVariable int pageNum){
        Page<FriendLink> pages = PageHelper.startPage(pageNum, 20);
        List<FriendLink> list = friendLinkService.getFriendLinkAll();
        model.addAttribute("friendLink", list);
        Tools.indexPageHelperJudge(model,pageNum,pages,20);
        return "friend_link";
    }
}
