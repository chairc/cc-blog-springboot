package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserRoleEntity;

import java.util.List;

/**
 * @author chairc
 * @date 2021/6/25 8:45
 */
public interface UserRoleService {

    List<UserRoleEntity> listUserRole();

    ResultSet getUserRoleByPrivateId(String userPrivateId);
}
