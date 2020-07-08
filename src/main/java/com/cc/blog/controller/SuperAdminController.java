package com.cc.blog.controller;

import com.cc.blog.model.Article;
import com.cc.blog.model.Message;
import com.cc.blog.model.ResultSet;
import com.cc.blog.model.User;
import com.cc.blog.service.*;
import com.cc.blog.util.Tools;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @Autowired
    private FriendLinkService friendLinkService;

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
            HttpSession session = request.getSession();
            String usernameIsAdmin = (String) session.getAttribute("username");
            User user = userService.getUserByUsername(usernameIsAdmin);
            //管理员登录需要验证角色，以防止空指针异常
            if (Tools.usernameSessionIsAdminValidate(user.getUser_safe_role())) {
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
     * @return 重定向：主页
     */

    @RequestMapping("/logout")
    public String superAdminLogout() {
        System.out.println("登出人员：" + Tools.usernameSessionValidate());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }





    //管理模块

    /**
     * 更新用户
     *
     * @return
     */

    @RequestMapping("/update")
    @ResponseBody
    public ResultSet updateUserInfoByAjax(@RequestParam(value = "id") int id) {
        ResultSet resultSet = new ResultSet();
        resultSet.success("删除成功");
        return resultSet;
    }

    /**
     * 删除用户
     *
     * @param deleteId
     * @return
     */

    @RequestMapping("/delete")
    @ResponseBody
    public ResultSet deleteUserInfoByAjax(@RequestParam(value = "deleteId") int deleteId) {
        ResultSet resultSet = new ResultSet();
        superAdminService.deleteUserById(deleteId);
        resultSet.success("删除成功");
        return resultSet;
    }

    /**
     * 测试点击Ajax替换数据
     *
     * @param userId
     * @return
     */

    @RequestMapping("/showUser")
    @ResponseBody
    public ResultSet clickTest(@RequestParam(value = "userId") String userId) {

        System.out.println("待查询用户ID：" + userId);
        System.out.println("总条数：" + userService.getUserCount());
        ResultSet resultSet = superAdminService.superAdminShowUser(userId);
        System.out.println("返回结果：" + resultSet.getData());
        return resultSet;
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
    public ResultSet showUserList(@RequestParam(value = "pageNum") int pageNum) throws JsonProcessingException {
        return userService.getUserAllByAdmin(pageNum);
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
    public ResultSet showArticleList(@RequestParam(value = "pageNum") int pageNum) throws JsonProcessingException {
        return articleService.getArticleAllByAdmin(pageNum);
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
    public ResultSet showMessageList(@RequestParam(value = "pageNum") int pageNum,
                                  HttpServletRequest request) throws JsonProcessingException {
        return messageService.getMessageAllByAdmin(pageNum);
    }

    /**
     * 将json展示到friendLinkList
     *
     * @param pageNum
     * @return
     * @throws JsonProcessingException
     */

    @RequestMapping("/showfriendLinkList")
    @ResponseBody
    public ResultSet showFriendLinkPageByAdmin(@RequestParam(value = "pageNum") int pageNum) throws JsonProcessingException{
        return friendLinkService.getFriendLinkAllByAdmin(pageNum);
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
        ResultSet resultSet = new ResultSet();
        switch (pageName) {
            case "userList":
                resultSet.setData(Integer.toString((userService.getUserCount() - 1) / 10 + 1));
                break;
            case "articleList":
                resultSet.setData(Integer.toString((articleService.getArticleCount() - 1) / 10 + 1));
                break;
            case "messageList":
                resultSet.setData(Integer.toString((messageService.getMessageCount() - 1) / 10 + 1));
                break;
            case "userMobileList":
                resultSet.setData(Integer.toString((userService.getUserCount() - 1) / 10 + 1));
                break;
            case "articleMobileList":
                resultSet.setData(Integer.toString((articleService.getArticleCount() - 1) / 10 + 1));
                break;
            case "messageMobileList":
                resultSet.setData(Integer.toString((messageService.getMessageCount() - 1) / 10 + 1));
                break;
        }
        return resultSet;
    }

}
