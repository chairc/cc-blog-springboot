package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.permission.PermissionEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserPermissionEntity;
import cn.chairc.blog.mapper.permission.PermissionMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.mapper.user.UserPermissionMapper;
import cn.chairc.blog.service.UserPermissionService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chairc
 * @date 2021/5/5 21:08
 */
@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    private static final Logger log = LoggerFactory.getLogger(UserPermissionServiceImpl.class);

    private UserPermissionMapper userPermissionMapper;

    private PermissionMapper permissionMapper;

    private UserMapper userMapper;

    @Autowired
    public UserPermissionServiceImpl(UserPermissionMapper userPermissionMapper, UserMapper userMapper,
                                     PermissionMapper permissionMapper) {
        this.userPermissionMapper = userPermissionMapper;
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
    }

    /**
     * 通过用户私有ID获取用户权限
     *
     * @param userPrivateId 用户私有ID
     * @return 用户权限
     */

    @Override
    public String getUserPermission(String userPrivateId) {
        return userPermissionMapper.getUserPermission(userPrivateId).getUserPermission();
    }

    /**
     * 获取用户权限表
     *
     * @return 用户权限表
     */

    @Override
    public List<UserPermissionEntity> listUserPermission() {
        List<UserPermissionEntity> userPermissionList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            userPermissionList = userPermissionMapper.listUserPermission();
            for (UserPermissionEntity userPermission : userPermissionList) {
                userPermission.setUserPermissionCreateTime(TimeUtil.exchangeTimeTypeDateToString(userPermission.getCreateTime()));
                userPermission.setUsername(userMapper.getUserByPrivateId(userPermission.getUserPrivateId()).getUsername());
                userPermission.setUserPermissionUpdateTime(TimeUtil.exchangeTimeTypeDateToString(userPermission.getUpdateTime()));
            }
            log.info("用户{}获取权限列表成功", userPrivateId);
        } catch (Exception e) {
            log.info("用户{}获取权限列表失败，原因：{}", userPrivateId, e.toString());
        }
        return userPermissionList;
    }

    @Override
    public ResultSet getUserPermissionByPrivateId(String userPrivateId) {
        ResultSet resultSet = new ResultSet();
        String operateUserPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            UserPermissionEntity userPermissionEntity = userPermissionMapper.getUserPermission(userPrivateId);
            userPermissionEntity.setUsername(userMapper.getUserByPrivateId(userPrivateId).getUsername());
            userPermissionEntity.setUserPermissionCreateTime(TimeUtil.exchangeTimeTypeDateToString(userPermissionEntity.getCreateTime()));
            userPermissionEntity.setUserPermissionUpdateTime(TimeUtil.exchangeTimeTypeDateToString(userPermissionEntity.getUpdateTime()));
            List<PermissionEntity> permissionEntityList = permissionMapper.listPermission();
            Map<String, Object> userPermissionMap = new HashMap<>();
            userPermissionMap.put("userPermission", userPermissionEntity);
            userPermissionMap.put("permissionList", permissionEntityList);
            resultSet.setData(userPermissionMap);
            resultSet.ok("获取用户权限信息成功");
            log.info("用户{}获取用户{}权限信息及列表成功", operateUserPrivateId, userPrivateId);
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}获取用户{}权限信息及列表失败，原因：{}", operateUserPrivateId, userPrivateId, e.toString());
        }
        return resultSet;
    }
}
