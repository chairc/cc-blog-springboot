package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.message.MessageEntity;
import cn.chairc.blog.entity.message.MessageInsertEntity;
import cn.chairc.blog.service.MessageService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/9 21:55
 */
@Controller
@RequestMapping("/api/message")
public class MessageController {

    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 新增留言
     *
     * @param messageInsertEntity 新增留言信息
     * @param bindingResult 绑定结果
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-2")
    @RequestMapping("/insertMessage")
    @ResponseBody
    public ResultSet insertMessage(@Valid @RequestBody MessageInsertEntity messageInsertEntity,
                                   BindingResult bindingResult) {
        MessageEntity messageEntity = new MessageEntity();
        try {
            Date date = TimeUtil.getServerTime();
            messageEntity.setMessagePrivateId(CommonUtil.createRandomPrivateId("message"));
            messageEntity.setMessageUserPrivateId(CommonUtil.sessionValidate("userPrivateId"));
            messageEntity.setMessageContent(messageInsertEntity.getMessageContent());
            messageEntity.setMessageIsDelete(1);
            messageEntity.setCreateTime(date);
            messageEntity.setUpdateTime(date);
        } catch (Exception e) {
            log.error("封装数据出错，原因：{}", e.toString());
        }
        return messageService.insertMessage(messageEntity,bindingResult);
    }
}
