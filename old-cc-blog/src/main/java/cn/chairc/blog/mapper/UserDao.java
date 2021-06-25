package cn.chairc.blog.mapper;

import cn.chairc.blog.model.Role;
import cn.chairc.blog.model.User;
import cn.chairc.blog.model.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    /**
     * 获取所有用户
     *
     * @return List<User>
     */

    List<User> getUserAll();

    /**
     * 通过ID获取用户
     *
     * @param id
     * @return User
     */

    User getUserById(int id);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return Integer
     */

    Integer loginUser(String username, String password);

    /**
     * 查找私有ID获取用户
     *
     * @param privateId
     * @return Integer
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
     * 通过用户名获取用户名
     *
     * @param openId
     * @return User
     */

    User getUserByOpenId(String openId);

    /**
     * 通过ID删除用户信息（暂时停用此接口）
     *
     * @param id
     */

    void deleteUserById(int id);

    /**
     * 更新用户信息（暂时停用此接口）
     *
     * @param user
     */

    void updateUser(User user);

    /**
     * 新增用户信息
     *
     * @param user
     */

    void insertUser(User user);

    /**
     * 用户名唯一性验证
     *
     * @param username
     * @return Integer
     */

    Integer usernameValidate(String username);

    /**
     * 通过QQ快速登录来验证openId
     *
     * @param openId
     * @return Integer
     */

    Integer openIdValidate(String openId);

    /**
     * 获取用户数
     *
     * @return Integer
     */

    Integer getUserCount();

    /**
     * 获取用户等级
     *
     * @return Role
     */

    Role getUserRole(String role);

    /**
     * 获取用户权限
     *
     * @param permission
     * @return Permission
     */

    Permission getUserPermission(String permission);

    /**
     * 更新用户登录日志（普通登录）
     *
     * @param user
     */

    void updateUserLoginLogByNormalLogin(User user);

    /**
     * 更新用户登录日志（qq登录）
     *
     * @param user
     */

    void updateUserLoginLogByQQLogin(User user);

    /**
     * 通过私有Id获取用户
     * @param privateId
     * @return
     */

    User getUserByPrivateId(String privateId);

}
