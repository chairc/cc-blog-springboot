package cn.chairc.blog.service.impl;

import cn.chairc.blog.mapper.FriendLinkDao;
import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.FriendLink;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.model.User;
import cn.chairc.blog.service.FriendLinkService;
import cn.chairc.blog.service.UserService;
import cn.chairc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkDao friendLinkDao;

    @Autowired
    private UserService userService;

    /**
     * 主页获取文章
     *
     * @return List<FriendLink>
     */

    @Override
    public List<FriendLink> getFriendLinkAllToIndex() {
        return friendLinkDao.getFriendLinkAllToIndex();
    }

    /**
     * 主页获取所有友链
     *
     * @return resultSet
     */

    @Override
    public List<FriendLink> getFriendLinkAll() {
        return friendLinkDao.getFriendLinkAll();
    }

    /**
     * 管理员获取所有友链
     *
     * @return resultSet
     */

    @Override
    public DataResultSet getFriendLinkAllByAdmin(int pageNum) {
        DataResultSet dataResultSet = new DataResultSet();
        try {
            //管理员获取
            String username = Tools.usernameSessionValidate();
            User admin = userService.getUserByUsername(username);
            if (Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())) {
                Page<FriendLink> messagePages = PageHelper.startPage(pageNum, 10);
                List<FriendLink> friendLinkList = friendLinkDao.getFriendLinkAllByAdmin();
                dataResultSet.success("超级管理员友链列表获取成功");
                dataResultSet.setData(friendLinkList);
                dataResultSet.setPage_num(pageNum);
                dataResultSet.setPage_count((int) messagePages.getTotal());
                dataResultSet.setPage_total((int) ((messagePages.getTotal() - 1) / 10 + 1));
            } else {
                dataResultSet.fail("超级管理员友链列表获取失败");
            }
        } catch (Exception e) {
            dataResultSet.error();
        }
        return dataResultSet;
    }

    /**
     * 通过私有id获取友链
     *
     * @return ResultSet
     */

    @Override
    public ResultSet getFriendLinkByPrivateId(String privateId) {
        ResultSet resultSet = new ResultSet();
        resultSet.success("查找成功");
        resultSet.setData(friendLinkDao.getFriendLink(privateId));
        return resultSet;
    }

    /**
     * 新增友链
     *
     * @return ResultSet
     */

    @Override
    public ResultSet insertFriendLink(FriendLink friendLink) {
        ResultSet resultSet = new ResultSet();
        friendLinkDao.insertFriendLink(friendLink);
        return resultSet;
    }

    /**
     * 更新友链
     *
     * @return ResultSet
     */

    @Override
    public ResultSet updateFriendLink(FriendLink friendLink) {
        ResultSet resultSet = new ResultSet();
        try {
            friendLinkDao.updateFriendLink(friendLink);
            resultSet.success("更新友情链接成功");
        } catch (Exception e) {
            resultSet.error();
        }
        return resultSet;
    }

    /**
     * 通过私有id删除友链
     */

    @Override
    public void deleteFriendLink(String privateId) {
        friendLinkDao.deleteFriendLink(privateId);
    }

    /**
     * 通过id获取友链
     *
     * @param id
     * @return
     */

    @Override
    public FriendLink getUserFriendLink(int id) {
        return friendLinkDao.getUserFriendLink(id);
    }
}
