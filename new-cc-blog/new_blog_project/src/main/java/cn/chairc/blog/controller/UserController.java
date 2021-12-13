package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserRegisteredEntity;
import cn.chairc.blog.entity.user.UserUpdateForgotPasswordEntity;
import cn.chairc.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author chairc
 * @date 2021/5/5 20:43
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

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
     * @param userRegisteredEntity 注册类
     * @param httpServletRequest   http请求
     * @param bindingResult        绑定结构
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-2")
    @RequestMapping("/registered")
    @ResponseBody
    public ResultSet userRegistered(@Valid @RequestBody UserRegisteredEntity userRegisteredEntity,
                                    BindingResult bindingResult,
                                    HttpServletRequest httpServletRequest) {
        return userService.userRegistered(userRegisteredEntity, httpServletRequest, bindingResult);
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

    /**
     * 用户找回密码
     *
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-3")
    @RequestMapping("/updateForgotPassword")
    @ResponseBody
    public ResultSet updateForgoPasswordByAjax(@Valid @RequestBody UserUpdateForgotPasswordEntity userUpdateForgotPasswordEntity,
                                               BindingResult bindingResult) {

        return userService.updateForgoPassword(userUpdateForgotPasswordEntity,bindingResult);
    }

}
