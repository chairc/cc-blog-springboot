package cn.chairc.blog.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 * ErrorController中方法二的配置
 *
 * @author chairc
 * @date 2021/6/4 17:23
 */
@Configuration
public class ErrorExceptionConfig {

    /*
     * 对于ErrorController中方法二的解释
     * 如果使用配置WebServerFactoryCustomizer方法，在第一次启动浏览器后与后台交互控制台会莫名进入/error/404（浏览无影响）
     * 如果浏览器不重新启动，控制台不会显示/error/404
     * 屏蔽WebServerFactoryCustomizer方法采用重写errorHtml则不会显示/error/404
     */

    /*
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizerIn400() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
        };
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizerIn401() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
        };
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizerIn404() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        };
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizerIn500() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        };
    }
    */
}
