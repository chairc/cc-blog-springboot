package cn.chairc.blog.service;

import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.model.Message;

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
     * 管理员获取留言
     *
     * @return
     */

    DataResultSet getMessageAllByAdmin(int pageNum);

    /**
     * 新增留言
     *
     * @param message
     */
    ResultSet insertMessage(Message message);

    /**
     * 通过私有ID删除留言
     *
     * @param privateId
     */
    ResultSet deleteMessage(String privateId);

    /**
     * 更新留言
     *
     * @param message
     */
    ResultSet updateMessage(Message message);

    /**
     * 获取留言数
     *
     * @return
     */
    Integer getMessageCount();

    /**
     * 通过私有Id获取留言
     * @param privateId
     * @return
     */

    ResultSet getMessageByPrivateId(String privateId);
}
