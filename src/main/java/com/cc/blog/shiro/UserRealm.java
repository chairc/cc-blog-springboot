package com.cc.blog.shiro;

import com.cc.blog.model.User;
import com.cc.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //资源授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //资源授权perms中的字符串
        //simpleAuthorizationInfo.addStringPermission("user:index");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String stringPermission = "";

        System.out.println("权重为：" + user.getUser_safe_weight());
        if (user.getUser_safe_weight() == 10) {
            stringPermission = "user:admin";
        } else {
            stringPermission = "user:index";
        }


        System.out.println("perms为：" + stringPermission);
        //未来单独设计权限表（待修改）
        simpleAuthorizationInfo.addStringPermission(stringPermission);

        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //判断用户名
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //获取该用户的所有信息
        User user = userService.getUserByUsername(usernamePasswordToken.getUsername());

        if (user == null) {
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(user, user.getUser_common_password(), "");
    }
}
