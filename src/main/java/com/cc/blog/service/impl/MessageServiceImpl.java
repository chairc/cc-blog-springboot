package com.cc.blog.service.impl;

import com.cc.blog.mapper.MessageDao;
import com.cc.blog.model.Message;
import com.cc.blog.model.ResultSet;
import com.cc.blog.model.User;
import com.cc.blog.service.MessageService;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserService userService;

    /**
     * 主页获取留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllToIndex() {
        return messageDao.getMessageAllToIndex();
    }

    /**
     * 主页获取精选留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllToIndexByWeight() {
        return messageDao.getMessageAllToIndexByWeight();
    }

    /**
     * 获取留言（降序）
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllByAscendingOrder() {
        return messageDao.getMessageAllByAscendingOrder();
    }

    /**
     * 获取留言（升序）
     *
     * @return
     */

    @Override
    public List<Message> getMessageAll() {
        return messageDao.getMessageAll();
    }

    /**
     * 获取精选留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllByWeight() {
        return messageDao.getMessageAllByWeight();
    }

    /**
     * 管理员获取留言
     *
     * @return
     */

    @Override
    public ResultSet getMessageAllByAdmin(int pageNum){
        ResultSet resultSet = new ResultSet();
        try {
            String username = Tools.usernameSessionValidate();
            User admin = userService.getUserByUsername(username);
            if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
                Page<Message> messagePages = PageHelper.startPage(pageNum, 10);
                List<Message> messageList = messageDao.getMessageAllByAdmin();
                resultSet.success("超级管理员留言列表获取成功");
                resultSet.setData(messageList);
            }else {
                resultSet.fail("超级管理员用户列表获取失败");
            }
        } catch (NullPointerException e) {
            resultSet.error();
        }
        return resultSet;
    }

    /**
     * 新增留言
     *
     * @param message
     */

    @Override
    public void insertMessage(Message message) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            messageDao.insertMessage(message);
        }

    }

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */

    @Override
    public void deleteMessage(String privateId) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            messageDao.deleteMessageByPrivateId(privateId);
        }

    }

    /**
     * 更新留言
     *
     * @param message
     */

    @Override
    public void updateMessage(Message message) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            messageDao.updateMessage(message);
        }

    }

    /**
     * 获取留言数
     *
     * @return
     */

    @Override
    public Integer getMessageCount() {
        return messageDao.getMessageCount();
    }
}
