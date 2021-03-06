package cn.chairc.blog.controller;

import cn.chairc.blog.model.*;
import cn.chairc.blog.service.FriendLinkService;
import cn.chairc.blog.service.HeadImageService;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HeadImageService headImageService;

    @Autowired
    private FriendLinkService friendLinkService;

    @Value("${head-image.default-head-image}")
    private String DEFAULT_HEAD_IMAGE;

    @Value("${head-image.default-head-image-man}")
    private String DEFAULT_HEAD_IMAGE_MAN;

    @Value("${head-image.default-head-image-woman}")
    private String DEFAULT_HEAD_IMAGE_WOMAN;

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
                user.setUser_common_friend_link_id(0);
                user.setUser_common_head_image_id(0);
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
                user.setUser_safe_role("5");
                user.setUser_safe_permission("6");
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
     * @return 根据session判断页面跳转
     */

    @RequestMapping("/user/login")
    public String showUserLoginPage() {
        try {
            String username = Tools.usernameSessionValidate();
            System.out.println("当前Session：" + username);
            User user = userService.getUserByUsername(username);
            Role role = userService.getUserRole(user.getUser_safe_role());
            System.out.println("登录人员：" + username);
            if (username == null) {//用户名判空
                System.out.println("用户名判空");
                return "login";
            } else if (role.getRole_name().equals("SuperAdmin")) {
                //如果查找到的角色是SuperAdmin，则进入管理员页面
                System.out.println("超级管理员登录");
                return "redirect:/superAdmin/admin";
            } else if (!role.getRole_name().equals("SuperAdmin")) {
                System.out.println("普通用户");
                return "redirect:/user/userIndex";
            }
        } catch (Exception e) {
            System.out.println("异常1");
            return "login";
        }
        return "login";
    }

    /**
     * Ajax登录
     *
     * @param username
     * @param password
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
            //Shiro记录session
            Session session = subject.getSession();
            session.setAttribute("username", username);
            //记录privateID
            //HttpSession httpSession = request.getSession();
            String privateId = userService.getUserByUsername(username).getUser_common_private_id();
            session.setAttribute("privateId", privateId);
            //更新用户登录记录
            User user = new User();
            user.setUser_common_username(username);
            user.setUser_common_password(password);
            user.setUser_safe_ip(Tools.getUserIp(request));
            user.setUser_safe_logtime(Tools.getServerTime());
            user.setUser_safe_browser(Tools.getBrowserVersion(request));
            user.setUser_safe_system(Tools.getSystemVersion(request));
            userService.updateUserLoginLog(user, "normalLogin");
            System.out.println("登录用户为：" + username + "用户私有ID为：" + privateId);
            resultSet.success("登录成功");
        } catch (UnknownAccountException e) {
            //用户名不存在
            resultSet.fail("登录失败，用户名不存在");
        } catch (IncorrectCredentialsException e) {
            //密码错误
            resultSet.fail("登录失败，密码错误");
        }
        System.out.println(resultSet);
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
     * @return 重定向：主页
     */

    @RequestMapping("/logout")
    public String userLogout() {
        System.out.println("登出人员：" + Tools.usernameSessionValidate());
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/login";
    }

    /**
     * 没有操作权限
     *
     * @return no_auth.html
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
    public String showMessagePage(Model model) {
        String username = Tools.usernameSessionValidate();
        User user = userService.getUserByUsername(username);
        String userHeadImage, userFriendLink;
        model.addAttribute("privateId", user.getUser_common_private_id());
        model.addAttribute("openId", user.getUser_common_open_id());
        model.addAttribute("username", user.getUser_common_username());
        model.addAttribute("nickname", user.getUser_common_nickname());
        if (user.getUser_common_head_image_id() != 0) {
            HeadImage headImage = headImageService.getUserHeadImage(user.getUser_common_head_image_id());
            userHeadImage = headImage.getHead_image_url();
        } else {
            if (user.getUser_secret_sex().equals("男")) {
                userHeadImage = DEFAULT_HEAD_IMAGE_MAN;
            } else if (user.getUser_secret_sex().equals("女")) {
                userHeadImage = DEFAULT_HEAD_IMAGE_WOMAN;
            } else {
                userHeadImage = DEFAULT_HEAD_IMAGE;
            }
        }
        model.addAttribute("headImage", userHeadImage);
        if (user.getUser_common_friend_link_id() != 0) {
            FriendLink friendLink = friendLinkService.getUserFriendLink(user.getUser_common_friend_link_id());
            userFriendLink = friendLink.getFriend_link_url();
        } else {
            userFriendLink = "暂无友链";
        }
        model.addAttribute("friendLink", userFriendLink);
        model.addAttribute("realName", user.getUser_secret_real_name());
        model.addAttribute("phone", user.getUser_secret_phone());
        model.addAttribute("email", user.getUser_secret_email());
        model.addAttribute("birthday", user.getUser_secret_birthday());
        model.addAttribute("sex", user.getUser_secret_sex());
        model.addAttribute("age", user.getUser_secret_age());
        model.addAttribute("wechat", user.getUser_secret_wechat());
        model.addAttribute("qq", user.getUser_secret_qq());
        model.addAttribute("weibo", user.getUser_secret_weibo());
        model.addAttribute("address", user.getUser_secret_address());
        model.addAttribute("logtime", user.getUser_safe_logtime());
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
                                              @PathVariable int pageNum) {
        //pageNum传进来页面号
        Page<User> pages = PageHelper.startPage(pageNum, 10);
        List<User> message = userService.getUserAll();
        model.addAttribute("user", message);
        Tools.indexPageHelperJudge(model, pageNum, pages, 10);
        return "user";
    }

    @RequestMapping("/getUser1/{id}")
    public String getUserById(@PathVariable int id) {
        return userService.getUserById(id).toString();
    }

    @RequestMapping("/getUser/{id}")
    public ModelAndView getUserByIdTest1(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("user", userService.getUserById(id));
        return mav;
    }
}
