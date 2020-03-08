package com.cc.blog.dao;

import com.cc.blog.model.Article;
import com.cc.blog.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {

    List<Message> getMessageAll_index();

    List<Message> getMessageAll_index_weight();

    List<Message> getMessageAll();

    Article getMessageByUsername(String username);

    void deleteMessageById(int id);
}
