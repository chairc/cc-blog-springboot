/*
文章参考来自：https://segmentfault.com/a/1190000020181967?utm_source=tag-newest
            https://wiki.connect.qq.com/get_user_info
            ##关于get_user_info
                此接口主要用于网站使用QQ登录时，直接拉取用户在QQ空间的昵称、头像、性别等信息，降低用户的注册成本。
            ##参数说明
                ret	返回码
                msg	如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
                nickname	    用户在QQ空间的昵称。
                figureurl	    大小为30×30像素的QQ空间头像URL。
                figureurl_1	    大小为50×50像素的QQ空间头像URL。
                figureurl_2	    大小为100×100像素的QQ空间头像URL。
                figureurl_qq_1	大小为40×40像素的QQ头像URL。
                figureurl_qq_2	大小为100×100像素的QQ头像URL。
                                需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
                gender	        性别。如果获取不到则默认返回"男"
 */
package cn.chairc.blog.controller;

import cn.chairc.blog.model.User;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.util.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/qqlogin")
public class QQLoginController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Value("${qqlogin.call-back-url}")
    private String CALLBACK_URL;

    @Value("${qqlogin.app-id}")
    private String APP_ID;

    @Value("${qqlogin.app-key}")
    private String APP_KEY;

    @Value("${qqlogin.get-authorizaion-code}")
    private String GET_AUTHORIZATION_CODE;

    @Value("${qqlogin.get-access-token}")
    private String GET_ACCESS_TOKEN;

    @Value("${qqlogin.get-open-id}")
    private String GET_OPEN_ID;

    @Value("${qqlogin.get-user-info}")
    private String GET_USER_INFO;

    /**
     * 测试配置
     *
     * @return
     */

    @RequestMapping("/testLoginByQQ")
    @ResponseBody
    public String testLoginByQQ() {
        return "CALLBACK_URL=" + CALLBACK_URL + "<br>" + "APP_ID=" + APP_ID + "<br>" +
                "APP_KEY=" + APP_KEY + "<br>" + "GET_AUTHORIZATION_CODE=" + GET_AUTHORIZATION_CODE +
                "<br>" + "GET_ACCESS_TOKEN=" + GET_ACCESS_TOKEN + "<br>" +
                "GET_OPEN_ID=" + GET_OPEN_ID + "<br>" + "GET_USER_INFO=" + GET_USER_INFO;
    }

    /**
     * 用户授权登录方法
     *
     * @param request
     * @param response
     * @throws IOException
     */

    @RequestMapping("/loginByQQ")
    public void userLoginByQQ(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String responseType = "code";
        String clientId = APP_ID;
        String redirectUrl = URLEncoder.encode(CALLBACK_URL, "UTF-8");
        //String redirectUrl = CALLBACK_URL;
        //client端的状态值。用于第三方应用防止CSRF攻击。
        String state = new Date().toString();
        request.getSession().setAttribute("state", state);

        String url = String.format(GET_AUTHORIZATION_CODE +
                        "?response_type=%s&client_id=%s&redirect_uri=%s&state=%s",
                responseType, clientId, redirectUrl, state);

        response.sendRedirect(url);
    }

    /**
     * 用户授权后的回调方法
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */

    @RequestMapping("/callback")
    public String callbackHandler(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        //获取回调的authorization
        String authorizationCode = request.getParameter("code");
        if (authorizationCode == null || authorizationCode.trim().isEmpty()) {
            throw new RuntimeException("未获取到AuthorizationCode");
        }
        //client端的状态值。用于第三方应用防止CSRF攻击。
        String state = request.getParameter("state");
        if (!state.equals(request.getParameter("state"))) {
            throw new RuntimeException("client端的状态值不匹配！");
        }

        //获取accessToken
        String urlForAccessToken = getUrlForAccessToken(authorizationCode);
        String accessToken = getAccessToken(urlForAccessToken);

        //根据accessToken获取openId
        if (accessToken == null || accessToken.trim().isEmpty()) {
            throw new RuntimeException("未获取到accessToken");
        }
        String openId = getOpenId(accessToken);

        //根据openId获取用户信息
        if (openId == null || openId.trim().isEmpty()) {
            throw new RuntimeException("未获取到openId");
        }
        String userInfo = getUserInfo(openId, accessToken);

        System.out.println(userInfo);

        //将userInfo从Json格式中解析并传入service层
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(userInfo, HashMap.class);
        String username = (String) hashMap.get("nickname");
        String sex = (String) hashMap.get("gender");

        //验证openId是否存在于用户表，若不存在则创建该用户并将处于登录状态，若存在则直接进入登录状态
        if (userService.openIdValidate(openId).equals(0)) {
            User user = new User();
            user.setUser_common_private_id(Tools.CreateUserRandomPrivateId());
            user.setUser_common_open_id(openId);
            user.setUser_common_username(openId);
            user.setUser_common_password("null");
            user.setUser_common_nickname(username);
            user.setUser_secret_sex(sex);
            user.setUser_safe_question("QQ快速登录");
            user.setUser_safe_answer("暂无");
            user.setUser_safe_logtime(Tools.getServerTime());
            user.setUser_safe_ip(Tools.getUserIp(request));
            user.setUser_safe_system(Tools.getSystemVersion(request));
            user.setUser_safe_browser(Tools.getBrowserVersion(request));
            user.setUser_safe_role("5");
            user.setUser_safe_permission("6");
            System.out.println(user);
            userService.insertUser(user);
        }
        //通过openId自动登录
        User getUserByOpenId = userService.getUserByOpenId(openId);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new
                UsernamePasswordToken(getUserByOpenId.getUser_common_username(), getUserByOpenId.getUser_common_password());

        try {
            subject.login(usernamePasswordToken);
            request.getSession().setAttribute("username", openId);
        }catch (UnknownAccountException | IncorrectCredentialsException | NullPointerException e){
            return "redirect:/user/login";
        }

        return "redirect:/";
    }

    // 下面是辅助方法

    /**
     * 拼接用于获取accessToken的链接
     *
     * @param authorizationCode
     * @return
     */

    private String getUrlForAccessToken(String authorizationCode) {
        String grantType = "authorization_code";
        String clientId = APP_ID;
        String clientSecret = APP_KEY;
//        String redirectUrl = URLEncoder.encode(CALLBACK_URL, "UTF-8"); //此处进行URLEncode会导致无法获取AccessToken
        String redirectUrl = CALLBACK_URL;

        return String.format(GET_ACCESS_TOKEN +
                        "?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
                grantType, clientId, clientSecret, authorizationCode, redirectUrl);
    }

    /**
     * 获取accessToken
     *
     * @param urlForAccessToken
     * @return
     */

    private String getAccessToken(String urlForAccessToken) {
        String firstCallbackInfo = restTemplate.getForObject(urlForAccessToken, String.class);
        assert firstCallbackInfo != null;
        String[] params = firstCallbackInfo.split("&");
        String accessToken = null;
        for (String param : params) {
            String[] keyValue = param.split("=");
            if (keyValue[0].equals("access_token")) {
                accessToken = keyValue[1];
                break;
            }
        }
        System.out.println("access_token = " + accessToken);
        return accessToken;
    }

    /**
     * 根据accessToken获取open_id
     *
     * @param accessToken
     * @return
     * @throws IOException
     */

    private String getOpenId(String accessToken) throws IOException {
        String url = String.format(GET_OPEN_ID + "?access_token=%s", accessToken);
        //callback( {"clientId":"YOUR_APPID","openId":"YOUR_OPENID"} );
        String secondCallbackInfo = restTemplate.getForObject(url, String.class);

        //正则表达式处理
        String regex = "\\{.*\\}";
        Pattern pattern = Pattern.compile(regex);
        assert secondCallbackInfo != null;
        Matcher matcher = pattern.matcher(secondCallbackInfo);
        if (!matcher.find()) {
            throw new RuntimeException("异常的回调值: " + secondCallbackInfo);
        }
        System.out.println("matcher = " + matcher);
        //调用jackson
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(matcher.group(0), HashMap.class);
        String openId = (String) hashMap.get("openid");

        System.out.println("openid = " + openId);
        return openId;
    }

    /**
     * 根据openid获取用户信息
     *
     * @param openId
     * @param accessToken
     * @return
     */

    private String getUserInfo(String openId, String accessToken) {
        String infoUrl = String.format(GET_USER_INFO + "?access_token=%s&oauth_consumer_key=%s&openid=%ss",
                accessToken, APP_ID, openId);
        System.out.println("infoUrl = " + infoUrl);
        return restTemplate.getForObject(infoUrl, String.class);
    }
}
