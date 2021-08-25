package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.permission.PermissionEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.role.RoleEntity;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.service.*;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/22 19:07
 */
@Controller
@RequestMapping("/api/admin")
public class AdminController {
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private static final String YES = "是";

    private UserService userService;

    private UserPermissionService userPermissionService;

    private UserRoleService userRoleService;

    private PermissionService permissionService;

    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, UserPermissionService userPermissionService,
                           UserRoleService userRoleService, PermissionService permissionService,
                           RoleService roleService) {
        this.userService = userService;
        this.userPermissionService = userPermissionService;
        this.userRoleService = userRoleService;
        this.permissionService = permissionService;
        this.roleService = roleService;
    }

    /**
     * 通过用户私有ID获取信息
     * 方法说明：无敏感信息
     *
     * @param userPrivateId 用户私有ID
     * @return 用户信息
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/getUserByPrivateId")
    @ResponseBody
    public ResultSet getUserByPrivateId(@RequestParam(value = "userPrivateId") String userPrivateId) {
        return userService.getUserByPrivateId(userPrivateId);
    }

    /**
     * 管理员新增用户
     * 方法说明：敏感信息，参数不能记录到日志文件
     *
     * @param addUsername       用户名
     * @param addUserEmail      用户邮箱
     * @param addUserPassword   用户密码
     * @param addUserIsActivity 是否激活
     * @return 成功或异常
     * @throws ParseException 解析异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-3")
    @RequestMapping("/insertUser")
    @ResponseBody
    public ResultSet insertUser(@RequestParam(value = "addUsername") String addUsername,
                                @RequestParam(value = "addUserEmail") String addUserEmail,
                                @RequestParam(value = "addUserPassword") String addUserPassword,
                                @RequestParam(value = "addUserIsActivity") String addUserIsActivity) throws ParseException {
        Date time = TimeUtil.getServerTime();
        UserEntity userEntity = new UserEntity();
        userEntity.setUserPrivateId(CommonUtil.createUserRandomPrivateId());
        userEntity.setUsername(addUsername);
        userEntity.setUserEmail(addUserEmail);
        userEntity.setPassword(addUserPassword);
        if (YES.equals(addUserIsActivity)) {
            userEntity.setUserIsActivity(1);
        } else {
            userEntity.setUserIsActivity(-1);
        }
        userEntity.setUserIsBanned(1);
        userEntity.setUserIsDelete(1);
        userEntity.setCreateTime(time);
        userEntity.setUpdateTime(time);
        return userService.insertUser(userEntity);
    }

    /**
     * 删除用户
     *
     * @param adminDeleteUserPrivateId 用户私有ID
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultSet deleteUser(@RequestParam(value = "adminDeleteUserPrivateId") String adminDeleteUserPrivateId) {
        return userService.deleteUser(adminDeleteUserPrivateId);
    }

    /**
     * 封禁用户
     *
     * @param adminBanUserPrivateId 用户私有ID
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/banUser")
    @ResponseBody
    public ResultSet banUser(@RequestParam(value = "adminBanUserPrivateId") String adminBanUserPrivateId) {
        return userService.banAndRemoveBanUser(adminBanUserPrivateId, "ban");
    }

    /**
     * 解禁用户
     *
     * @param adminRemoveBanUserPrivateId 用户私有ID
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/removeBanUser")
    @ResponseBody
    public ResultSet removeBanUser(@RequestParam(value = "adminRemoveBanUserPrivateId") String adminRemoveBanUserPrivateId) {
        return userService.banAndRemoveBanUser(adminRemoveBanUserPrivateId, "removeBan");
    }

    /**
     * 注销用户
     *
     * @param adminCancelUserPrivateId 用户私有ID
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/cancelUser")
    @ResponseBody
    public ResultSet cancelUser(@RequestParam(value = "adminCancelUserPrivateId") String adminCancelUserPrivateId) {
        return userService.cancelAndActiveUser(adminCancelUserPrivateId, "cancel");
    }

    /**
     * 激活用户
     *
     * @param adminActiveUserUserPrivateId 用户私有ID
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/activeUser")
    @ResponseBody
    public ResultSet activeUser(@RequestParam(value = "adminActiveUserUserPrivateId") String adminActiveUserUserPrivateId) {
        return userService.cancelAndActiveUser(adminActiveUserUserPrivateId, "active");
    }

    /**
     * 更新用户信息
     *
     * @param adminEditUserPrivateId 用户私有ID
     * @param adminEditUsername      用户名
     * @param adminEditUserEmail     用户邮箱
     * @param adminEditUserPassword  密码
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-3")
    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultSet updateUser(@RequestParam(value = "adminEditUserPrivateId") String adminEditUserPrivateId,
                                @RequestParam(value = "adminEditUsername") String adminEditUsername,
                                @RequestParam(value = "adminEditUserEmail") String adminEditUserEmail,
                                @RequestParam(value = "adminEditUserPassword") String adminEditUserPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserPrivateId(adminEditUserPrivateId);
        userEntity.setUsername(adminEditUsername);
        userEntity.setUserEmail(adminEditUserEmail);
        userEntity.setPassword(adminEditUserPassword);
        return userService.updateUser(userEntity);
    }

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/getUserPermissionByPrivateId")
    @ResponseBody
    public ResultSet getUserPermissionByPrivateId(@RequestParam(value = "userPrivateId") String userPrivateId) {
        return userPermissionService.getUserPermissionByPrivateId(userPrivateId);
    }

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/getUserRoleByPrivateId")
    @ResponseBody
    public ResultSet getUserRoleByPrivateId(@RequestParam(value = "userPrivateId") String userPrivateId) {
        return userRoleService.getUserRoleByPrivateId(userPrivateId);
    }

    /**
     * 新增权限
     *
     * @param permissionName        权限名
     * @param permissionDescription 权限描述
     * @return 成功或异常
     * @throws ParseException 解析异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/insertPermission")
    @ResponseBody
    public ResultSet insertPermission(@RequestParam(value = "permissionName") String permissionName,
                                      @RequestParam(value = "permissionDescription") String permissionDescription) throws ParseException {
        PermissionEntity permissionEntity = new PermissionEntity();
        Date time = TimeUtil.getServerTime();
        permissionEntity.setPermissionName(permissionName);
        permissionEntity.setPermissionDescription(permissionDescription);
        permissionEntity.setPermissionIsDelete(1);
        permissionEntity.setCreateTime(time);
        permissionEntity.setUpdateTime(time);
        return permissionService.insertPermission(permissionEntity);
    }

    /**
     * 更新权限
     *
     * @param permissionNameBefore  需要修改的权限名
     * @param permissionName        修改后的权限名
     * @param permissionDescription 新权限的描述，若为空则不修改描述
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/updatePermission")
    @ResponseBody
    public ResultSet updatePermission(@RequestParam(value = "permissionNameBefore") String permissionNameBefore,
                                      @RequestParam(value = "permissionName") String permissionName,
                                      @RequestParam(value = "permissionDescription") String permissionDescription) {
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setPermissionName(permissionName);
        permissionEntity.setPermissionDescription(permissionDescription);
        permissionEntity.setPermissionNameBefore(permissionNameBefore);
        return permissionService.updatePermission(permissionEntity);
    }

    /**
     * 新增角色
     *
     * @param roleName        角色名
     * @param roleDescription 角色描述
     * @return 成功或异常
     * @throws ParseException 解析异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/insertRole")
    @ResponseBody
    public ResultSet insertRole(@RequestParam(value = "roleName") String roleName,
                                @RequestParam(value = "roleDescription") String roleDescription) throws ParseException {
        RoleEntity roleEntity = new RoleEntity();
        Date time = TimeUtil.getServerTime();
        roleEntity.setRoleName(roleName);
        roleEntity.setRoleDescription(roleDescription);
        roleEntity.setRoleIsDelete(1);
        roleEntity.setCreateTime(time);
        roleEntity.setUpdateTime(time);
        return roleService.insertRole(roleEntity);
    }

    /**
     * 更新权限
     *
     * @param roleNameBefore  需要修改的角色名
     * @param roleName        修改后的角色名
     * @param roleDescription 新角色的描述，若为空则不修改描述
     * @return 成功或异常
     */

    @LogVisitor(level = "LEVEL-3")
    @RequestMapping("/updateRole")
    @ResponseBody
    public ResultSet updateRole(@RequestParam(value = "roleNameBefore") String roleNameBefore,
                                @RequestParam(value = "roleName") String roleName,
                                @RequestParam(value = "roleDescription") String roleDescription) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(roleName);
        roleEntity.setRoleNameBefore(roleNameBefore);
        roleEntity.setRoleDescription(roleDescription);
        return roleService.updateRole(roleEntity);
    }
}
