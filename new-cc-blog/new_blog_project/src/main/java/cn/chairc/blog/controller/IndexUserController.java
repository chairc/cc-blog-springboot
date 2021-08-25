package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.utils.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户页面跳转控制层
 *
 * @author chairc
 * @date 2021/5/9 14:35
 */
@Controller
@RequestMapping("/user")
public class IndexUserController {

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/index")
    public String showUserPage(Model model) {
        model.addAttribute("username", CommonUtil.sessionValidate("username"));
        return "user/user";
    }

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/dashboard")
    public String showUserDashboardPage() {
        return "user/dashboard/user_dashboard";
    }

    @LogVisitor(level = "LEVEL-2")
    @RequestMapping("/userSetting")
    public String showUserSettingPage() {
        return "common/setting";
    }
}
