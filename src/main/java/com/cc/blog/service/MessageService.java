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

    public List<Message> getMessageAll_index() {
        return messageDao.getMessageAll_index();
    }

    public List<Message> getMessageAll_index_weight() {
        return messageDao.getMessageAll_index_weight();
    }

    public List<Message> getMessageAll() {
        return messageDao.getMessageAll();
    }
}
