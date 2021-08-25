package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.common.ErrorEnum;
import cn.chairc.blog.entity.log.LogVisitorEntity;
import cn.chairc.blog.service.LogService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 继承SpringBoot中BasicErrorController，可以自定义错误样式
 *
 * @author chairc
 * @date 2021/6/4 16:37
 */
@Controller
@RequestMapping("/error")
public class ErrorController extends BasicErrorController {

    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    private LogService logService;

    @Autowired
    public ErrorController(ServerProperties serverProperties, LogService logService) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
        this.logService = logService;
    }

    /*
     * 方法一：对BasicErrorController中errorHtml进行重写，使用自定义返回方式
     * 使用方法：将ErrorExceptionConfig中@bean下方法进行注释
     */

    /**
     * 对BasicErrorController中的errorHtml进行重写，达到自定义错误处理的情况
     * 方法说明：无敏感信息
     *
     * @param request  request
     * @param response response
     * @return ModelAndView
     */

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        ErrorEnum errorEnum;
        String visitorUsername = "";
        //  使用枚举类型记录值
        switch (status.value()) {
            case 400:
                errorEnum = ErrorEnum.HTTP_STATUS_400;
                break;
            case 401:
                errorEnum = ErrorEnum.HTTP_STATUS_401;
                break;
            case 404:
                errorEnum = ErrorEnum.HTTP_STATUS_404;
                break;
            case 500:
                errorEnum = ErrorEnum.HTTP_STATUS_500;
                break;
            default:
                errorEnum = ErrorEnum.HTTP_STATUS_ERROR;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorType", status.value());
        modelAndView.addObject("errorTitle", errorEnum.getErrorTitle());
        modelAndView.addObject("errorContent", errorEnum.getErrorContent());
        modelAndView.setViewName(errorEnum.getReturnHtml());
        try {
            //  单独记录访问错误页面，与@VisitorLog注解不同
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                visitorUsername = "未登录用户";
            } else {
                Session session = currentUser.getSession();
                visitorUsername = (String) session.getAttribute("userPrivateId");
            }
            log.info("访问方法名：{}；访问路由：{}；访问路径：{}；访问用户：{}；访问IP：{}；访问浏览器：{}；访问系统：{}；访问等级：{}",
                    "error" + status.value(), request.getRequestURI(), request.getRequestURL(), visitorUsername,
                    CommonUtil.getUserIp(request), CommonUtil.getBrowserVersion(request),
                    CommonUtil.getSystemVersion(request), "LEVEL-0");
            LogVisitorEntity errorLog = new LogVisitorEntity();
            errorLog.setLogVisitorPrivateId(CommonUtil.createRandomPrivateId("log_visitor"));
            errorLog.setLogVisitorMethodName("error" + status.value());
            errorLog.setLogVisitorRequestUri(request.getRequestURI());
            errorLog.setLogVisitorRequestUrl(request.getRequestURL().toString());
            errorLog.setLogVisitorParameter("");
            errorLog.setLogVisitorValue("");
            errorLog.setLogVisitorUsername(visitorUsername);
            errorLog.setLogVisitorIp(CommonUtil.getUserIp(request));
            errorLog.setLogVisitorBrowserVersion(CommonUtil.getBrowserVersion(request));
            errorLog.setLogVisitorSystemVersion(CommonUtil.getSystemVersion(request));
            errorLog.setLogVisitorLevel("LEVEL-0");
            errorLog.setCreateTime(TimeUtil.getServerTime());
            errorLog.setUpdateTime(TimeUtil.getServerTime());
            logService.insertLogVisitor(errorLog);
        } catch (Exception e) {
            log.error("自定义错误返回样式异常");
        }
        return modelAndView;
    }

    /*
     * 方法二：使用@Configuration新建ErrorExceptionConfig配置，利用WebServerFactoryCustomizer新增错误链接跳转
     * 使用方法：注释掉方法一对errorHtml方法的重写，取消ErrorExceptionConfig中的注释
     */

    /**
     * 显示400无法完成请求页面
     *
     * @param model 模型
     * @return warning.html
     */

    @LogVisitor(level = "LEVEL-0")
    @RequestMapping("/400")
    public String show400Page(Model model) {
        model.addAttribute("errorType", "400");
        model.addAttribute("errorTitle", "糟了！无法完成请求了");
        model.addAttribute("errorContent", "无法完成请求了");
        return "error/warning";
    }

    /**
     * 显示401未登录页面
     *
     * @param model 模型
     * @return warning.html
     */

    @LogVisitor(level = "LEVEL-0")
    @RequestMapping("/401")
    public String show401Page(Model model) {
        model.addAttribute("errorType", "401");
        model.addAttribute("errorTitle", "糟了！登录过期了");
        model.addAttribute("errorContent", "账户登录时间已经过期");
        return "error/warning";
    }

    /**
     * 显示404页面
     *
     * @param model 模型
     * @return warning.html
     */

    @LogVisitor(level = "LEVEL-0")
    @RequestMapping({"/404", "/"})
    public String show404Page(Model model) {
        model.addAttribute("errorType", "404");
        model.addAttribute("errorTitle", "糟了！页面消失了");
        model.addAttribute("errorContent", "我们似乎没有找到页面呢");
        return "error/warning";
    }

    /**
     * 显示500页面
     *
     * @param model 模型
     * @return warning.html
     */

    @LogVisitor(level = "LEVEL-0")
    @RequestMapping("/500")
    public String show500Page(Model model) {
        model.addAttribute("errorType", "500");
        model.addAttribute("errorTitle", "糟了！似乎发生了什么错误");
        model.addAttribute("errorContent", "我们服务器似乎出现了内部错误");
        return "error/error";
    }

    /**
     * 显示未授权页面
     *
     * @return error.html
     */

    @LogVisitor(level = "LEVEL-0")
    @RequestMapping("/noAuth")
    public String showNoAuthPage(Model model) {
        model.addAttribute("errorType", "401");
        model.addAttribute("errorTitle", "糟了！用户暂无权限");
        model.addAttribute("errorContent", "您暂未授权进行该操作");
        return "error/error";
    }

}
