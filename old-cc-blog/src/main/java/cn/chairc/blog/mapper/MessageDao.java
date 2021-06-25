package cn.chairc.blog.mapper;

import cn.chairc.blog.model.Article;
import cn.chairc.blog.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDao {

    /**
     * 主页获取留言
     */

    List<Message> getMessageAllToIndex();

    /**
     * 主页获取精选留言
     */

    List<Message> getMessageAllToIndexByWeight();

    /**
     * 获取留言（降序）
     */

    List<Message> getMessageAll();

    /**
     * 获取留言（升序）
     */

    List<Message> getMessageAllByAscendingOrder();

    /**
     * 获取精选留言
     */

    List<Message> getMessageAllByWeight();

    /**
     * 通过用户名获取留言
     *
     * @param username
     */

    Message getMessageByUsername(String username);

    /**
     * 管理员获取留言
     */

    List<Message> getMessageAllByAdmin();

    /**
     * 新增留言
     */

    void insertMessage(Message message);

    /**
     * 通过私有ID删除留言
     */

    void deleteMessageByPrivateId(String privateId);

    /**
     * 修改留言
     */

    void updateMessage(Message message);

    /**
     * 获取留言条数
     */

    Integer getMessageCount();

    /**
     * 通过私有ID获取留言
     */

    Message getMessageByPrivateId(String privateId);
}
