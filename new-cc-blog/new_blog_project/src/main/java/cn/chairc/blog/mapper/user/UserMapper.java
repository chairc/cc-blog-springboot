package cn.chairc.blog.mapper.user;

import cn.chairc.blog.entity.user.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 20:44
 */
@Mapper
@Component
public interface UserMapper {

    /**
     * 获取用户列表
     *
     * @return 用户表
     */

    List<UserEntity> listUser();

    /**
     * 通过用户私有ID获取用户信息
     *
     * @param userPrivateId 用户私有ID
     * @return 用户信息
     */

    UserEntity getUserByPrivateId(String userPrivateId);

    /**
     * 通过用户Email获取用户信息
     *
     * @param userEmail 用户电子邮件
     * @return 用户信息
     */

    UserEntity getUserByEmail(String userEmail);

    /**
     * 判断用户电子邮件是否存在
     *
     * @param userEmail 用户电子邮件
     * @return 存在或不存在
     */

    boolean getUserEmailIsExist(String userEmail);

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 存在或不存在
     */

    boolean getUsernameIsExist(String username);

    /**
     * 新增用户
     *
     * @param user 用户信息
     */

    void insertUser(UserEntity user);

    /**
     * 更新用户
     *
     * @param user 用户信息
     */

    void updateUser(UserEntity user);

    /**
     * 删除用户
     *
     * @param userPrivateId 用户私有ID
     */

    void deleteUser(String userPrivateId);

    /**
     * 获取所有用户数
     *
     * @return 用户数
     */

    int getUserTotal();


}
