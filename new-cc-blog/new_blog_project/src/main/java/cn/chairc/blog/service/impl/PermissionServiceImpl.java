package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.permission.PermissionEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.mapper.permission.PermissionMapper;
import cn.chairc.blog.service.PermissionService;
import cn.chairc.blog.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairc
 * @date 2021/5/5 20:46
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    private PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    /**
     * 获取权限表
     *
     * @return 权限表
     */

    @Override
    public List<PermissionEntity> listPermissions() {
        List<PermissionEntity> permissionList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            permissionList = permissionMapper.listPermission();
            log.info("用户{}获取权限列表成功", userPrivateId);
        } catch (Exception e) {
            log.info("用户{}获取权限列表失败，原因：{}", userPrivateId, e.toString());
        }
        return permissionList;
    }

    /**
     * 新增权限
     *
     * @param permission 权限信息
     * @return 成功或异常
     */

    @Override
    public ResultSet insertPermission(PermissionEntity permission) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            //  如果权限不存在，则可以新建权限
            if(!permissionMapper.getPermissionIsExist(permission.getPermissionName())){
                permissionMapper.insertPermission(permission);
                resultSet.ok("新增权限成功");
                log.info("用户{}新增权限成功", userPrivateId);
            }else {
                resultSet.fail("新增权限失败，原因：该权限存在");
                log.info("用户{}新增权限失败，原因：该权限存在", userPrivateId);
            }
        }catch (Exception e){
            resultSet.interServerError();
            log.info("用户{}新增权限失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 更新权限
     *
     * @param permissionEntity     新权限信息
     * @return 成功或异常
     */

    @Override
    public ResultSet updatePermission(PermissionEntity permissionEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            //  先验证权限是否存在
            if(permissionMapper.getPermissionIsExist(permissionEntity.getPermissionNameBefore())){
                //  将权限进行更新
                permissionMapper.updatePermission(permissionEntity);
                resultSet.ok("更新权限成功");
                log.info("用户{}更新权限成功", userPrivateId);
            }else {
                resultSet.fail("更新权限失败，权限不存在");
                log.info("用户{}更新权限失败，原因：权限不存在", userPrivateId);
            }
        }catch (Exception e){
            resultSet.interServerError();
            log.info("用户{}更新权限失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }
}
