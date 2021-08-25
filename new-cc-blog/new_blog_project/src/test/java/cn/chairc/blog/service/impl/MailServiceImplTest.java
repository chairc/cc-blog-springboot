package cn.chairc.blog.service.impl;

import cn.chairc.blog.BlogApplication;
import cn.chairc.blog.entity.mail.MailEntity;
import cn.chairc.blog.service.MailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @auther chairc
 * @date 2021/5/9 22:44
 */
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class MailServiceImplTest {

    private MailService mailService;

    @Autowired
    public MailServiceImplTest(MailService mailService) {
        this.mailService = mailService;
    }

    @Test
    void sendEmail() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setEmailType("registered");
        mailEntity.setUserEmail("974833488@qq.com");
        mailService.sendAutomaticEmail(mailEntity);
    }
}