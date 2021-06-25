package cn.chairc.blog;

import cn.chairc.blog.util.Tools;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Test
    void contextLoads() {

        for(int i = 0;i<51;i++){
            String privateId = Tools.CreateRandomPrivateId(1);
            System.out.println(privateId);
        }
    }

}
