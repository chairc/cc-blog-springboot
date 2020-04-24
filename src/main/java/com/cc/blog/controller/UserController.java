package com.cc.blog.controller;

import com.cc.blog.model.User;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
    public Map<String, String> registeredUserByAjax(@RequestParam(value = "username", required = false) String username,
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
        //map集合用来存放返回值
        Map<String, String> map = new HashMap<String, String>();
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
                System.out.println(user);
                userService.insertUser(user,request);
                map.put("result", "1");
            } else {
                map.put("result", "0");
            }
        } else {
            map.put("result", "-1");
        }

        return map;
    }

    /**
     * 登录页面
     *
     * @param request
     * @return 根据session判断页面跳转
     */

    @RequestMapping("/login")
    public String showUserLoginPage(HttpServletRequest request) {
        String username = Tools.usernameSessionValidate(request);
        System.out.println("当前Session：" + username);
        if (username == null) {
            return "login";
        } else if (username.equals("SuperAdmin")) {
            return "redirect:/superAdmin/admin";
        }
        System.out.println("登录人员：" + username);

        return "redirect:/user";
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
        request.getSession().invalidate();

        return "redirect:/";
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
    public Map<String, String> userLoginByAjax(@RequestParam(value = "username") String username,
                                               @RequestParam(value = "password") String password,
                                               HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();

        if (username != null && password != null && !username.equals("")
                && !password.equals("") && !username.equals("SuperAdmin")) {
            if (userService.loginUser(username, password) == 1) {
                request.getSession().setAttribute("username", username);
                map.put("result", "1");
            } else {
                map.put("result", "0");
            }
        } else {
            map.put("result", "-1");
        }
        return map;
    }

    /**
     * 展示所有用户信息
     *
     * @return mav
     */

    @RequestMapping("/user")
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
     * @see MessageController#showMessagePageByPageHelper(Model, int) (Model, int)
     */

    @RequestMapping("/user/{pageNum}")
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
        return userService.getUserById(id,request).toString();
    }

    @RequestMapping("/getUser/{id}")
    public ModelAndView getUserByIdTest1(@PathVariable int id,
                                         HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userService.getUserById(id,request));
        return mav;
    }


}
