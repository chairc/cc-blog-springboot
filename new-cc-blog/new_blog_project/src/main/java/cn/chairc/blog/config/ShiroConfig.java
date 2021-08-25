package cn.chairc.blog.config;

import cn.chairc.blog.shiro.UserRealm;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chairc
 * @date 2021/5/5 20:45
 */
@Configuration
public class ShiroConfig {

    private static Logger log = LoggerFactory.getLogger(ShiroConfig.class);

    /**
     * shiro过滤器配置
     *
     * @param securityManager 安全管理器
     * @return shiro过滤器配置
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //  设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
         * Shiro过滤器
         * anon:无需认证访问
         * authc：必须认证访问
         * user:使用rememberMe功能直接访问
         * perms:资源必须得到资源权限访问
         * role:资源必须得到角色授权访问
         */
        //  filterMap过滤器Map，顺序存放拦截url
        Map<String, String> filterMap = new LinkedHashMap<String, String>();

        //  放行页面
        filterMap.put("/login", "anon");
        filterMap.put("/registered", "anon");
        filterMap.put("/message/**", "anon");
        filterMap.put("/article/**", "anon");
        filterMap.put("/post/**", "anon");
        filterMap.put("/api/user/login", "anon");
        filterMap.put("/api/user/registered", "anon");
        filterMap.put("/api/message/insertMessage", "anon");
        filterMap.put("/error/**", "anon");

        //  用户授权过滤器
        filterMap.put("/user", "authc,perms[user:user]");
        filterMap.put("/user/**", "authc,perms[user:user]");
        filterMap.put("/api/user/operate/**", "authc,perms[user:user]");
        //  管理员授权过滤器
        filterMap.put("/admin", "authc,perms[user:admin]");
        filterMap.put("/admin/**", "authc,perms[user:admin]");
        filterMap.put("/api/admin/**", "authc,perms[user:admin]");

        //  权限过滤器
        filterMap.put("/information/**", "authc");
        filterMap.put("/api/article/**", "authc");
        filterMap.put("/api/post/**", "authc");
        filterMap.put("/api/message/**", "authc");
        filterMap.put("/api/file/**", "authc");
        filterMap.put("/api/comment/**", "authc");

        //  设置未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/noAuth");

        //  失败后跳转登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 获取默认Web会话管理器
     *
     * @return defaultWebSessionManager
     */

    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {

        /*
         * DefaultWebSecurityManager类主要定义了设置subjectDao，
         * 获取会话模式，设置会话模式，设置会话管理器，是否是http会
         * 话模式等操作，它继承了DefaultSecurityManager类，实现了
         * WebSecurityManager接口，
         */

        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //  会话过期时间
        defaultWebSessionManager.setGlobalSessionTimeout(1000 * 3600);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionIdCookieEnabled(true);
        log.info("cc-blog get default web session manager. status: ok!");
        return defaultWebSessionManager;
    }

    /**
     * 获取默认Web安全管理器
     *
     * @param userRealm 用户领域
     * @return securityManager
     */

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //  自定义userRealm
        securityManager.setRealm(userRealm);
        //  session管理
        securityManager.setSessionManager(getDefaultWebSessionManager());
        ThreadContext.bind(securityManager);
        log.info("cc-blog get default web security manager. status: ok!");
        return securityManager;
    }

    /**
     * 获取用户领域
     *
     * @return UserRealm
     */

    @Bean(name = "userRealm")
    public UserRealm getUserRealm() {
        log.info("cc-blog get user realm.");
        return new UserRealm();
    }

    /**
     * 记住我管理器
     *
     * @return cookieRememberMeManager
     */

    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        //  cookie设置为7天
        simpleCookie.setMaxAge(604800);
        cookieRememberMeManager.setCookie(simpleCookie);
        log.info("cc-blog get rememberMe manager. status: ok!");
        return cookieRememberMeManager;
    }

    /**
     * 自动代理创建器
     *
     * @return defaultAdvisorAutoProxyCreator
     */

    @Bean
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        //  这真的是一个神奇的bug，aop和shiro会导致代理冲突。哈哈哈，所以我们将任意的usePrefix或ProxyTargetClass任意设为true即可
        //  defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        log.info("cc-blog create default advisor auto proxy creator. status: ok!");
        return defaultAdvisorAutoProxyCreator;
    }
}
