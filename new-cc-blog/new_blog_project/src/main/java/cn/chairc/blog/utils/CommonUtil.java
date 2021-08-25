package cn.chairc.blog.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author chairc
 * @date 2021/5/6 17:58
 */
public class CommonUtil {

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 生成私人ID
     *
     * @param type 传入类型
     * @return privateId
     */

    public static String createRandomPrivateId(String type) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String[] arr = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
                "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
                "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        String header = type + "_" + simpleDateFormat.format(TimeUtil.getServerTime()) + "_";
        int i = 0, randomNum;
        StringBuilder privateId = new StringBuilder();
        while (i < 5) {
            //  随机选择
            randomNum = (int) (Math.random() * 52);
            //  拼接
            privateId.insert(0, arr[randomNum]);
            i++;
        }
        privateId.insert(0, header);
        return privateId.toString();
    }

    /**
     * 生成用户私人ID
     *
     * @return privateId
     */

    public static String createUserRandomPrivateId() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
        String header = "user_" + simpleDateFormat.format(TimeUtil.getServerTime()) + "_";
        String[] arr = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        int i = 0, randomNum;
        StringBuilder privateId = new StringBuilder();
        while (i < 5) {
            randomNum = (int) (Math.random() * 10);
            privateId.insert(0, arr[randomNum]);
            i++;
        }
        privateId.insert(0, header);
        return privateId.toString();
    }

    /**
     * 验证码生成器
     *
     * @return 验证码
     */

    public static String createVerificationCode() {
        int verificationCode = (int) ((Math.random() * 9 + 1) * 100000);
        return String.valueOf(verificationCode);
    }

    /**
     * 获取IP
     *
     * @param request request请求
     * @return 用户当前IP
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
     * @param request request请求
     * @return 浏览器信息
     */

    public static String getBrowserVersion(HttpServletRequest request) {
        //  获取浏览器信息
        String ua = request.getHeader("User-Agent");
        //  转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //  获取浏览器信息
        Browser browserInfo = userAgent.getBrowser();
        return browserInfo.getName();
    }

    /**
     * 获取系统信息
     *
     * @param request request请求
     * @return 用户系统信息
     */

    public static String getSystemVersion(HttpServletRequest request) {
        //  获取系统信息
        String ua = request.getHeader("User-Agent");
        //  转成UserAgent对象
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        //  获取系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        return os.getName();
    }

    /**
     * 用户Session验证
     *
     * @return 返回所要获取session信息
     */

    public static String sessionValidate(String key) {
        //Shiro获取session
        String returnVal = null;
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser.isAuthenticated()){
            Session session = currentUser.getSession();
            switch (key) {
                case "username":
                    returnVal = (String) session.getAttribute("username");
                    break;
                case "userPrivateId":
                    returnVal = (String) session.getAttribute("userPrivateId");
                    break;
                case "userEmail":
                    returnVal = (String) session.getAttribute("userEmail");
                    break;
                case "permission":
                    returnVal = (String) session.getAttribute("permission");
                    break;
                case "role":
                    returnVal = (String) session.getAttribute("role");
                    break;
                case "ip":
                    returnVal = (String) session.getAttribute("ip");
                    break;
                case "browser":
                    returnVal = (String) session.getAttribute("browser");
                    break;
                case "system":
                    returnVal = (String) session.getAttribute("system");
                    break;
                default:
                    break;
            }
        }
        return returnVal;
    }
}
