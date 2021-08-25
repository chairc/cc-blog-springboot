package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.role.RoleEntity;

import java.util.List;

/**
 * @author chairc
 * @date 2021/6/25 9:29
 */
public interface RoleService {

    /**
     * 获取所有角色
     *
     * @return 角色表
     */

    List<RoleEntity> listRole();

    /**
     * 新增角色
     *
     * @param roleEntity 角色类
     * @return 成功或异常
     */

    ResultSet insertRole(RoleEntity roleEntity);

    /**
     * 更新角色
     *
     * @param roleEntity 角色类
     * @return 成功或异常
     */

    ResultSet updateRole(RoleEntity roleEntity);
}
