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
    //记录输入错误总数
    private int mistakeNum = 0;

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MessageService messageService;

    @RequestMapping("/admin")
    public String showSuperAdmin() {
        return "admin";
    }

    /**
     * 超级管理员登录
     *
     * @param request
     * @return
     */

    @RequestMapping("/login")
    public String showSuperAdminLoginPage(HttpServletRequest request) {
        String username = Tools.usernameSessionValidate(request);
        if (username == null) {
            return "super_admin_login";
        }
        System.out.println("登录人员：" + username);
        //超级管理员登录界面
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
        request.getSession().invalidate();

        return "redirect:/";
    }

    /**
     * 超级管理员Ajax登录
     *
     * @param username
     * @param password
     * @param request
     * @return map
     */

    @RequestMapping("/superAdminLoginByAjax")
    @ResponseBody
    public Map<String, String> superAdminLoginByAjax(@RequestParam("username") String username,
                                                     @RequestParam("password") String password,
                                                     @RequestParam("privateId") String privateId,
                                                     HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        if (mistakeNum < 5) {
            if (username != null && password != null && privateId != null
                    && !username.equals("") && !password.equals("") && !privateId.equals("")) {
                if (superAdminService.superAdminLogin(username, password, privateId) == 1) {
                    request.getSession().setAttribute("username", username);
                    map.put("result", "1");
                } else {
                    mistakeNum++;
                    map.put("result", "0");
                }
            } else {
                mistakeNum++;
                map.put("result", "-1");
            }
        } else {
            map.put("result", "error");
        }
        System.out.println(mistakeNum);

        return map;
    }

    /**
     * 用于重置次数的作弊码
     *
     * @return
     */

    @RequestMapping("/help/clean")
    public String cleanLimitSuperAdminLogin() {
        mistakeNum = 0;
        return "redirect:/superAdmin/login";
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
    public Object deleteUserInfoByAjax(@RequestParam(value = "deleteId") int deleteId) {
        Map<String, String> map = new HashMap<>();
        superAdminService.deleteUserById(deleteId);
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
    public Object clickTest(@RequestParam("userId") String userId) {
        System.out.println("待查询用户ID：" + userId);
        System.out.println("总条数：" + userService.getUserCount());
        Map map = superAdminService.superAdminShowUser(userId);
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
    public Object showUserList(@RequestParam("pageNum") int pageNum) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        Page<User> userPages = PageHelper.startPage(pageNum, 10);
        //需修改到service层中
        List<User> userList = userService.getUserAll();
        ObjectMapper mapper = new ObjectMapper();
        String userListToJson = mapper.writeValueAsString(userList);
        map.put("result", userListToJson);
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
    public Object showArticleList(@RequestParam("pageNum") int pageNum) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        Page<Article> articlePages = PageHelper.startPage(pageNum, 10);
        //需修改到service层中
        List<Article> articleList = articleService.getArticleAllByAscendingOrder();
        ObjectMapper mapper = new ObjectMapper();
        String articleListToJson = mapper.writeValueAsString(articleList);
        map.put("result", articleListToJson);
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
    public Object showMessageList(@RequestParam("pageNum") int pageNum) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        Page<Message> messagePages = PageHelper.startPage(pageNum, 10);
        //需修改到service层中
        List<Message> messageList = messageService.getMessageAllByAscendingOrder();
        ObjectMapper mapper = new ObjectMapper();
        String messageListToJson = mapper.writeValueAsString(messageList);
        map.put("result", messageListToJson);
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
    public Object selectPageNumByPageName(@RequestParam("pageName") String pageName) {
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
