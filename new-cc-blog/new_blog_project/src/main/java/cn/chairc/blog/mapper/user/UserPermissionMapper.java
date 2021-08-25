package cn.chairc.blog.mapper.user;

import cn.chairc.blog.entity.user.UserPermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 21:02
 */
@Mapper
@Component
public interface UserPermissionMapper {
    UserPermissionEntity getUserPermission(String userPrivateId);

    List<UserPermissionEntity> listUserPermission();

    boolean getUserPermissionIsExist(String userPrivateId);

    void insertUserPermission(UserPermissionEntity userPermission);

    void updateUserPermission(UserPermissionEntity userPermission);

    void deleteUserPermission(String userPrivateId);

}
