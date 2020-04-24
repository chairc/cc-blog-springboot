package com.cc.blog.mapper;

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

    List<Message> getMessageAllToIndex();

    /**
     * 主页获取精选留言
     *
     * @return
     */

    List<Message> getMessageAllToIndexByWeight();

    /**
     * 获取留言（降序）
     *
     * @return
     */

    List<Message> getMessageAll();

    /**
     * 获取留言（升序）
     *
     * @return
     */

    List<Message> getMessageAllByAscendingOrder();

    /**
     * 获取精选留言
     *
     * @return
     */

    List<Message> getMessageAllByWeight();

    /**
     * 通过用户名获取留言
     *
     * @param username
     * @return
     */

    Article getMessageByUsername(String username);

    /**
     * 管理员获取留言
     *
     * @return
     */

    List<Message> getMessageAllByAdmin();

    /**
     * 新增留言
     *
     * @param message
     */

    void insertMessage(Message message);

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */

    void deleteMessageByPrivateId(String privateId);

    /**
     * 修改留言
     *
     * @param message
     */

    void updateMessage(Message message);

    /**
     * 获取留言条数
     *
     * @return
     */

    Integer getMessageCount();
}
