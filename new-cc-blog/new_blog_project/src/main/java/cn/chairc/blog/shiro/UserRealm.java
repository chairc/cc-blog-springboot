package cn.chairc.blog.shiro;

import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.service.UserPermissionService;
import cn.chairc.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author chairc
 * @date 2021/5/5 20:45
 */
public class UserRealm extends AuthorizingRealm {
    private static Logger log = LoggerFactory.getLogger(UserRealm.class); //slf4j

    private UserService userService;

    private UserPermissionService userPermissionService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserPermissionService(UserPermissionService userPermissionService) {
        this.userPermissionService = userPermissionService;
    }

    /**
     * 执行授权逻辑
     *
     * @param principals 身份信息
     * @return 授权信息
     */

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //  资源授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        //  资源授权perms中的字符串
        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
        //  获取用户身份许可
        String permission = userPermissionService.getUserPermission(user.getUserPrivateId());

        //  根据许可名过滤权限（ShiroConfig.java权限过滤器）
        simpleAuthorizationInfo.addStringPermission(permission);

        return simpleAuthorizationInfo;
    }

    /**
     * 执行认证逻辑
     *
     * @param token 用户名与密码
     * @return 判断账号密码
     * @throws AuthenticationException 身份验证异常
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //判断用户名（学号）
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        log.info("当前正在登录用户为:" + usernamePasswordToken.getUsername());
        //获取该用户的所有信息
        UserEntity user = userService.getUserByEmail(usernamePasswordToken.getUsername());

        if (user == null) {
            return null;
        }
        //判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
