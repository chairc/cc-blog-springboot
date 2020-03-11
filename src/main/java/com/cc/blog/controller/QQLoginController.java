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
package com.cc.blog.controller;

import com.cc.blog.model.User;
import com.cc.blog.service.UserService;

import com.cc.blog.tools.Tools;
import com.fasterxml.jackson.databind.ObjectMapper;
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
     */

    @RequestMapping("/loginByQQ")
    public void loginByQQ(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String response_type = "code";
        String client_id = APP_ID;
        String redirect_uri = URLEncoder.encode(CALLBACK_URL, "UTF-8");
        //String redirect_uri = CALLBACK_URL;
        //client端的状态值。用于第三方应用防止CSRF攻击。
        String state = new Date().toString();
        request.getSession().setAttribute("state", state);

        String url = String.format(GET_AUTHORIZATION_CODE +
                        "?response_type=%s&client_id=%s&redirect_uri=%s&state=%s",
                response_type, client_id, redirect_uri, state);

        response.sendRedirect(url);

        // 如果一切顺利，就会进入callbackHandler方法
    }

    /**
     * 用户授权后的回调方法
     */

    @RequestMapping("/callback")
    @ResponseBody
    public String callbackHandler(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        // 1.获取回调的authorization
        String authorization_code = request.getParameter("code");
        if (authorization_code == null || authorization_code.trim().isEmpty()) {
            throw new RuntimeException("未获取到AuthorizationCode");
        }
        // 2.client端的状态值。用于第三方应用防止CSRF攻击。
        String state = request.getParameter("state");
        if (!state.equals(request.getParameter("state"))) {
            throw new RuntimeException("client端的状态值不匹配！");
        }

        // 3.获取accessToken
        String url_for_access_token = getUrlForAccessToken(authorization_code);
        String access_token = getAccessToken(url_for_access_token);

        // 4.根据access_token获取open_id
        if (access_token == null || access_token.trim().isEmpty()) {
            throw new RuntimeException("未获取到accessToken");
        }
        String open_id = getOpenId(access_token);

        // 5.根据open_id获取用户信息
        if (open_id == null || open_id.trim().isEmpty()) {
            throw new RuntimeException("未获取到openId");
        }
        String user_info = getUserInfo(open_id, access_token);
        //return "user_info为：" + user_info;

        System.out.println(user_info);


        // ... 获取到用户信息就可以进行自己的业务逻辑处理了
        User user = new User();
        Integer flag = 1;
        String private_id = "";
        while (flag == 1) {
            private_id = Tools.CreateUserRandomPrivateId();
            flag = userService.getUserPrivateId(private_id); //判断私有ID是否存在
        }
        user.setUser_common_private_id(private_id);
        //user.setUser_common_username(username);
        //user.setUser_secret_qq(qq);
        user.setUser_safe_logtime(Tools.getServerTime());
        user.setUser_safe_ip(Tools.getUserIp(request));
        user.setUser_safe_system(Tools.getSystemVersion(request));
        user.setUser_safe_browser(Tools.getBrowserVersion(request));
        System.out.println(user);
        userService.insertUser(user);

        return "redirect:/";
    }

    // 下面是辅助方法

    /**
     * 拼接用于获取accessToken的链接
     */

    private String getUrlForAccessToken(String authorization_code) {
        String grant_type = "authorization_code";
        String client_id = APP_ID;
        String client_secret = APP_KEY;
//        String redirect_uri = URLEncoder.encode(CALLBACK_URL, "UTF-8"); 此处进行URLEncode会导致无法获取AccessToken
        String redirect_uri = CALLBACK_URL;

        return String.format(GET_ACCESS_TOKEN +
                        "?grant_type=%s&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
                grant_type, client_id, client_secret, authorization_code, redirect_uri);
    }

    /**
     * 获取accessToken
     */

    private String getAccessToken(String url_for_access_token) {
        String first_callback_info = restTemplate.getForObject(url_for_access_token, String.class);
        assert first_callback_info != null;
        String[] params = first_callback_info.split("&");
        String access_token = null;
        for (String param : params) {
            String[] key_value = param.split("=");
            if (key_value[0].equals("access_token")) {
                access_token = key_value[1];
                break;
            }
        }
        System.out.println("access_token = " + access_token);
        return access_token;
    }

    /**
     * 根据accessToken获取openid
     */

    private String getOpenId(String access_token) throws IOException {
        String url = String.format(GET_OPEN_ID + "?access_token=%s", access_token);
        //callback( {"client_id":"YOUR_APPID","open_id":"YOUR_OPENID"} );
        String secondCallbackInfo = restTemplate.getForObject(url, String.class);

        //正则表达式处理
        String regex = "\\{.*\\}";
        Pattern pattern = Pattern.compile(regex);
        assert secondCallbackInfo != null;
        Matcher matcher = pattern.matcher(secondCallbackInfo);
        if (!matcher.find()) {
            throw new RuntimeException("异常的回调值: " + secondCallbackInfo);
        }

        //调用jackson
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap hashMap = objectMapper.readValue(matcher.group(0), HashMap.class);

        //return "获取到的openid为：" + openid;
        return ((String) hashMap.get("openid"));
    }

    /**
     * 根据openid获取用户信息
     */

    private String getUserInfo(String open_id, String access_token) {
        String info_url = String.format(GET_USER_INFO + "?access_token=%s&oauth_consumer_key=%s&openid=%ss",
                access_token, APP_ID, open_id);
        System.out.println("infoUrl = " + info_url);
        return restTemplate.getForObject(info_url, String.class);
    }
}
