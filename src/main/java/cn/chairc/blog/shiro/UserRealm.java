package cn.chairc.blog.shiro;

import cn.chairc.blog.model.Role;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.model.Permission;
import cn.chairc.blog.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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

        //Subject subject = SecurityUtils.getSubject();
        //User user = (User) subject.getPrincipal();
        User user = (User) principals.getPrimaryPrincipal();
        Role role = userService.getUserRole(user.getUser_safe_role());
        //获取用户身份许可
        Permission permission = userService.getUserPermission(user.getUser_safe_permission());

        System.out.println("当前用户信息为：" + user);
        System.out.println("当前用户等级为：" + role.getRole_name());
        System.out.println("当前用户权限为：" + permission.getPermission_name());
        //根据许可名过滤权限（ShiroConfig.java权限过滤器）
        simpleAuthorizationInfo.addStringPermission(permission.getPermission_name());

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
        System.out.println("getUsername:"+usernamePasswordToken.getUsername());
        //获取该用户的所有信息
        User user = userService.getUserByUsername(usernamePasswordToken.getUsername());

        if (user == null) {
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(user, user.getUser_common_password(), "");
    }
}
