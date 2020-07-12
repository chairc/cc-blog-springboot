package cn.chairc.blog.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    /**
     * 注解@Autowired使用TestTemplate报错配置
     *
     * @param restTemplateBuilder
     * @return
     */
    @Bean
    public RestTemplate restTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        //注意：Spring Boot不再自动定义一个RestTemplate，
        // 而是定义了一个RestTemplateBuilder允许您更好地控制所RestTemplate创建的对象。
        return restTemplateBuilder.build();
    }
}
