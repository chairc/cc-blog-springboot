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
}
