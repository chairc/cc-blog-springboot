package cn.chairc.blog.mapper.message;

import cn.chairc.blog.entity.message.MessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/8/22 19:20
 */
@Mapper
public interface MessageMapper {

    List<MessageEntity> listMessage();

    MessageEntity getMessage(String messagePrivateId);

    void insertMessage(MessageEntity messageEntity);

    void deleteMessage(String messagePrivateId);

    void updateMessage(MessageEntity messageEntity);
}
