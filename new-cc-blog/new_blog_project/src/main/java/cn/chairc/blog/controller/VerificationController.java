package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chairc
 * @date 2021/6/7 14:50
 */
@RequestMapping("/api/verification")
@Controller
public class VerificationController {

    private VerificationService verificationService;

    @Autowired
    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    /**
     * 注册发送邮箱激活验证码
     * 方法说明：无敏感信息
     *
     * @param registeredEmail 注册邮箱
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("/registered")
    @ResponseBody
    public ResultSet createRegisteredVerificationCode(@RequestParam(value = "registeredEmail") String registeredEmail) {
        return verificationService.registeredVerificationCode(registeredEmail);
    }

    /**
     * 找回密码发送邮箱验证
     * 方法说明：无敏感信息
     *
     * @param forgotEmail 找回邮箱
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-1")
    @RequestMapping("forgotPassword")
    @ResponseBody
    public ResultSet createForgotPasswordVerificationCode(@RequestParam(value = "forgotEmail") String forgotEmail) {
        return verificationService.forgotPasswordVerificationCode(forgotEmail);
    }
}
