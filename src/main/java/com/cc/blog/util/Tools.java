package com.cc.blog.util;

import com.github.pagehelper.Page;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tools {

    /**
     * 生成私人ID
     *
     * @return privateId
     */

    public static String CreateRandomPrivateId(int type) {  //0：article;1:message;2:friendLink;3:entertainment
        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
                "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        String[] str = new String[]{"article_", "message_", "friendLink_","entertainment_"};
        int i = 0;
        StringBuilder privateId = new StringBuilder();
        while (i < 15) {
            int randomNum = (int) (Math.random() * 52);     //  随机选择
            privateId.insert(0, arr[randomNum]);     //  拼接
            i++;
        }
        privateId.insert(0, str[type]);
        return privateId.toString();
    }

    /**
     * 生成用户私人ID
     *
     * @return privateId
     */

    public static String CreateUserRandomPrivateId() {
        String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        int i = 0;
        StringBuilder privateId = new StringBuilder();
        while (i < 12) {
            int randomNum = (int) (Math.random() * 10);
            privateId.insert(0, arr[randomNum]);
            i++;
        }
        privateId.insert(0, "cc_blog_");
        return privateId.toString();
    }

    /**
     * 获取服务器时间
     *
     * @return time
     */

    public static String getServerTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    /**
     * 获取IP
     *
     * @return ip
     */

    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.length() > 0) {
            String[] ips = ip.split(",");
            for (String s : ips) {
                if (s.trim().length() > 0 && !"unknown".equalsIgnoreCase(s.trim())) {
                    ip = s.trim();
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 获取浏览器信息
     *
     * @param request
     * @return browser
     */

    public static String getBrowserVersion(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");             //  获取浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);   //    转成UserAgent对象
        Browser browserInfo = userAgent.getBrowser();               //   获取浏览器信息
        return browserInfo.getName();
    }

    /**
     * 获取系统信息
     *
     * @param request
     * @return system
     */

    public static String getSystemVersion(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");                 //  获取浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);       //  转成UserAgent对象
        OperatingSystem os = userAgent.getOperatingSystem();            //  获取系统信息
        return os.getName();
    }

    /**
     * 用户Session验证
     *
     * @return username
     */

    public static String usernameSessionValidate() {
        /*servlet自带session
        HttpSession session = request.getSession();
        */
        //Shiro获取session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (String) session.getAttribute("username");
    }

    /**
     * 用户是否为超管管理员Session验证
     *
     * @return
     */

    public static boolean usernameSessionIsAdminValidate(String role) {
        try {
            if (role.equals("2") || role.equals("1")) {
                return true;
            }
        } catch (NullPointerException e) {//防止ShiroHttpServletRequest返回空指针
            return false;
        }
        return false;
    }

    /**
     * 主页各个标签翻页选择
     * 前端html的”pageNumPrev“、”pageNumNext“、”pageTotal“会出现红色下波浪线，可忽略
     *
     * @param model
     * @param pageNum
     * @param pages
     * @param setPageNum
     */

    public static void indexPageHelperJudge(Model model, int pageNum, Page pages, int setPageNum) {
        if (pageNum == 1) {
            //如果当前页处于第一页，则上一页设为1
            model.addAttribute("pageNumPrev", 1);
        } else {
            //否则上一页设为当前页-1
            model.addAttribute("pageNumPrev", pageNum - 1);
        }
        if (pageNum == pages.getTotal() / setPageNum + 1) {
            //如果当前页为最后一页，则下一页一直是最后一页
            model.addAttribute("pageNumNext", pages.getTotal() / setPageNum + 1);
        } else {
            //否则，下一页为当前页+1
            model.addAttribute("pageNumNext", pageNum + 1);
        }
        model.addAttribute("pageTotal", pages.getTotal() / setPageNum + 1);
    }
}
