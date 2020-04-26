package com.cc.blog.controller;

import com.cc.blog.model.Article;
import com.cc.blog.model.Message;
import com.cc.blog.model.User;
import com.cc.blog.service.ArticleService;
import com.cc.blog.service.MessageService;
import com.cc.blog.service.SuperAdminService;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/superAdmin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageService messageService;

    //登录相关

    /**
     * 管理界面显示
     *
     * @param request
     * @return
     */

    @RequestMapping("/admin")
    public String showSuperAdmin(HttpServletRequest request) {
        try {
            if (Tools.usernameSessionIsAdminValidate(request)) {
                return "admin";
            }
        } catch (Exception e) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    /**
     * 超级管理员登出
     *
     * @param request
     * @return 重定向：主页
     */

    @RequestMapping("/logout")
    public String superAdminLogout(HttpServletRequest request) {
        System.out.println("登出人员：" + Tools.usernameSessionValidate(request));
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }





    //管理模块

    /**
     * 删除用户
     *
     * @param deleteId
     * @return
     */

    @RequestMapping("/delete")
    @ResponseBody
    public Object deleteUserInfoByAjax(@RequestParam(value = "deleteId") int deleteId,
                                       HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        superAdminService.deleteUserById(deleteId, request);
        map.put("result", "1");
        return map;
    }

    /**
     * 测试点击Ajax替换数据
     *
     * @param userId
     * @return
     */

    @RequestMapping("/showUser")
    @ResponseBody
    public Object clickTest(@RequestParam(value = "userId") String userId,
                            HttpServletRequest request) {
        System.out.println("待查询用户ID：" + userId);
        System.out.println("总条数：" + userService.getUserCount());
        Map map = superAdminService.superAdminShowUser(userId, request);
        System.out.println("返回结果：" + map);
        return map;
    }

    /**
     * 将json展示到userList
     *
     * @param pageNum
     * @return
     * @throws JsonProcessingException
     */

    @RequestMapping("/showUserList")
    @ResponseBody
    public Object showUserList(@RequestParam(value = "pageNum") int pageNum,
                               HttpServletRequest request) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        try {
            Page<User> userPages = PageHelper.startPage(pageNum, 10);
            List<User> userList = userService.getUserAll(request);
            ObjectMapper mapper = new ObjectMapper();
            String userListToJson = mapper.writeValueAsString(userList);
            map.put("result", userListToJson);
        } catch (NullPointerException e) {
            map.put("result", "error");
        }
        return map;
    }

    /**
     * 将json展示到articleList
     *
     * @param pageNum
     * @return
     * @throws JsonProcessingException
     */

    @RequestMapping("/showArticleList")
    @ResponseBody
    public Object showArticleList(@RequestParam(value = "pageNum") int pageNum,
                                  HttpServletRequest request) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        try {
            Page<Article> articlePages = PageHelper.startPage(pageNum, 10);
            List<Article> articleList = articleService.getArticleAllByAdmin(request);
            ObjectMapper mapper = new ObjectMapper();
            String articleListToJson = mapper.writeValueAsString(articleList);
            map.put("result", articleListToJson);
        } catch (NullPointerException e) {
            map.put("result", "error");
        }
        return map;
    }

    /**
     * 将json展示到messageList
     *
     * @param pageNum
     * @return
     * @throws JsonProcessingException
     */

    @RequestMapping("/showMessageList")
    @ResponseBody
    public Object showMessageList(@RequestParam(value = "pageNum") int pageNum,
                                  HttpServletRequest request) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        try {
            Page<Message> messagePages = PageHelper.startPage(pageNum, 10);
            List<Message> messageList = messageService.getMessageAllByAdmin(request);
            ObjectMapper mapper = new ObjectMapper();
            String messageListToJson = mapper.writeValueAsString(messageList);
            map.put("result", messageListToJson);
        } catch (NullPointerException e) {
            map.put("result", "error");
        }
        return map;
    }

    /**
     * 通过页面名查找页面数
     *
     * @param pageName
     * @return
     */

    @RequestMapping("/selectPageNumByPageName")
    @ResponseBody
    public Object selectPageNumByPageName(@RequestParam(value = "pageName") String pageName) {
        Map<String, String> map = new HashMap<>();
        switch (pageName) {
            case "userList":
                map.put("result", Integer.toString(userService.getUserCount() / 10 + 1));
                break;
            case "articleList":
                map.put("result", Integer.toString(articleService.getArticleCount() / 10 + 1));
                break;
            case "messageList":
                map.put("result", Integer.toString(messageService.getMessageCount() / 10 + 1));
                break;
        }
        return map;
    }

}
