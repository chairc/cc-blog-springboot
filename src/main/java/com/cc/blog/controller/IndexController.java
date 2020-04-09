package com.cc.blog.controller;

import com.cc.blog.model.Article;
import com.cc.blog.model.Message;
import com.cc.blog.service.ArticleService;
import com.cc.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        String[] randomPages = new String[]{"article", "message"};
        String randomPage = "";
        int randomNum = (int) (Math.random() * 2);
        randomPage = "redirect:/" + randomPages[randomNum];
        return randomPage;
    }
}
