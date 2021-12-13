package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.mail.MailEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.service.MailService;
import cn.chairc.blog.utils.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * @author chairc
 * @date 2021/5/9 21:59
 */
@Service
public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    private MailSender mailSender;

    @Autowired
    public MailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.from}")
    private String emailFrom;

    @Value("${spring.mail.verification-path}")
    private String emailVerificationPath;

    /**
     * 自动发送邮件
     *
     * @param mail 邮件类
     */

    @Override
    public void sendAutomaticEmail(MailEntity mail) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailFrom);
            simpleMailMessage.setTo(mail.getUserEmail());
            String mailSubject = "";
            String mailContent = "";
            //  将验证信息存入session
            String mailVerification = CommonUtil.createVerificationCode();
            session.setAttribute("verificationEmail",mail.getUserEmail());
            session.setAttribute("verificationCode",mailVerification);
            session.setAttribute("verificationType",mail.getEmailType());
            //  自动信息发送
            switch (mail.getEmailType()) {
                case "registered":
                    mailSubject = "小虚无的下午茶博客用户邮箱注册激活";
                    mailContent = "激活验证码为：" + mailVerification;
                    log.info("registered");
                    break;
                case "forgotPassword":
                    mailSubject = "小虚无的下午茶博客用户邮箱找回密码";
                    mailContent = "找回密码验证码为：" + mailVerification;
                    log.info("forgotPassword");
                    break;
                case "subscribeInfo":
                    mailSubject = "小虚无的下午茶博客用户邮箱订阅信息";
                    mailContent = "订阅信息为：XXXXXXXXXX";
                    log.info("subscribeInfo");
                    break;
                default:
                    break;
            }
            mail.setEmailSubject(mailSubject);
            mail.setEmailContent(mailContent);
            simpleMailMessage.setSubject(mail.getEmailSubject());
            simpleMailMessage.setText(mail.getEmailContent());
            mailSender.send(simpleMailMessage);
            log.info("通过{}向邮箱{}自动发送一下{}类信息：{}", emailFrom, mail.getUserEmail(), mail.getEmailType(), mail.getEmailContent());
        } catch (Exception e) {
            log.error("邮件自动发送异常错误，错误原因{}", e.toString());
        }
    }

    /**
     * 手动发送邮件
     *
     * @param mail 邮件类
     * @return 成功或异常
     */

    @Override
    public ResultSet sendManualEmail(MailEntity mail) {
        ResultSet resultSet = new ResultSet();
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(emailFrom);
            simpleMailMessage.setTo(mail.getUserEmail());
            simpleMailMessage.setSubject(mail.getEmailSubject());
            simpleMailMessage.setText(mail.getEmailContent());
            mailSender.send(simpleMailMessage);
            log.info("通过{}向邮箱{}手动发送邮件", emailFrom, mail.getUserEmail());
            resultSet.ok("发送邮件成功");
        } catch (Exception e) {
            log.error("邮件手动发送异常错误，错误原因{}", e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }
}
