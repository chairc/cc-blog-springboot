package com.cc.blog.service.impl;

import com.cc.blog.mapper.MessageDao;
import com.cc.blog.model.Message;
import com.cc.blog.model.ResultSet;
import com.cc.blog.service.MessageService;
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
    public List<Message> getMessageAllByAscendingOrder(HttpServletRequest request) {
        return messageDao.getMessageAllByAscendingOrder();
    }

    /**
     * 获取留言（升序）
     *
     * @return
     */

    @Override
    public List<Message> getMessageAll(HttpServletRequest request) {
        return messageDao.getMessageAll();
    }

    /**
     * 获取精选留言
     *
     * @return
     */

    @Override
    public List<Message> getMessageAllByWeight(HttpServletRequest request) {
        return messageDao.getMessageAllByWeight();
    }

    /**
     * 管理员获取留言
     *
     * @param request
     * @return
     */

    @Override
    public ResultSet getMessageAllByAdmin(HttpServletRequest request,int pageNum){
        ResultSet resultSet = new ResultSet();
        Page<Message> messagePages = PageHelper.startPage(pageNum, 10);
        try {
            if(Tools.usernameSessionIsAdminValidate(request)){
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
    public void insertMessage(Message message,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            messageDao.insertMessage(message);
        }

    }

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */

    @Override
    public void deleteMessage(String privateId,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            messageDao.deleteMessageByPrivateId(privateId);
        }

    }

    /**
     * 更新留言
     *
     * @param message
     */

    @Override
    public void updateMessage(Message message,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
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
