package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.log.LogUserLoginEntity;
import cn.chairc.blog.entity.user.*;
import cn.chairc.blog.mapper.log.LogUserLoginMapper;
import cn.chairc.blog.mapper.user.UserHeadPictureMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.mapper.user.UserPermissionMapper;
import cn.chairc.blog.mapper.user.UserRoleMapper;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author chairc
 * @date 2021/5/5 20:44
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String USER_NO_ACTIVITY_STRING = "noActivity";

    private static final String EMAIL_VERIFICATION_PATTERN = "/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/";

    private static final String EMAIL_VERIFICATION_CODE_STRING = "registered";

    private UserMapper userMapper;

    private UserPermissionMapper userPermissionMapper;

    private UserRoleMapper userRoleMapper;

    private LogUserLoginMapper logUserLoginMapper;

    private UserHeadPictureMapper userHeadPictureMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserPermissionMapper userPermissionMapper,
                           UserRoleMapper userRoleMapper, LogUserLoginMapper logUserLoginMapper,
                           UserHeadPictureMapper userHeadPictureMapper) {
        this.userMapper = userMapper;
        this.userPermissionMapper = userPermissionMapper;
        this.userRoleMapper = userRoleMapper;
        this.logUserLoginMapper = logUserLoginMapper;
        this.userHeadPictureMapper = userHeadPictureMapper;
    }

    /**
     * 通过邮箱获取用户信息
     *
     * @param userEmail 用户邮箱
     * @return 用户信息
     */

    @Override
    public UserEntity getUserByEmail(String userEmail) {
        return userMapper.getUserByEmail(userEmail);
    }

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */

    @Override
    public List<UserEntity> listUser() {
        List<UserEntity> userList = new ArrayList<>();
        try {
            userList = userMapper.listUser();
            for (UserEntity user : userList) {
                //  转义Date类时间为String类并格式化
                user.setUserCreateTime(TimeUtil.exchangeTimeTypeDateToString(user.getCreateTime()));
                //  在登录日志查询最近一次登录信息
                LogUserLoginEntity logUserLoginEntity = logUserLoginMapper.getCurrentUserLoginLog(user.getUserPrivateId());
                if (logUserLoginEntity != null) {
                    user.setUserIp(logUserLoginEntity.getUserIp());
                    user.setUserBrowser(logUserLoginEntity.getUserBrowser());
                    user.setUserSystem(logUserLoginEntity.getUserSystem());
                } else {
                    user.setUserIp("无数据");
                    user.setUserBrowser("无数据");
                    user.setUserSystem("无数据");
                }
            }
        } catch (Exception e) {
            log.error("获取用户列信息失败，原因：{}", e.toString());
        }
        return userList;
    }

    /**
     * 通过用户私有ID获取用户信息
     *
     * @param userPrivateId 用户私有ID
     * @return 用户信息
     */

    @Override
    public ResultSet getUserByPrivateId(String userPrivateId) {
        ResultSet resultSet = new ResultSet();
        try {
            UserEntity user = userMapper.getUserByPrivateId(userPrivateId);
            LogUserLoginEntity logUserLoginEntity = logUserLoginMapper.getCurrentUserLoginLog(user.getUserPrivateId());
            if (logUserLoginEntity != null) {
                user.setUserIp(logUserLoginEntity.getUserIp());
                user.setUserBrowser(logUserLoginEntity.getUserBrowser());
                user.setUserSystem(logUserLoginEntity.getUserSystem());
            } else {
                user.setUserIp("无数据");
                user.setUserBrowser("无数据");
                user.setUserSystem("无数据");
            }
            user.setUserCreateTime(TimeUtil.exchangeTimeTypeDateToString(user.getCreateTime()));
            user.setUserUpdateTime(TimeUtil.exchangeTimeTypeDateToString(user.getUpdateTime()));
            resultSet.setData(user);
            resultSet.ok("获取用户信息成功");
            log.info("获取用户{}信息成功", userPrivateId);
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("获取用户{}信息失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 用户登录
     *
     * @param userEmail          用户邮箱
     * @param userPassword       用户密码
     * @param httpServletRequest 请求
     * @return 成功或异常
     */

    @Override
    public ResultSet userLogin(String userEmail, String userPassword, HttpServletRequest httpServletRequest) {
        ResultSet resultSet = new ResultSet();
        //  获取Shiro的Subject
        Subject subject = SecurityUtils.getSubject();
        //  封装数据，密码加密
        String md5Password = DigestUtils.md5DigestAsHex(userPassword.getBytes());
        UsernamePasswordToken token = new UsernamePasswordToken(userEmail, md5Password);
        //  执行登陆方法
        try {
            subject.login(token);
            UserEntity user = userMapper.getUserByEmail(userEmail);
            //  检测是否封禁
            if (user.getUserIsBanned() == -1) {
                log.warn("用户{}登录失败，账户封禁", user.getUserPrivateId());
                throw new DisabledAccountException();
            }
            if (user.getUserIsActivity() == -1) {
                log.warn("用户{}登录失败，账户未激活", user.getUserPrivateId());
                throw new UnknownAccountException(USER_NO_ACTIVITY_STRING);
            }
            //  Shiro记录session
            Session session = subject.getSession();
            //  记录私有ID，用户名，电子邮件
            session.setAttribute("userPrivateId", user.getUserPrivateId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userEmail", userEmail);
            //  记录用户权限
            String permission = userPermissionMapper.getUserPermission(user.getUserPrivateId()).getUserPermission();
            session.setAttribute("permission", permission);
            //  记录用户角色
            String role = userRoleMapper.getUserRole(user.getUserPrivateId()).getUserRole();
            session.setAttribute("role", role);
            //  记录IP
            session.setAttribute("ip", CommonUtil.getUserIp(httpServletRequest));
            //  新增用户近期登录记录
            Date time = TimeUtil.getServerTime();
            LogUserLoginEntity logUserLoginEntity = new LogUserLoginEntity();
            logUserLoginEntity.setUserPrivateId(user.getUserPrivateId());
            logUserLoginEntity.setUserIp(CommonUtil.getUserIp(httpServletRequest));
            logUserLoginEntity.setUserBrowser(CommonUtil.getBrowserVersion(httpServletRequest));
            logUserLoginEntity.setUserSystem(CommonUtil.getSystemVersion(httpServletRequest));
            logUserLoginEntity.setCreateTime(time);
            logUserLoginEntity.setUpdateTime(time);
            logUserLoginMapper.insertUserLoginLog(logUserLoginEntity);
            log.info("登录用户为:{}，用户privateId为：{}，权限为：{}，角色为：{}——登录状态：成功",
                    user.getUsername(), user.getUserPrivateId(), permission, role);
            resultSet.ok("登录成功");
        } catch (UnknownAccountException e) {
            //  账号未激活
            if (USER_NO_ACTIVITY_STRING.equals(e.getMessage())) {
                resultSet.fail("登录失败，账户未激活");
            } else {
                //  账号不存在
                log.warn("登录失败，账号{}邮箱不存在", userEmail);
                resultSet.fail("登录失败，邮箱不存在");
            }
        } catch (IncorrectCredentialsException e) {
            //  密码错误
            log.warn("登录失败，账号{}密码错误", userEmail);
            resultSet.fail("登录失败，密码错误");
        } catch (DisabledAccountException e) {
            //  密码错误
            log.warn("登录失败，账号{}已封禁", userEmail);
            resultSet.fail("登录失败，账号封禁");
        } catch (Exception e) {
            log.warn("登录失败，{}", e.toString());
            resultSet.fail("登录失败，异常错误");
        }
        return resultSet;
    }

    /**
     * 用户登出
     *
     * @return 成功或异常
     */

    @Override
    public ResultSet userLogout() {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            log.info("用户{}登出成功", userPrivateId);
            resultSet.ok("登出成功");
        } catch (Exception e) {
            log.error("用户{}登出失败，原因：{}", userPrivateId, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 用户注册
     *
     * @param userRegisteredEntity 注册类
     * @return 成功或异常
     */

    @Override
    public ResultSet userRegistered(UserRegisteredEntity userRegisteredEntity, HttpServletRequest httpServletRequest) {
        ResultSet resultSet = new ResultSet();
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        //  传入的注册值
        String registeredUsername = userRegisteredEntity.getRegisteredUsername();
        String registeredEmail = userRegisteredEntity.getRegisteredEmail();
        String registeredPassword = userRegisteredEntity.getRegisteredPassword();
        String registeredRetypePassword = userRegisteredEntity.getRegisteredRetypePassword();
        String registeredVerificationCode = userRegisteredEntity.getRegisteredVerificationCode();
        //  session保存的验证码值
        String verificationEmail = (String) session.getAttribute("verificationEmail");
        String verificationCode = (String) session.getAttribute("verificationCode");
        String verificationType = (String) session.getAttribute("verificationType");
        try {
            //  用户名不能为空
            if ("".equals(registeredUsername) || registeredUsername == null) {
                resultSet.fail("注册用户名不能为空");
                return resultSet;
            }
            //  用户名重复
            if (userMapper.getUsernameIsExist(registeredUsername)) {
                resultSet.fail("用户名存在");
                return resultSet;
            }
            //  注册邮箱不能为空
            if ("".equals(registeredEmail) || registeredEmail == null) {
                resultSet.fail("注册邮箱不能为空");
                return resultSet;
            }
            //  邮箱格式不正确
            Pattern pattern = Pattern.compile(EMAIL_VERIFICATION_PATTERN);
            if (pattern.matcher(registeredEmail).matches()) {
                resultSet.fail("注册邮箱格式不正确");
                return resultSet;
            }
            //  邮箱存在
            if (userMapper.getUserEmailIsExist(registeredEmail)) {
                resultSet.fail("邮箱存在");
                return resultSet;
            }
            //  密码不能为空
            if ("".equals(registeredPassword) || "".equals(registeredRetypePassword)
                    || registeredPassword == null || registeredRetypePassword == null) {
                resultSet.fail("注册密码不能为空");
                return resultSet;
            }
            //  密码不相同
            if (!registeredPassword.equals(registeredRetypePassword)) {
                resultSet.fail("两遍密码输入不一致");
                return resultSet;
            }
            //  验证码不能为空
            if ("".equals(registeredVerificationCode) || registeredVerificationCode == null) {
                resultSet.fail("验证码不能为空");
                return resultSet;
            }
            //  验证码失效
            if (!EMAIL_VERIFICATION_CODE_STRING.equals(verificationType) || verificationEmail == null
                    || verificationCode == null) {
                resultSet.fail("注册验证码失效");
                return resultSet;
            }
            //  邮箱和验证码匹配错误
            if (!verificationCode.equals(registeredVerificationCode) && verificationEmail.equals(registeredEmail)) {
                resultSet.fail("验证码错误");
                return resultSet;
            }
            String userPrivateId = CommonUtil.createUserRandomPrivateId();
            Date time = TimeUtil.getServerTime();
            //  封装用户类
            UserEntity userEntity = new UserEntity();
            userEntity.setUserPrivateId(userPrivateId);
            userEntity.setUsername(userRegisteredEntity.getRegisteredUsername());
            userEntity.setUserEmail(registeredEmail);
            userEntity.setPassword(DigestUtils.md5DigestAsHex(registeredPassword.getBytes()));
            //  1为未封禁，-1为封禁
            userEntity.setUserIsBanned(1);
            //  1为激活，-1为未激活
            userEntity.setUserIsActivity(1);
            //  1为未注销,-1为注销
            userEntity.setUserIsDelete(1);
            userEntity.setCreateTime(time);
            userEntity.setUpdateTime(time);
            //  封装用户权限类
            UserPermissionEntity userPermissionEntity = new UserPermissionEntity();
            userPermissionEntity.setUserPrivateId(userPrivateId);
            userPermissionEntity.setUserPermission("user:user");
            //  1为正常，-1为注销
            userPermissionEntity.setUserPermissionStatus(1);
            userPermissionEntity.setUserPermissionIsDelete(1);
            userPermissionEntity.setCreateTime(time);
            userPermissionEntity.setUpdateTime(time);
            //  封装角色类
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserPrivateId(userPrivateId);
            userRoleEntity.setUserRole("用户");
            //  1为正常，-1为注销
            userRoleEntity.setUserRoleStatus(1);
            userRoleEntity.setUserRoleIsDelete(1);
            userRoleEntity.setCreateTime(time);
            userRoleEntity.setUpdateTime(time);
            //  写入头像信息
            UserHeadPictureEntity userHeadPictureEntity = new UserHeadPictureEntity();
            userHeadPictureEntity.setUserPrivateId(userPrivateId);
            userHeadPictureEntity.setUserHeadMappingUrl("/static/images/picture/login/login-ico.svg");
            userHeadPictureEntity.setUserHeadMappingThumbnailUrl("/static/images/picture/login/login-ico.svg");
            userHeadPictureEntity.setUserHeadIsDelete(1);
            userHeadPictureEntity.setCreateTime(time);
            userHeadPictureEntity.setUpdateTime(time);
            userMapper.insertUser(userEntity);
            userPermissionMapper.insertUserPermission(userPermissionEntity);
            userRoleMapper.insertUserRole(userRoleEntity);
            userHeadPictureMapper.insertUserHeadPicture(userHeadPictureEntity);
            resultSet.ok("注册成功");
            log.info("用户注册成功");
        } catch (Exception e) {
            log.error("用户注册失败，原因：{}", e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 用户修改密码
     *
     * @param oldPassword      旧密码
     * @param newPassword      新密码
     * @param newPasswordAgain 再次输入新密码
     * @return ResultSet结果集
     */

    @Override
    public ResultSet updateUserPassword(String oldPassword, String newPassword, String newPasswordAgain) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            UserEntity user = userMapper.getUserByPrivateId(userPrivateId);
            //  判断密码框是否为空
            if ("".equals(newPasswordAgain) || "".equals(newPassword) || "".equals(oldPassword)) {
                resultSet.fail("密码框不能为空");
                log.info("用户{}密码框为空", userPrivateId);
                return resultSet;
            }
            String md5OldPassword = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
            String md5NewPassword = DigestUtils.md5DigestAsHex(newPassword.getBytes());
            //  判断新旧密码是否一致
            if (md5OldPassword.equals(md5NewPassword)) {
                resultSet.fail("新密码与旧密码不能相同");
                log.info("用户{}新密码与旧密码相同", userPrivateId);
                return resultSet;
            }
            //  旧密码与数据库是否一致
            if (!user.getPassword().equals(md5OldPassword)) {
                resultSet.fail("旧密码输入错误");
                log.info("用户{}旧密码不一致", userPrivateId);
                return resultSet;
            }
            //  判断新密码是否一致
            if (!newPassword.equals(newPasswordAgain)) {
                resultSet.fail("两遍新密码不一致");
                log.info("用户{}新密码不一致", userPrivateId);
                return resultSet;
            }
            //  对密码进行加密更新处理
            UserEntity userUpdatePassword = new UserEntity();
            userUpdatePassword.setUserPrivateId(userPrivateId);
            userUpdatePassword.setPassword(md5NewPassword);
            userMapper.updateUser(userUpdatePassword);
            //  自动登出
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            log.info("用户{}因修改密码并登出成功", userPrivateId);
            resultSet.ok("修改密码成功，请重新登录");
        } catch (Exception e) {
            log.error("用户{}修改密码时出错，错误为{}", userPrivateId, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 管理员新增用户
     *
     * @param userEntity 用户类
     * @return 成功或异常
     */

    @Override
    public ResultSet insertUser(UserEntity userEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            userMapper.insertUser(userEntity);
            log.info("用户{}新增用户成功", userPrivateId);
            resultSet.ok("新增用户成功");
        } catch (Exception e) {
            log.error("用户{}新增用户时出错，错误为{}", userPrivateId, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 管理员删除用户
     * 方法说明：在删除用户时，需要将用户的角色和权限删除标识置为-1
     *
     * @param userPrivateId 用户私有ID
     * @return 成功或异常
     */

    @Override
    public ResultSet deleteUser(String userPrivateId) {
        ResultSet resultSet = new ResultSet();
        String operateUserPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            UserEntity userEntity = new UserEntity();
            UserPermissionEntity userPermissionEntity = new UserPermissionEntity();
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userEntity.setUserPrivateId(userPrivateId);
            userPermissionEntity.setUserPrivateId(userPrivateId);
            userRoleEntity.setUserPrivateId(userPrivateId);
            userEntity.setUserIsDelete(-1);
            userPermissionEntity.setUserPermissionIsDelete(-1);
            userRoleEntity.setUserRoleIsDelete(-1);
            userMapper.updateUser(userEntity);
            userPermissionMapper.updateUserPermission(userPermissionEntity);
            userRoleMapper.updateUserRole(userRoleEntity);
            log.info("用户{}删除用户成功", userPrivateId);
            resultSet.ok("删除用户成功");
        } catch (Exception e) {
            log.error("用户{}删除用户时出错，错误为{}", operateUserPrivateId, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 管理员封禁或解禁用户
     * 方法说明：在封禁用户时，仅需要将ban状态设置为-1，角色和权限状态不需要改变
     *
     * @param userPrivateId 用户私有ID
     * @param type          类型
     * @return 成功或异常
     */

    @Override
    public ResultSet banAndRemoveBanUser(String userPrivateId, String type) {
        ResultSet resultSet = new ResultSet();
        String operateUserPrivateId = CommonUtil.sessionValidate("userPrivateId");
        String message = "";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserPrivateId(userPrivateId);
        switch (type) {
            case "ban":
                userEntity.setUserIsBanned(-1);
                message = "封禁用户";
                break;
            case "removeBan":
                userEntity.setUserIsBanned(1);
                message = "解禁用户";
                break;
            default:
                userEntity.setUserIsBanned(0);
        }
        try {
            userMapper.updateUser(userEntity);
            log.info("用户{}{}成功", userPrivateId, message);
            resultSet.ok(message + "成功");
        } catch (Exception e) {
            log.error("用户{}在{}时出错，错误为{}", operateUserPrivateId, message, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 管理员注销或激活用户
     * 方法说明：在注销用户时，需要将用户的权限和角色状态置为-1
     *
     * @param userPrivateId 用户私有ID
     * @param type          类型
     * @return 成功或异常
     */

    @Override
    public ResultSet cancelAndActiveUser(String userPrivateId, String type) {
        ResultSet resultSet = new ResultSet();
        String operateUserPrivateId = CommonUtil.sessionValidate("userPrivateId");
        String message = "";
        UserEntity userEntity = new UserEntity();
        UserPermissionEntity userPermissionEntity = new UserPermissionEntity();
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userEntity.setUserPrivateId(userPrivateId);
        userPermissionEntity.setUserPrivateId(userPrivateId);
        userRoleEntity.setUserPrivateId(userPrivateId);
        switch (type) {
            case "cancel":
                userEntity.setUserIsActivity(-1);
                userPermissionEntity.setUserPermissionStatus(-1);
                userRoleEntity.setUserRoleStatus(-1);
                message = "注销用户";
                break;
            case "active":
                userEntity.setUserIsActivity(1);
                userPermissionEntity.setUserPermissionStatus(1);
                userRoleEntity.setUserRoleStatus(1);
                message = "激活用户";
                break;
            default:
                userEntity.setUserIsActivity(0);
                userPermissionEntity.setUserPermissionStatus(0);
                userRoleEntity.setUserRoleStatus(0);
        }
        try {
            userMapper.updateUser(userEntity);
            userPermissionMapper.updateUserPermission(userPermissionEntity);
            userRoleMapper.updateUserRole(userRoleEntity);
            log.info("用户{}{}成功", userPrivateId, message);
            resultSet.ok(message + "成功");
        } catch (Exception e) {
            log.error("用户{}在{}时出错，错误为{}", operateUserPrivateId, message, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 修改用户信息
     *
     * @param userEntity 用户类
     * @return 成功或异常
     */

    @Override
    public ResultSet updateUser(UserEntity userEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            userMapper.updateUser(userEntity);
            log.info("用户{}修改信息成功", userPrivateId);
            resultSet.ok("修改信息成功");
        } catch (Exception e) {
            log.error("用户{}在修改信息时出错，错误为{}", userEntity, e.toString());
            resultSet.interServerError();
        }
        return resultSet;
    }

    /**
     * 获取用户头像
     *
     * @param userPrivateId 用户私有ID
     * @return UserHeadPictureEntity
     */

    @Override
    public UserHeadPictureEntity getUserHeadPic(String userPrivateId) {
        UserHeadPictureEntity userHeadPictureEntity = new UserHeadPictureEntity();
        try {
            userHeadPictureEntity = userHeadPictureMapper.getUserHeadPicture(userPrivateId);
            log.info("用户{}头像获取成功", userPrivateId);
        } catch (Exception e) {
            log.error("用户{}在头像获取时出错，错误为{}", userPrivateId, e.toString());
        }
        return userHeadPictureEntity;
    }
}
