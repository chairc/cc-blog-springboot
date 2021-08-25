package cn.chairc.blog.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author chairc
 * @date 2021/8/16 17:51
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface LogArticle {

    String value() default "";

    String level() default "";

    String operateType() default "";

    String description() default "";
}
