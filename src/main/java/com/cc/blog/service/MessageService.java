package com.cc.blog.service;

import com.cc.blog.model.Message;

import java.util.List;

public interface MessageService {

    /**
     * 获取主页留言
     *
     * @return
     */

    List<Message> getMessageAllToIndex();

    /**
     * 获取主页留言
     *
     * @return
     */

    List<Message> getMessageAllToIndexByWeight();

    /**
     * 获取留言（降序）
     *
     * @return
     */
    List<Message> getMessageAllByAscendingOrder();

    /**
     * 获取留言（升序）
     *
     * @return
     */
    List<Message> getMessageAll();

    /**
     * 获取精选留言
     *
     * @return
     */
    List<Message> getMessageAllByWeight();

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
    void deleteMessage(String privateId);

    /**
     * 更新留言
     *
     * @param message
     */
    void updateMessage(Message message);

    /**
     * 获取留言数
     *
     * @return
     */
    Integer getMessageCount();
}
