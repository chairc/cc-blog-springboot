package cn.chairc.blog.aspect;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.log.LogVisitorEntity;
import cn.chairc.blog.service.LogService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 访问日志记录操作
 *
 * @author chairc
 * @date 2021/5/25 17:31
 */
@Aspect
@Component
public class LogVisitorAspect {

    private static Logger log = LoggerFactory.getLogger(LogVisitorAspect.class);

    private static final String LOG_IGNORED_STRING = "ignored";

    private LogService logService;

    @Autowired
    public LogVisitorAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 切入点为@VisitorLog注解
     */

    @Pointcut("@annotation(cn.chairc.blog.annotation.LogVisitor)")
    public void LogVisitorCut() {

    }

    /**
     * 访问日志切入操作，在切入后在数据库的访问日志表中记录信息
     *
     * @param proceedingJoinPoint 进程连接点
     * @return object
     * @throws Throwable 异常抛出
     */

    @Around("LogVisitorCut()")
    public Object logVisitorCutAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //  获取当前Servlet请求并记录请求内容
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        //  获取请求中的request
        HttpServletRequest request = servletRequestAttributes.getRequest();

        //  环绕通知ProceedingJoinPoint执行proceed方法的作用是让目标方法执行
        //  环绕通知=前置+目标方法执行+后置
        Object object = proceedingJoinPoint.proceed();
        try {
            //  获取方法签名
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            LogVisitor logVisitor = method.getAnnotation(LogVisitor.class);
            String level = logVisitor.level();
            String value = logVisitor.value();
            String visitorUsername = "";
            String logPrivateId = CommonUtil.createRandomPrivateId("log_visitor");
            String methodName = methodSignature.getName();
            String uri = request.getRequestURI();
            String url = request.getRequestURL().toString();
            String parameter = "";
            //  如果value的值是ignored并且参数不能为空值，说明数据敏感，需要忽略记录
            if (!LOG_IGNORED_STRING.equals(value) && request.getQueryString() != null) {
                parameter = request.getQueryString();
                url = request.getRequestURL().toString() + "?" + parameter;
            } else if (LOG_IGNORED_STRING.equals(value) && request.getQueryString() != null) {
                parameter = "已隐藏";
            } else {
                parameter = "无数据";
            }
            String ip = CommonUtil.getUserIp(request);
            String browser = CommonUtil.getBrowserVersion(request);
            String system = CommonUtil.getSystemVersion(request);
            Date time = TimeUtil.getServerTime();
            //  当前访问用户，可能存在未登录情况，若未登录直接进入异常
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                visitorUsername = "未登录用户";
            } else {
                Session session = currentUser.getSession();
                visitorUsername = (String) session.getAttribute("userPrivateId");
            }
            //  控制台打印数据，后期可注释掉
            log.info("访问方法名：{}；访问路由：{}；访问路径：{}；参数：{}；访问用户：{}；访问IP：{}；访问浏览器：{}；访问系统：{}；访问等级：{}",
                    methodName, uri, url, parameter, visitorUsername, ip, browser, system, level);
            //  进行访问日志记录
            LogVisitorEntity logVisitorEntity = new LogVisitorEntity();
            logVisitorEntity.setLogVisitorPrivateId(logPrivateId);
            logVisitorEntity.setLogVisitorMethodName(methodName);
            logVisitorEntity.setLogVisitorRequestUri(uri);
            logVisitorEntity.setLogVisitorRequestUrl(url);
            logVisitorEntity.setLogVisitorParameter(parameter);
            logVisitorEntity.setLogVisitorValue(value);
            logVisitorEntity.setLogVisitorUsername(visitorUsername);
            logVisitorEntity.setLogVisitorIp(ip);
            logVisitorEntity.setLogVisitorBrowserVersion(browser);
            logVisitorEntity.setLogVisitorSystemVersion(system);
            logVisitorEntity.setLogVisitorLevel(level);
            logVisitorEntity.setCreateTime(time);
            logVisitorEntity.setUpdateTime(time);
            logService.insertLogVisitor(logVisitorEntity);
        } catch (UnavailableSecurityManagerException ignored) {
            log.warn("UnavailableSecurityManagerException错误");
            /*
             * 错误点（暂时解决）
             * 报错信息：org.apache.shiro.UnavailableSecurityManagerException: No SecurityManager accessible to the
             * calling code, either bound to the org.apache.shiro.util.ThreadContext or as a vm static singleton.
             * This is an invalid application configuration.
             *
             * 推测原因：在调用Subject之前未进行login方法，在请求该url时对Shiro进行session获取，通俗一点的说是我需要的东西
             * 没要Shiro负责，但是却找他要结果。
             *
             * 推测错误点：ErrorController中页面访问错误
             *
             * 测试结果：在ErrorExceptionConfig配置中将拦截信息转发到相应页面而出现的多次错误，在ErrorController中对
             * BasicErrorController进行继承，在下面的方法中错误的使用了@VisitorLog注解
             *
             * 修改后解决方法1：实例使用redirect重定向到页面，参考如现ErrorController中方法。
             * 修改后解决方法2：在ShiroConfig中的getDefaultWebSecurityManager方法增加ThreadContext.bind(securityManager);
             */
        } catch (Exception e) {
            log.error("LogVisitorAspect发送错误，原因：{}", e.toString());
        }
        return object;
    }
}
