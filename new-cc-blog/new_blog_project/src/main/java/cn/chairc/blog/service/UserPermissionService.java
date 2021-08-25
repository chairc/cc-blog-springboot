package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserPermissionEntity;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 21:08
 */
public interface UserPermissionService {

    /**
     * 通过用户私有ID获取用户权限
     *
     * @param userPrivateId 用户私有ID
     * @return 用户权限
     */

    String getUserPermission(String userPrivateId);

    /**
     * 获取用户权限表
     *
     * @return 用户权限表
     */

    List<UserPermissionEntity> listUserPermission();

    ResultSet getUserPermissionByPrivateId(String userPrivateId);
}
