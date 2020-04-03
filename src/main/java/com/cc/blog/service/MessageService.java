package com.cc.blog.service;

import com.cc.blog.dao.MessageDao;
import com.cc.blog.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageDao messageDao;

    /**
     * 主页获取留言
     *
     * @return
     */

    public List<Message> getMessageAll_index() {
        return messageDao.getMessageAll_index();
    }

    /**
     * 主页获取精选留言
     *
     * @return
     */

    public List<Message> getMessageAll_index_weight() {
        return messageDao.getMessageAll_index_weight();
    }

    /**
     * 获取留言
     *
     * @return
     */

    public List<Message> getMessageAll() {
        return messageDao.getMessageAll();
    }

    /**
     * 获取精选留言
     *
     * @return
     */

    public List<Message> getMessageAll_weight() {
        return messageDao.getMessageAll_weight();
    }

    /**
     * 新增留言
     *
     * @param message
     */

    public void insertMessage(Message message) {
        messageDao.insertMessage(message);
    }

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */

    public void deleteMessage(String privateId) {
        messageDao.deleteMessageByPrivateId(privateId);
    }

    /**
     * 更新留言
     *
     * @param message
     */

    public void updateMessage(Message message) {
        messageDao.updateMessage(message);
    }

    /**
     * 获取留言数
     *
     * @return
     */

    public Integer getMessageCount() {
        return messageDao.getMessageCount();
    }
}
