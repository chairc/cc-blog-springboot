package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.role.RoleEntity;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.entity.user.UserRoleEntity;
import cn.chairc.blog.mapper.role.RoleMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.mapper.user.UserRoleMapper;
import cn.chairc.blog.service.UserRoleService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chairc
 * @date 2021/6/25 8:46
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private static final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    private UserRoleMapper userRoleMapper;

    private RoleMapper roleMapper;

    private UserMapper userMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper, UserMapper userMapper,
                               RoleMapper roleMapper) {
        this.userRoleMapper = userRoleMapper;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<UserRoleEntity> listUserRole() {
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            userRoleEntityList = userRoleMapper.listUserRole();
            for (UserRoleEntity userRoleEntity : userRoleEntityList) {
                UserEntity userEntity = userMapper.getUserByPrivateId(userRoleEntity.getUserPrivateId());
                userRoleEntity.setUsername(userEntity.getUsername());
                userRoleEntity.setUserRoleCreateTime(TimeUtil.exchangeTimeTypeDateToString(userRoleEntity.getCreateTime()));
                userRoleEntity.setUserRoleUpdateTime(TimeUtil.exchangeTimeTypeDateToString(userRoleEntity.getUpdateTime()));
            }
            log.info("用户{}获取角色列表成功", userPrivateId);
        } catch (Exception e) {
            log.info("用户{}获取角色列表失败，原因：{}", userPrivateId, e.toString());
        }
        return userRoleEntityList;
    }

    @Override
    public ResultSet getUserRoleByPrivateId(String userPrivateId) {
        ResultSet resultSet = new ResultSet();
        String operateUserPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            UserRoleEntity userRoleEntity = userRoleMapper.getUserRole(userPrivateId);
            userRoleEntity.setUsername(userMapper.getUserByPrivateId(userPrivateId).getUsername());
            userRoleEntity.setUserRoleCreateTime(TimeUtil.exchangeTimeTypeDateToString(userRoleEntity.getCreateTime()));
            userRoleEntity.setUserRoleUpdateTime(TimeUtil.exchangeTimeTypeDateToString(userRoleEntity.getUpdateTime()));
            List<RoleEntity> roleEntityList = roleMapper.listRole();
            Map<String, Object> userRoleMap = new HashMap<>();
            userRoleMap.put("userRole", userRoleEntity);
            userRoleMap.put("roleList", roleEntityList);
            resultSet.setData(userRoleMap);
            resultSet.ok("获取用户角色信息成功");
            log.info("用户{}获取用户{}角色信息及列表成功", operateUserPrivateId, userPrivateId);
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}获取用户{}角色信息及列表失败，原因：{}", operateUserPrivateId, userPrivateId, e.toString());
        }
        return resultSet;
    }
}
