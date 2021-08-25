package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserRegisteredEntity;
import cn.chairc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chairc
 * @date 2021/5/5 20:43
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录
     * 方法说明：敏感信息，参数不能记录到日志文件
     *
     * @param userEmail          用户电子邮件
     * @param userPassword       用户密码
     * @param httpServletRequest http请求
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-2")
    @RequestMapping("/login")
    @ResponseBody
    public ResultSet userLogin(@RequestParam(value = "userEmail") String userEmail,
                               @RequestParam(value = "userPassword") String userPassword,
                               HttpServletRequest httpServletRequest) {
        return userService.userLogin(userEmail, userPassword, httpServletRequest);
    }

    /**
     * 用户登出
     * 方法说明：无敏感信息
     *
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/logout")
    @ResponseBody
    public ResultSet userLogout() {
        return userService.userLogout();
    }

    /**
     * 用户注册
     * 方法说明：敏感信息，参数不能记录到日志文件
     *
     * @param registeredUsername         注册用户名
     * @param registeredEmail            注册邮箱
     * @param registeredPassword         注册密码
     * @param registeredRetypePassword   注册缺人密码
     * @param registeredVerificationCode 注册邮箱激活码
     * @param httpServletRequest         http请求
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-2")
    @RequestMapping("/registered")
    @ResponseBody
    public ResultSet userRegistered(@RequestParam(value = "registeredUsername") String registeredUsername,
                                    @RequestParam(value = "registeredEmail") String registeredEmail,
                                    @RequestParam(value = "registeredPassword") String registeredPassword,
                                    @RequestParam(value = "registeredRetypePassword") String registeredRetypePassword,
                                    @RequestParam(value = "registeredVerificationCode") String registeredVerificationCode,
                                    HttpServletRequest httpServletRequest) {
        //  注册类中可自定义参数
        UserRegisteredEntity userRegisteredEntity = new UserRegisteredEntity();
        userRegisteredEntity.setRegisteredUsername(registeredUsername);
        userRegisteredEntity.setRegisteredEmail(registeredEmail);
        userRegisteredEntity.setRegisteredPassword(registeredPassword);
        userRegisteredEntity.setRegisteredRetypePassword(registeredRetypePassword);
        userRegisteredEntity.setRegisteredVerificationCode(registeredVerificationCode);
        return userService.userRegistered(userRegisteredEntity, httpServletRequest);
    }

    /**
     * 修改用户密码
     * 方法说明：敏感信息，参数不能记录到日志文件
     *
     * @param oldPassword      旧密码
     * @param newPassword      新密码
     * @param newPasswordAgain 再次输入新密码
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-3")
    @RequestMapping("/updateUserPassword")
    @ResponseBody
    public ResultSet updateUserPasswordByAjax(@RequestParam(value = "oldPassword") String oldPassword,
                                              @RequestParam(value = "newPassword") String newPassword,
                                              @RequestParam(value = "newPasswordAgain") String newPasswordAgain) {
        return userService.updateUserPassword(oldPassword, newPassword, newPasswordAgain);
    }

}
