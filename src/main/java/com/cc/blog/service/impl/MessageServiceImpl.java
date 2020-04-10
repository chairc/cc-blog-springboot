package com.cc.blog.service.impl;

import com.cc.blog.mapper.MessageDao;
import com.cc.blog.model.Message;
import com.cc.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Message> getMessageAllToIndex() {
        return messageDao.getMessageAllToIndex();
    }

    /**
     * 主页获取精选留言
     *
     * @return
     */

    public List<Message> getMessageAllToIndexByWeight() {
        return messageDao.getMessageAllToIndexByWeight();
    }

    /**
     * 获取留言（降序）
     *
     * @return
     */

    public List<Message> getMessageAllByAscendingOrder() {
        return messageDao.getMessageAllByAscendingOrder();
    }

    /**
     * 获取留言（升序）
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

    public List<Message> getMessageAllByWeight() {
        return messageDao.getMessageAllByWeight();
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
