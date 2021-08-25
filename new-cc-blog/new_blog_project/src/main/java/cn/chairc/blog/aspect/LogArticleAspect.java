package cn.chairc.blog.aspect;

import cn.chairc.blog.annotation.LogArticle;
import cn.chairc.blog.entity.log.LogArticleEntity;
import cn.chairc.blog.mapper.log.LogArticleMapper;
import cn.chairc.blog.service.LogService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
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
 * @author chairc
 * @date 2021/8/16 17:53
 */
@Aspect
@Component
public class LogArticleAspect {

    private static Logger log = LoggerFactory.getLogger(LogArticleAspect.class);

    private LogService logService;

    @Autowired
    public LogArticleAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("@annotation(cn.chairc.blog.annotation.LogArticle)")
    public void LogArticleCut() {

    }

    @Around("LogArticleCut()")
    public Object logArticleCutAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = proceedingJoinPoint.proceed();
        try {
            //  获取当前Servlet请求并记录请求内容
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert servletRequestAttributes != null;
            //  获取请求中的request
            HttpServletRequest request = servletRequestAttributes.getRequest();
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            Method method = methodSignature.getMethod();
            LogArticle logArticle = method.getAnnotation(LogArticle.class);
            //  操作等级
            String level = logArticle.level();
            //  方法名
            String methodName = method.getName();
            //  operateType分为insert delete update select
            String operateType = logArticle.operateType();
            //  记录用户操作描述
            String description = logArticle.description();
            Date date = TimeUtil.getServerTime();
            LogArticleEntity logArticleEntity = new LogArticleEntity();
            logArticleEntity.setLogArticlePrivateId(CommonUtil.createRandomPrivateId("log_article"));
            logArticleEntity.setLogArticleUserPrivateId(CommonUtil.sessionValidate("userPrivateId"));
            logArticleEntity.setLogArticleMethodName(methodName);
            logArticleEntity.setLogArticleOperate(operateType);
            logArticleEntity.setLogArticleLevel(level);
            logArticleEntity.setLogArticleDescription(description);
            logArticleEntity.setLogArticleIp(CommonUtil.getUserIp(request));
            logArticleEntity.setLogArticleBrowserVersion(CommonUtil.getBrowserVersion(request));
            logArticleEntity.setLogArticleSystemVersion(CommonUtil.getSystemVersion(request));
            logArticleEntity.setCreateTime(date);
            logArticleEntity.setUpdateTime(date);
            logService.insertLogArticle(logArticleEntity);
        } catch (Exception e) {
            log.error("LogArticleAspect发送错误，原因：{}", e.toString());
        }
        return object;
    }

}
