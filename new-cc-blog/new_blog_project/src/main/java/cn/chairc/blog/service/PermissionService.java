package cn.chairc.blog.service;

import cn.chairc.blog.entity.permission.PermissionEntity;
import cn.chairc.blog.entity.common.ResultSet;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 20:45
 */
public interface PermissionService {

    /**
     * 获取权限表
     *
     * @return 权限表
     */

    List<PermissionEntity> listPermissions();

    /**
     * 新增权限
     *
     * @param permission 权限
     * @return 成功或异常
     */

    ResultSet insertPermission(PermissionEntity permission);

    /**
     * 更新权限
     * @param permissionEntity 新权限信息
     * @return 成功或异常
     */

    ResultSet updatePermission(PermissionEntity permissionEntity);
}
