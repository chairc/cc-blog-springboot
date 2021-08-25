package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.role.RoleEntity;
import cn.chairc.blog.mapper.role.RoleMapper;
import cn.chairc.blog.service.RoleService;
import cn.chairc.blog.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairc
 * @date 2021/6/25 9:29
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private RoleMapper roleMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    /**
     * 获取所有角色
     *
     * @return 角色表
     */

    @Override
    public List<RoleEntity> listRole() {
        List<RoleEntity> roleEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            roleEntityList = roleMapper.listRole();
            log.info("用户{}获取角色列表成功", userPrivateId);
        } catch (Exception e) {
            log.info("用户{}获取角色列表失败，原因：{}", userPrivateId, e.toString());
        }
        return roleEntityList;
    }

    /**
     * 新增角色
     *
     * @param roleEntity 角色类
     * @return 成功或异常
     */

    @Override
    public ResultSet insertRole(RoleEntity roleEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            if (!roleMapper.getRoleIsExist(roleEntity.getRoleName())) {
                roleMapper.insertRole(roleEntity);
                resultSet.ok("新增角色成功");
                log.info("用户{}新增角色成功", userPrivateId);
            } else {
                resultSet.fail("新增角色失败，原因：角色存在");
                log.info("用户{}新增角色失败，原因：角色存在", userPrivateId);
            }
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}新增角色失败，原因：{}}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 更新角色
     *
     * @param roleEntity 角色类
     * @return 成功或异常
     */

    @Override
    public ResultSet updateRole(RoleEntity roleEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            if (roleMapper.getRoleIsExist(roleEntity.getRoleNameBefore())) {
                roleMapper.updateRole(roleEntity);
                resultSet.ok("更新角色成功");
                log.info("用户{}更新角色成功", userPrivateId);
            } else {
                resultSet.fail("更新角色失败，原因：角色不存在");
                log.info("用户{}更新角色失败，原因：角色存在", userPrivateId);
            }
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}更新角色失败，原因：{}}", userPrivateId, e.toString());
        }
        return resultSet;
    }
}
