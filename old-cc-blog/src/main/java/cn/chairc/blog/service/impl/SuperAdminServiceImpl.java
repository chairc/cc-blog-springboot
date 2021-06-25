package cn.chairc.blog.service.impl;

import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.util.Tools;
import cn.chairc.blog.mapper.SuperAdminDao;
import cn.chairc.blog.model.User;
import cn.chairc.blog.service.SuperAdminService;
import cn.chairc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private SuperAdminDao superAdminDao;

    @Autowired
    private UserService userService;

    /**
     * 通过ID删除用户信息
     *
     * @param id
     */

    @Override
    public void deleteUserById(int id) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            superAdminDao.deleteUserById(id);
        }
    }

    /**
     * 更新用户信息
     *
     * @param user
     */

    @Override
    public void updateUser(User user) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            superAdminDao.updateUser(user);
        }
    }

    /**
     * 根据ID查询用户信息
     * @param userId
     * @return resultSet
     */

    @Override
    public ResultSet superAdminShowUser(String userId){
        ResultSet resultSet = new ResultSet();
        try {
            int getUserId = Integer.parseInt(userId);
            if (getUserId >= 0 && getUserId <= userService.getUserCount()) {
                User user = userService.getUserById(getUserId);
                String username = user.getUser_common_username();
                System.out.println(username);
                resultSet.setCode("1");
                resultSet.setData(user);
            } else if (getUserId < 0) {
                resultSet.fail("提交失败，低于0值");
            } else if (getUserId > userService.getUserCount()) {
                resultSet.fail("提交失败，超出范围");
            } else {
                resultSet.fail("提交失败，请正确输入");
            }
        }catch (Exception e){
            resultSet.error();
        }
        return resultSet;
    }
}
