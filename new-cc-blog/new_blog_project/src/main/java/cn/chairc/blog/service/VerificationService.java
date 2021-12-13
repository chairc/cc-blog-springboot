package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;

/**
 * @author chairc
 * @date 2021/6/7 14:50
 */
public interface VerificationService {

    /**
     * 注册发送验证码
     *
     * @param registeredEmail 注册邮箱
     * @return 成功或异常
     */

    ResultSet registeredVerificationCode(String registeredEmail);

    /**
     * 忘记密码发送验证码
     *
     * @param forgotEmail 找回邮箱
     * @return 成功或异常
     */

    ResultSet forgotPasswordVerificationCode(String forgotEmail);
}
