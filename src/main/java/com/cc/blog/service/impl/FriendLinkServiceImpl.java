package com.cc.blog.service.impl;

import com.cc.blog.mapper.FriendLinkDao;
import com.cc.blog.model.FriendLink;
import com.cc.blog.model.ResultSet;
import com.cc.blog.service.FriendLinkService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    FriendLinkDao friendLinkDao;

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
    public ResultSet getFriendLinkAllByAdmin(HttpServletRequest request,int pageNum) {
        ResultSet resultSet = new ResultSet();
        Page<FriendLink> messagePages = PageHelper.startPage(pageNum, 10);
        try{
            if(Tools.usernameSessionIsAdminValidate(request)){//管理员获取
                resultSet.success("超级管理员友链列表获取成功");
                resultSet.setData(friendLinkDao.getFriendLinkAllByAdmin());
            }else {
                resultSet.fail("超级管理员友链列表获取失败");
            }
        }catch (Exception e){
            resultSet.error();
        }
        return resultSet;
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
        }catch (Exception e){
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
}
