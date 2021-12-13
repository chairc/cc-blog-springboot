package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.message.MessageEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/9 21:58
 */
public interface MessageService {

    /**
     * 获取留言板列表
     *
     * @return 留言列表
     */

    List<MessageEntity> listMessage();

    /**
     * 新增留言
     *
     * @param messageEntity 留言类
     * @param bindingResult 绑定结果
     * @return 成功或异常
     */

    ResultSet insertMessage(MessageEntity messageEntity,BindingResult bindingResult);

    /**
     * 获取留言
     *
     * @param messagePrivateId 留言私有ID
     * @return 留言类
     */

    MessageEntity getMessage(String messagePrivateId);
}
