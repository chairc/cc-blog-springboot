package cn.chairc.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chairc
 * @date 2021/5/5 22:03
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload-file.head-file-path}")
    private String UPLOAD_HEAD_PATH;

    @Value("${upload-file.article-file-path}")
    private String UPLOAD_ARTICLE_PATH;

    /**
     * 添加资源处理程序
     *
     * @param registry 资源处理程序注册表
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源路径映射
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //头像图片资源路径映射
        registry.addResourceHandler("/path/head/**").addResourceLocations("file:" + UPLOAD_HEAD_PATH);
        //文章图片资源路径映射
        registry.addResourceHandler("/path/article/**").addResourceLocations("file:" + UPLOAD_ARTICLE_PATH);
    }
}
