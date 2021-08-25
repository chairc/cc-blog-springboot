package cn.chairc.blog.mapper.permission;

import cn.chairc.blog.entity.permission.PermissionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 20:53
 */
@Mapper
@Component
public interface PermissionMapper {

    /**
     * 获取权限表内容
     *
     * @return 权限表
     */

    List<PermissionEntity> listPermission();

    /**
     * 通过权限名获取单条权限信息
     *
     * @param permissionName 权限名称
     * @return 单条权限信息
     */

    PermissionEntity getPermission(String permissionName);

    /**
     * 通过权限名得到当前权限是否存在
     *
     * @param permissionName 权限名
     * @return 存在或不存在
     */

    boolean getPermissionIsExist(String permissionName);

    /**
     * 新增权限
     *
     * @param permissionEntity 权限信息
     */

    void insertPermission(PermissionEntity permissionEntity);

    /**
     * 更新权限
     *
     * @param permissionEntity 权限信息
     */

    void updatePermission(PermissionEntity permissionEntity);

    /**
     * 删除权限
     *
     * @param permissionName 权限名
     */

    void deletePermission(String permissionName);
}
