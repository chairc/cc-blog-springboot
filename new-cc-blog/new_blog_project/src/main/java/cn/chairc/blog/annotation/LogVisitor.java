package cn.chairc.blog.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 访问日志注解
 *
 * @author chairc
 * @date 2021/5/25 17:31
 */
@Target({ElementType.TYPE, ElementType.METHOD}) //  定义注解的作用目标**作用范围字段、枚举的常量/方法（作用于方法）
@Retention(RetentionPolicy.RUNTIME) //  注解会在class字节码文件中存在，在运行时可以通过反射获取到（运行时有效）
@Component
@Documented
public @interface LogVisitor {

    String value() default "";

    String level() default "";
}
