package com.cc.blog.controller;

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
import org.springframework.ui.Model;
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
    private int mistake_num = 0;

    @Autowired
    SuperAdminService superAdminService;

    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @Autowired
    MessageService messageService;


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
        if (mistake_num < 5) {
            if (username != null && password != null && privateId != null
                    && !username.equals("") && !password.equals("") && !privateId.equals("")) {
                if (superAdminService.superAdminLogin(username, password, privateId) == 1) {
                    request.getSession().setAttribute("username", username);
                    map.put("result", "1");
                } else {
                    mistake_num++;
                    map.put("result", "0");
                }
            } else {
                mistake_num++;
                map.put("result", "-1");
            }
        } else {
            map.put("result", "error");
        }
        System.out.println(mistake_num);

        return map;
    }

    /**
     * 用于重置次数的作弊码
     *
     * @return
     */

    @RequestMapping("/help/clean")
    public String cleanLimitSuperAdminLogin() {
        mistake_num = 0;
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
    public Map<String, String> deleteUserInfoByAjax(@RequestParam(value = "deleteId") int deleteId) {
        Map<String, String> map = new HashMap<>();
        superAdminService.deleteUserById(deleteId);
        map.put("result", "1");
        return map;
    }

    @RequestMapping("/management/people")
    public String showManagementPeople(Model model) {

        return "admin";
    }

    @RequestMapping("/management/article")
    public String showManagementAritcle(Model model) {

        return "admin";
    }

    @RequestMapping("/management/message")
    public String showManagementMessage(Model model) {

        return "admin";
    }

    @RequestMapping("/management/photo")
    public String showManagementPhoto(Model model) {

        return "admin";
    }

    /**
     * 测试点击Ajax替换数据
     *
     * @param userId
     * @return
     */

    @RequestMapping("/showUser")
    @ResponseBody
    public Map<String, String> clickTest(@RequestParam("userId") int userId) throws JsonProcessingException {
        System.out.println("待查询用户：" + userId);
        System.out.println("总条数：" + userService.getUserCount());
        Map<String, String> map = new HashMap<>();
        if (userId >= 0 && userId <= userService.getUserCount()) {
            User user = userService.getUserById(userId);
            String username = user.getUser_common_username();
            System.out.println(username);
            ObjectMapper mapper = new ObjectMapper();
            String userToJson = mapper.writeValueAsString(user);
            map.put("result", userToJson);
        } else if (userId < 0) {
            map.put("result", "-1");
        } else if (userId > userService.getUserCount()) {
            map.put("result", "-2");
        } else {
            map.put("result", "error");
        }
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
    public Map<String, String> showUserList(@RequestParam("pageNum") int pageNum) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        Page<User> userPages = PageHelper.startPage(pageNum, 10);
        List<User> userList = userService.getUserAll();
        ObjectMapper mapper = new ObjectMapper();
        String userListToJson = mapper.writeValueAsString(userList);
        map.put("result", userListToJson);
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
    public Map<String, String> selectPageNumBypageName(@RequestParam("pageName") String pageName) {
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
