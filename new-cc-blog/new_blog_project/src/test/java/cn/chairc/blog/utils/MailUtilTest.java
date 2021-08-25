package cn.chairc.blog.utils;

import cn.chairc.blog.BlogApplication;
import cn.chairc.blog.entity.verification.VerificationCodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

/**
 * @auther chairc
 * @date 2021/5/10 17:18
 */
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class MailUtilTest {

    @Value("${spring.mail.verification-path}")
    private String EMAIL_VERIFICATION_PATH;

    @Test
    void createAutomaticEmailUrl() throws ParseException {
        VerificationCodeEntity verificationCodeEntity = new VerificationCodeEntity();
        verificationCodeEntity.setUserPrivateId("123456");
        verificationCodeEntity.setUserVerificationCode(CommonUtil.createRandomPrivateId("code"));
        verificationCodeEntity.setUserVerificationCodeType("registered");
        System.out.println(MailUtil.createAutomaticEmailUrl(EMAIL_VERIFICATION_PATH, verificationCodeEntity));
    }

}
