package cn.chairc.blog.mapper.user;

import cn.chairc.blog.entity.user.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/6/7 13:31
 */
@Mapper
public interface UserRoleMapper {

    UserRoleEntity getUserRole(String userPrivateId);

    List<UserRoleEntity> listUserRole();

    boolean getUserRoleIsExist(String userPrivateId);

    void insertUserRole(UserRoleEntity userRoleEntity);

    void updateUserRole(UserRoleEntity userRoleEntity);

    void deleteUserRole(String userPrivateId);
}
