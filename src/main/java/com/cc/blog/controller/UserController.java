package com.cc.blog.controller;

import com.cc.blog.model.ResultSet;
import com.cc.blog.model.Role;
import com.cc.blog.model.User;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //用户基本操作

    /**
     * 注册页面
     *
     * @return registered页面
     */

    @RequestMapping("/registered")
    public String showRegisteredPage() {
        return "registered";
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param nickname
     * @param phone
     * @param email
     * @param sex
     * @param birthday
     * @param wechat
     * @param qq
     * @param weibo
     * @param question
     * @param answer
     * @param request
     * @return 1:成功 0：username password answer为空 -1：username重复
     */

    @RequestMapping("/registeredUserByAjax")
    @ResponseBody
    public ResultSet registeredUserByAjax(@RequestParam(value = "username", required = false) String username,
                                                    @RequestParam(value = "password", required = false) String password,
                                                    @RequestParam(value = "nickname", required = false) String nickname,
                                                    @RequestParam(value = "phone", required = false) String phone,
                                                    @RequestParam(value = "email", required = false) String email,
                                                    @RequestParam(value = "sex", required = false) String sex,
                                                    @RequestParam(value = "birthday", required = false) String birthday,
                                                    @RequestParam(value = "wechat", required = false) String wechat,
                                                    @RequestParam(value = "qq", required = false) String qq,
                                                    @RequestParam(value = "weibo", required = false) String weibo,
                                                    @RequestParam(value = "question", required = false) String question,
                                                    @RequestParam(value = "answer", required = false) String answer,
                                                    HttpServletRequest request) {
        ResultSet resultSet = new ResultSet();
        //验证username的唯一性
        if (userService.usernameValidate(username).equals(0)) {
            if (username != null && password != null && !username.equals("") && !password.equals("") && !answer.equals("")) {
                User user = new User();
                //将数据放入user中
                user.setUser_common_private_id(Tools.CreateUserRandomPrivateId());
                user.setUser_common_username(username);
                user.setUser_common_password(password);
                user.setUser_common_nickname(nickname);
                user.setUser_secret_phone(phone);
                user.setUser_secret_email(email);
                user.setUser_secret_sex(sex);
                user.setUser_secret_birthday(birthday);
                user.setUser_secret_wechat(wechat);
                user.setUser_secret_qq(qq);
                user.setUser_secret_weibo(weibo);
                user.setUser_safe_question(question);
                user.setUser_safe_answer(answer);
                user.setUser_safe_logtime(Tools.getServerTime());
                user.setUser_safe_ip(Tools.getUserIp(request));
                user.setUser_safe_system(Tools.getSystemVersion(request));
                user.setUser_safe_browser(Tools.getBrowserVersion(request));
                user.setUser_safe_role("1");
                user.setUser_safe_permission("1");
                System.out.println(user);
                userService.insertUser(user);
                resultSet.setCode("1");
                resultSet.setMsg("提交成功");
            } else {
                resultSet.setCode("0");
                resultSet.setMsg("正确填写用户名、密码和安全答案");
            }
        } else {
            resultSet.setCode("0");
            resultSet.setMsg("提交失败，用户名存在");
        }

        return resultSet;
    }

    /**
     * 登录页面
     *
     * @param request
     * @return 根据session判断页面跳转
     */

    @RequestMapping("/user/login")
    public String showUserLoginPage(HttpServletRequest request) {
        String username = Tools.usernameSessionValidate(request);
        System.out.println("当前Session：" + username);
        try {
            User user = userService.getUserByUsername(username);
            Role role = userService.getUserRole(user.getUser_safe_role());
            if (username == null) {//用户名判空
                return "login";
            } else if (role.getRole_name().equals("SuperAdmin")) {//如果查找到的角色是SuperAdmin，则进入管理员页面
                return "redirect:/superAdmin/admin";
            }
            System.out.println("登录人员：" + username);
        } catch (Exception e) {
            return "login";
        }
        return "redirect:/user/userIndex";
    }

    /**
     * Ajax登录
     *
     * @param username
     * @param password
     * @param request
     * @return map
     */

    @RequestMapping("/loginUserByAjax")
    @ResponseBody
    public ResultSet userLoginByAjax(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     HttpServletRequest request) {
        ResultSet resultSet = new ResultSet();
        //1.获取Shiro的Subject
        Subject subject = SecurityUtils.getSubject();
        //2.封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3.执行登陆方法
        try {
            subject.login(token);
            request.getSession().setAttribute("username", username);
            resultSet.success("登录成功");
        } catch (UnknownAccountException e) {
            //用户名不存在
            resultSet.fail("登录失败，用户名不存在");
        } catch (IncorrectCredentialsException e) {
            //密码错误
            resultSet.fail("登录失败，密码错误");
        }
        return resultSet;
    }

    /**
     * 用于重置次数的作弊码（暂时取消）
     *
     * @return
     */

    @RequestMapping("/help/clean")
    public String cleanLimitSuperAdminLogin() {
        return "redirect:/user/login";
    }

    /**
     * 登出
     *
     * @param request
     * @return 重定向：主页
     */

    @RequestMapping("/logout")
    public String userLogout(HttpServletRequest request) {
        System.out.println("登出人员：" + Tools.usernameSessionValidate(request));
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/login";
    }

    /**
     * 没有操作权限
     *
     * @return
     */

    @RequestMapping("/noAuth")
    public String noAuth() {
        return "no_auth";
    }

    /**
     * 展示所有用户信息
     *
     * @return mav
     */

    @RequestMapping("/user/userIndex")
    public String showMessagePage(Model model,
                                  HttpServletRequest request) {
        int page = 1;
        //第一页开始，一页十条数据
        Page<User> pages = PageHelper.startPage(1, 10);
        List<User> list = userService.getUserAll(request);
        model.addAttribute("user", list);
        model.addAttribute("pageNum", page);
        //前一页设为1，下一页设为这一页+1
        model.addAttribute("pageNumPrev", 1);
        model.addAttribute("pageNumNext", page + 1);
        //获取后台总条数，进行计算出页面数
        model.addAttribute("pageTotal", pages.getTotal() / 10 + 1);
        return "user";
    }

    /**
     * 用户列表分页显示
     * <p>
     * {@link MessageController}
     * 注解参考see中MessageController#showMessageByPage(Model, int)中方法
     *
     * @param model
     * @param pageNum
     * @return admin页面
     * @see MessageController#showMessagePageByPageHelper (Model, int, HttpServletRequest)
     */

    @RequestMapping("/user/userIndex/{pageNum}")
    public String showMessagePageByPageHelper(Model model,
                                              @PathVariable int pageNum,
                                              HttpServletRequest request) {
        //pageNum传进来页面号
        Page<User> pages = PageHelper.startPage(pageNum, 10);
        List<User> message = userService.getUserAll(request);
        model.addAttribute("user", message);
        if (pageNum == 1) {
            //如果当前页处于第一页，则上一页设为1
            model.addAttribute("pageNumPrev", 1);
        } else {
            //否则上一页设为当前页-1
            model.addAttribute("pageNumPrev", pageNum - 1);
        }
        if (pageNum == pages.getTotal() / 10 + 1) {
            //如果当前页为最后一页，则下一页一直是最后一页
            model.addAttribute("pageNumNext", pages.getTotal() / 10 + 1);
        } else {
            //否则，下一页为当前页+1
            model.addAttribute("pageNumNext", pageNum + 1);
        }
        model.addAttribute("pageTotal", pages.getTotal() / 10 + 1);
        return "user";
    }

    @RequestMapping("/getUser1/{id}")
    public String getUserById(@PathVariable int id,
                              HttpServletRequest request) {
        return userService.getUserById(id, request).toString();
    }

    @RequestMapping("/getUser/{id}")
    public ModelAndView getUserByIdTest1(@PathVariable int id,
                                         HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userService.getUserById(id, request));
        return mav;
    }


}
