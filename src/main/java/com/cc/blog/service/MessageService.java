package com.cc.blog.service;

import com.cc.blog.model.Message;
import com.cc.blog.model.ResultSet;

import javax.servlet.http.HttpServletRequest;
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
    List<Message> getMessageAllByAscendingOrder(HttpServletRequest request);

    /**
     * 获取留言（升序）
     *
     * @return
     */
    List<Message> getMessageAll(HttpServletRequest request);

    /**
     * 获取精选留言
     *
     * @return
     */
    List<Message> getMessageAllByWeight(HttpServletRequest request);

    /**
     * 管理员获取留言
     *
     * @param request
     * @return
     */

    ResultSet getMessageAllByAdmin(HttpServletRequest request, int pageNum);

    /**
     * 新增留言
     *
     * @param message
     */
    void insertMessage(Message message, HttpServletRequest request);

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */
    void deleteMessage(String privateId, HttpServletRequest request);

    /**
     * 更新留言
     *
     * @param message
     */
    void updateMessage(Message message, HttpServletRequest request);

    /**
     * 获取留言数
     *
     * @return
     */
    Integer getMessageCount();
}
