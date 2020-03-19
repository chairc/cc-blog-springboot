package com.cc.blog.controller;

import com.cc.blog.model.User;
import com.cc.blog.service.SuperAdminService;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.invoke.empty.Empty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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

    /**
     * 显示超级管理员页面
     *
     * @return
     */

    @RequestMapping("admin")
    public String showSuperAdmin(HttpServletRequest request) {
        if (Tools.usernameSessionValidate(request) == null) {
            return "redirect:/superAdmin/login";
        }
        return "admin";
    }

    /**
     * 超级管理员登录
     *
     * @param request
     * @return
     */

    @RequestMapping("/login")
    public String superAdminLogin(HttpServletRequest request) {
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
    public String logout(HttpServletRequest request) {
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
    public Map<String, String> loginUserByAjax(@RequestParam("username") String username,
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

    @RequestMapping("/clickTest")
    @ResponseBody
    public Map<String, String> clickTest(@RequestParam("userId") int userId) {
        Map<String, String> map = new HashMap<>();

        User user = userService.getUserById(userId);
        String username = user.getUser_common_username();
        System.out.println(username);
        //map.put("result", username);
        map.put("result", user.toString());
        return map;
    }

}
