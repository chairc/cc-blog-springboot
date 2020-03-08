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
    private MessageService messageService;

    public IndexController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("index");
        List<Article> article = articleService.getArticleAll_index();
        List<Message> message = messageService.getMessageAll_index();
        List<Message> message_weight = messageService.getMessageAll_index_weight();
        mav.addObject("article", article);
        mav.addObject("message", message);
        mav.addObject("message_weight", message_weight);
        return mav;
    }

    @RequestMapping("/randomJump")
    public String randomJump() {
        String[] randomPages = new String[]{"article", "message"};
        String randomPage = "";
        int randomNum = (int) (Math.random() * 2);
        randomPage = "redirect:/" + randomPages[randomNum];
        return randomPage;
    }
}
