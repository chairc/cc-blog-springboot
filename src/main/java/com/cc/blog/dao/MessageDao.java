package com.cc.blog.dao;

import com.cc.blog.model.Article;
import com.cc.blog.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {

    /**
     * 主页获取留言
     *
     * @return
     */

    List<Message> getMessageAll_index();

    /**
     * 主页获取精选留言
     *
     * @return
     */

    List<Message> getMessageAll_index_weight();

    /**
     * 获取留言
     *
     * @return
     */

    List<Message> getMessageAll();

    /**
     * 获取精选留言
     *
     * @return
     */

    List<Message> getMessageAll_weight();

    /**
     * 通过用户名获取留言
     *
     * @param username
     * @return
     */

    Article getMessageByUsername(String username);

    /**
     * 通过ID删除留言
     *
     * @param id
     */

    void deleteMessageById(int id);
}
