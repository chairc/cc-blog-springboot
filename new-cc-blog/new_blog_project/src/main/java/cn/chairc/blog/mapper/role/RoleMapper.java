package cn.chairc.blog.mapper.role;

import cn.chairc.blog.entity.role.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/6/7 13:31
 */
@Mapper
public interface RoleMapper {

    /**
     * 获取权限列表
     *
     * @return 角色表
     */

    List<RoleEntity> listRole();

    /**
     * 获取角色
     *
     * @param roleName 角色名
     * @return 角色类
     */

    RoleEntity getRole(String roleName);

    /**
     * 获取角色是否存在
     *
     * @param roleName 角色名
     * @return 存在或不存在
     */

    boolean getRoleIsExist(String roleName);

    /**
     * 新增角色
     *
     * @param roleEntity 角色类
     */

    void insertRole(RoleEntity roleEntity);

    /**
     * 更新角色
     *
     * @param roleEntity 角色类
     */

    void updateRole(RoleEntity roleEntity);

    /**
     * 永久删除角色
     *
     * @param roleName 角色名
     */

    void deleteRole(String roleName);

}
