package com.cc.blog.util;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tools {

    /**
     * 生成私人ID
     *
     * @return privateId
     */
    public static String CreateRandomPrivateId(int type) {//0：article;1:message;
        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
                "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        String[] str = new String[]{"article_","message_"};
        int i = 0;
        StringBuilder privateId = new StringBuilder();
        while (i < 15) {
            int randomNum = (int) (Math.random() * 52);
            privateId.insert(0, arr[randomNum]);
            i++;
        }
        privateId.insert(0,str[type]);
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
            /*for(int i=0;i<ips.length;i++){
                if(ips[i].trim().length() > 0 && !"unknown".equalsIgnoreCase(ips[i].trim())){
                    ip = ips[i].trim();
                    break;
                }
            }*/
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
        String ua = request.getHeader("User-Agent");//获取浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(ua); //转成UserAgent对象
        Browser browserInfo = userAgent.getBrowser();  //获取浏览器信息
        return browserInfo.getName();
    }

    /**
     * 获取系统信息
     *
     * @param request
     * @return system
     */
    public static String getSystemVersion(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");//获取浏览器信息
        UserAgent userAgent = UserAgent.parseUserAgentString(ua); //转成UserAgent对象
        OperatingSystem os = userAgent.getOperatingSystem();//获取系统信息
        return os.getName();
    }

    public static String usernameSessionValidate(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (String) session.getAttribute("username");
    }

}
