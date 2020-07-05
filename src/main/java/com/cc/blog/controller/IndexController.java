package com.cc.blog.controller;

import com.cc.blog.model.Article;
import com.cc.blog.model.Message;
import com.cc.blog.service.ArticleService;
import com.cc.blog.service.MessageService;
import com.cc.blog.util.Tools;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MessageService messageService;

    /**
     * 主页显示
     *
     * @return mav
     */

    @RequestMapping("/")
    public ModelAndView showIndexPage() {
        ModelAndView mav = new ModelAndView("index");
        List<Article> article = articleService.getArticleAllToIndex();
        List<Message> message = messageService.getMessageAllToIndex();
        List<Message> messageWeight = messageService.getMessageAllToIndexByWeight();
        mav.addObject("article", article);
        mav.addObject("message", message);
        mav.addObject("message_weight", messageWeight);
        return mav;
    }

    /**
     * 随机跳转页面
     *
     * @return randomPage
     */

    @RequestMapping("/randomJump")
    public String randomJumpPage() {
        String[] randomPages = new String[]{"article/1", "message/1", "friendLink/1"};
        String randomPage = "";
        int randomNum = (int) (Math.random() * 3);
        randomPage = "redirect:/" + randomPages[randomNum];
        return randomPage;
    }
}
