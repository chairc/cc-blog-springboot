package cn.chairc.blog.service.impl;

import cn.chairc.blog.BlogApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @auther chairc
 * @date 2021/7/5 16:10
 */
@SpringBootTest(classes = BlogApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class FileServiceImplTest {

    @Test
    void uploadHeadFile() {
    }

    @Test
    void uploadArticleFile() {
    }
}