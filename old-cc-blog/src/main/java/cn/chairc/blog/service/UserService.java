package cn.chairc.blog.service;

import cn.chairc.blog.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    /**
     * 获取所有用户
     *
     * @return List<User>
     */

    List<User> getUserAll();

    /**
     * 管理员获取用户列表
     *
     * @param pageNum
     * @return
     */

    DataResultSet getUserAllByAdmin(int pageNum);

    /**
     * 通过ID获取用户
     *
     * @param id
     * @return User
     */

    User getUserById(int id);

    /**
     * 用户登录（已废弃，使用shiro验证）
     *
     * @param username
     * @param password
     * @return 1 或 0
     */

    Integer loginUser(String username, String password);

    /**
     * 查找私有ID获取用户
     *
     * @param privateId
     * @return 1 或 0
     */

    Integer getUserPrivateId(String privateId);

    /**
     * 通过用户名获取用户信息
     *
     * @param username
     * @return User
     */

    User getUserByUsername(String username);

    /**
     * 通过openId获得用户名
     *
     * @param openId
     * @return User
     */

    User getUserByOpenId(String openId);

    /**
     * 新增用户信息
     *
     * @param user
     */

    void insertUser(User user);

    /**
     * 通过ID删除用户信息（暂时停用此方法）
     *
     * @param id
     */
    void deleteUserById(int id);

    /**
     * 更新用户信息（暂时停用此方法）
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 用户名唯一性验证
     *
     * @param username
     * @return 1 或 0
     */
    Integer usernameValidate(String username);

    /**
     * 通过QQ快速登录来验证openId
     *
     * @param openId
     * @return 1 或 0
     */
    Integer openIdValidate(String openId);

    /**
     * 获取用户数
     *
     * @return 用户数
     */

    Integer getUserCount();

    /**
     * 获取用户权限
     *
     * @return 权限值
     */

    Role getUserRole(String role);

    /**
     * 获取用户身份许可
     *
     * @param permission
     * @return 返回许可
     */

    Permission getUserPermission(String permission);

    /**
     * 更新用户登录日志
     *
     * @param user
     */

    void updateUserLoginLog(User user,String loginMethod);

    /**
     * 通过私有ID获得用户
     * @param privateId
     * @return
     */

    User getUserByPrivateId(String privateId);

}
