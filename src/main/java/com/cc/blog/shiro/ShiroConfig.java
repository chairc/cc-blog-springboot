package com.cc.blog.shiro;

import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
         * Shiro过滤器
         * anon:无需认证访问
         * authc：必须认证访问
         * user:使用rememberMe功能直接访问
         * perms:资源必须得到资源权限访问
         * role:资源必须得到角色授权访问
         */
        //filterMap过滤器Map，顺序存放拦截url
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        //放行页面
        filterMap.put("/user/login", "anon");
        filterMap.put("/superAdmin/login","anon");

        //授权过滤器
        //filterMap.put("/user/userIndex","perms[user:user]");
        filterMap.put("/user/userIndex","perms[user:admin]");
        filterMap.put("/superAdmin/*","perms[user:superAdmin]");

        //权限过滤器
        filterMap.put("/user/*", "authc");
        //filterMap.put("/superAdmin/*", "authc");

        //设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        //失败后跳转登陆页面
        shiroFilterFactoryBean.setLoginUrl("/user/login");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    @Bean
    public RememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        //cookie设置为7天
        simpleCookie.setMaxAge(604800);
        cookieRememberMeManager.setCookie(simpleCookie);

        return cookieRememberMeManager;
    }
}
