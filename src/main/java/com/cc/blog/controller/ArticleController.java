package com.cc.blog.controller;

import com.cc.blog.model.Article;
import com.cc.blog.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 文章列表显示
     *
     * @param model
     * @return article页面
     */

    @RequestMapping("/article")
    public String showArticle(Model model) {
        int page = 1;
        Page<Article> pages = PageHelper.startPage(1, 8);
        List<Article> list = articleService.getArticleAll();
        model.addAttribute("article", list);
        model.addAttribute("pageNum", page);
        model.addAttribute("pageNumPrev", 1);
        model.addAttribute("pageNumNext", page + 1);
        model.addAttribute("pageTotal", pages.getTotal() / 8 + 1);
        return "article";
    }

    /**
     * 文章列表分页显示
     *
     * @param model
     * @param pageNum
     * @return article页面
     */

    @RequestMapping("/article/{pageNum}")
    public String showArticle1(Model model,
                               @PathVariable int pageNum) {
        Page<Article> pages = PageHelper.startPage(pageNum, 8);
        List<Article> list = articleService.getArticleAll();
        model.addAttribute("article", list);
        if (pageNum == 1) {
            model.addAttribute("pageNumPrev", 1);
        } else {
            model.addAttribute("pageNumPrev", pageNum - 1);
        }
        if (pageNum == pages.getTotal() / 8 + 1) {
            model.addAttribute("pageNumNext", pages.getTotal() / 8 + 1);
        } else {
            model.addAttribute("pageNumNext", pageNum + 1);
        }
        model.addAttribute("pageTotal", pages.getTotal() / 8 + 1);
        return "article";
    }

    /**
     * 文章显示
     *
     * @param model
     * @param privateId
     * @return show_article页面
     */

    @RequestMapping("/article/title/{privateId}")
    public String showArticleByPrivateId(Model model,
                                         @PathVariable String privateId) {
        List<Article> list = articleService.getArticleByPrivateId(privateId);
        model.addAttribute("article", list);
        return "show_article";
    }

    @RequestMapping("/articleTest")
    public String articleTest(){
        return "article_test";
    }
}
