package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.message.MessageEntity;
import cn.chairc.blog.service.MessageService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/9 21:55
 */
@Controller
@RequestMapping("/api/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 新增留言
     *
     * @param messageContent 留言内容
     * @return 成功或异常
     * @throws ParseException 解析异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-2")
    @RequestMapping("/insertMessage")
    @ResponseBody
    public ResultSet insertMessage(@RequestParam(value = "messageContent") String messageContent) throws ParseException {
        Date date = TimeUtil.getServerTime();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessagePrivateId(CommonUtil.createRandomPrivateId("message"));
        messageEntity.setMessageUserPrivateId(CommonUtil.sessionValidate("userPrivateId"));
        messageEntity.setMessageContent(messageContent);
        messageEntity.setMessageIsDelete(1);
        messageEntity.setCreateTime(date);
        messageEntity.setUpdateTime(date);
        return messageService.insertMessage(messageEntity);
    }
}
