package cn.chairc.blog.utils;

import cn.chairc.blog.BlogApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther chairc
 * @date 2021/6/7 15:19
 */
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class CommonUtilTest {

    @Test
    void createRandomPrivateId() {
    }

    @Test
    void createUserRandomPrivateId() {
    }

    @Test
    void createVerificationCode() {
        for (int i=0;i<20;i++){
            System.out.println(CommonUtil.createVerificationCode());
        }
    }

    @Test
    void getUserIp() {
    }

    @Test
    void getBrowserVersion() {
    }

    @Test
    void getSystemVersion() {
    }

    @Test
    void sessionValidate() {
    }
}