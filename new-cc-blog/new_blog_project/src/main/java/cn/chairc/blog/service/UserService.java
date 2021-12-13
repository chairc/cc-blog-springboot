package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.entity.user.UserRegisteredEntity;
import cn.chairc.blog.entity.user.UserUpdateForgotPasswordEntity;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 20:44
 */
public interface UserService {

    /**
     * 通过邮箱获取用户信息
     *
     * @param userEmail 用户邮箱
     * @return 用户信息
     */

    UserEntity getUserByEmail(String userEmail);

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */

    List<UserEntity> listUser();

    /**
     * 通过用户私有ID获取用户信息
     *
     * @param userPrivateId 用户私有ID
     * @return 用户信息
     */

    ResultSet getUserByPrivateId(String userPrivateId);

    /**
     * 用户登录
     *
     * @param userEmail          用户邮箱
     * @param userPassword       用户密码
     * @param httpServletRequest 请求
     * @return 成功或异常
     */

    ResultSet userLogin(String userEmail, String userPassword, HttpServletRequest httpServletRequest);

    /**
     * 用户登出
     *
     * @return 成功或异常
     */

    ResultSet userLogout();

    /**
     * 用户注册
     *
     * @param userRegisteredEntity 注册类
     * @param httpServletRequest   http请求
     * @param bindingResult        绑定结构
     * @return 成功或异常
     */

    ResultSet userRegistered(UserRegisteredEntity userRegisteredEntity, HttpServletRequest httpServletRequest, BindingResult bindingResult);

    /**
     * 用户修改密码
     *
     * @param oldPassword      旧密码
     * @param newPassword      新密码
     * @param newPasswordAgain 再次输入新密码
     * @return ResultSet结果集
     */

    ResultSet updateUserPassword(String oldPassword, String newPassword, String newPasswordAgain);

    /**
     * 管理员新增用户
     *
     * @param userEntity 用户类
     * @return 成功或异常
     */

    ResultSet insertUser(UserEntity userEntity);

    /**
     * 管理员删除用户
     *
     * @param userPrivateId 用户私有ID
     * @return 成功或异常
     */

    ResultSet deleteUser(String userPrivateId);

    /**
     * 管理员封禁或解禁用户
     *
     * @param userPrivateId 用户私有ID
     * @param type          类型
     * @return 成功或异常
     */

    ResultSet banAndRemoveBanUser(String userPrivateId, String type);

    /**
     * 管理员注销或激活用户
     *
     * @param userPrivateId 用户私有ID
     * @param type          类型
     * @return 成功或异常
     */

    ResultSet cancelAndActiveUser(String userPrivateId, String type);

    /**
     * 修改用户信息
     *
     * @param userEntity 用户类
     * @return 成功或异常
     */

    ResultSet updateUser(UserEntity userEntity);

    /**
     * 获取用户头像
     *
     * @param userPrivateId 用户私有ID
     * @return UserHeadPictureEntity
     */

    UserHeadPictureEntity getUserHeadPic(String userPrivateId);

    /**
     * 用户找回密码
     *
     * @param userUpdateForgotPasswordEntity 用户找回密码类
     * @param bindingResult                 绑定结果
     * @return 成功或异常
     */

    ResultSet updateForgoPassword(UserUpdateForgotPasswordEntity userUpdateForgotPasswordEntity, BindingResult bindingResult);

}
