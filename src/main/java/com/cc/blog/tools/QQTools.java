package com.cc.blog.tools;

public final class QQTools {

    /**
     * 登录处理回调地址，可自定义，此时是为了演示方便，实际开发不应该放在常量类中，应放在配置文件
     */

    public static final String CALLBACK_URL = "";

    /**
     * appID和appKey
     */

    public static final String APP_ID = "";
    public static final String APP_KEY = "";

    /**
     * 获取Authorization Code
     */

    public static final String GET_AUTHORIZATION_CODE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 通过Authorization Code获取Access Token
     */

    public static final String GET_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * 获取用户openId
     */

    public static final String GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me";

    /**
     * 获取用户信息
     */

    public static final String GET_USER_INFO = "https://graph.qq.com/user/get_user_info";
}
