package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.mail.MailEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.service.MailService;
import cn.chairc.blog.service.VerificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author chairc
 * @date 2021/6/7 14:50
 */
@Service
public class VerificationServiceImpl implements VerificationService {

    private static final Logger log = LoggerFactory.getLogger(VerificationServiceImpl.class);

    private static final String EMAIL_VERIFICATION_PATTERN = "/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/";

    private MailService mailService;

    private UserMapper userMapper;

    @Autowired
    public VerificationServiceImpl(MailService mailService, UserMapper userMapper) {
        this.mailService = mailService;
        this.userMapper = userMapper;
    }

    /**
     * 注册发送验证码
     *
     * @param registeredEmail 注册邮箱
     * @return 成功或异常
     */

    @Override
    public ResultSet registeredVerificationCode(String registeredEmail) {
        ResultSet resultSet = new ResultSet();
        try {
            //  验证格式
            Pattern pattern = Pattern.compile(EMAIL_VERIFICATION_PATTERN);
            if (registeredEmail == null || "".equals(registeredEmail)) {
                resultSet.fail("注册邮箱不能为空");
                return resultSet;
            }
            if (pattern.matcher(registeredEmail).matches()) {
                resultSet.fail("注册邮箱格式不正确");
                return resultSet;
            }
            if (userMapper.getUserEmailIsExist(registeredEmail)) {
                resultSet.fail("验证码邮箱存在");
                return resultSet;
            }
            //  这里发送邮件
            MailEntity mailEntity = new MailEntity();
            mailEntity.setUserEmail(registeredEmail);
            mailEntity.setEmailType("registered");
            mailService.sendAutomaticEmail(mailEntity);
            resultSet.ok("我们给你的邮箱发送了一封激活邮件，请及时查收");
        } catch (Exception e) {
            log.error("验证码发送失败");
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 忘记密码发送验证码
     *
     * @param forgotEmail 找回邮箱
     * @return 成功或异常
     */

    @Override
    public ResultSet forgotPasswordVerificationCode(String forgotEmail) {
        ResultSet resultSet = new ResultSet();
        try {
            //  验证格式
            Pattern pattern = Pattern.compile(EMAIL_VERIFICATION_PATTERN);
            if (forgotEmail == null || "".equals(forgotEmail)) {
                resultSet.fail("注册邮箱不能为空");
                return resultSet;
            }
            if (pattern.matcher(forgotEmail).matches()) {
                resultSet.fail("注册邮箱格式不正确");
                return resultSet;
            }
            if (!userMapper.getUserEmailIsExist(forgotEmail)) {
                resultSet.fail("验证码邮箱不存在");
                return resultSet;
            }
            MailEntity mailEntity = new MailEntity();
            mailEntity.setUserEmail(forgotEmail);
            mailEntity.setEmailType("forgotPassword");
            mailService.sendAutomaticEmail(mailEntity);
            resultSet.ok("我们给你的邮箱发送了一封找回密码邮件，请及时查收");
        } catch (Exception e) {
            log.error("验证码发送失败");
            resultSet.interServerError();
        }
        return resultSet;
    }
}
