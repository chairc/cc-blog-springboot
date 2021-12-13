package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.message.MessageEntity;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.mapper.comment.CommentMessageMapper;
import cn.chairc.blog.mapper.message.MessageMapper;
import cn.chairc.blog.mapper.user.UserHeadPictureMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.service.MessageService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import cn.chairc.blog.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chairc
 * @date 2021/5/9 21:58
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private static final int MAX_LENGTH = 255;

    private MessageMapper messageMapper;

    private UserMapper userMapper;

    private UserHeadPictureMapper userHeadPictureMapper;

    private CommentMessageMapper commentMessageMapper;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, UserMapper userMapper,
                              UserHeadPictureMapper userHeadPictureMapper, CommentMessageMapper commentMessageMapper) {
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
        this.userHeadPictureMapper = userHeadPictureMapper;
        this.commentMessageMapper = commentMessageMapper;
    }

    /**
     * 获取留言板列表
     *
     * @return 留言列表
     */

    @Override
    public List<MessageEntity> listMessage() {
        List<MessageEntity> messageEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            messageEntityList = messageMapper.listMessage();
            if (messageEntityList != null) {
                for (MessageEntity messageEntity : messageEntityList) {
                    String username = "已注销用户", userHeadImg = "/static/images/picture/login/login-ico.svg";
                    int messageCommentTotal = commentMessageMapper.getCommentMessageTotal(messageEntity.getMessagePrivateId());
                    UserEntity userEntity = userMapper.getUserByPrivateId(messageEntity.getMessageUserPrivateId());
                    UserHeadPictureEntity userHeadPicture = userHeadPictureMapper.getUserHeadPicture(messageEntity.getMessageUserPrivateId());
                    if (userEntity != null) {
                        username = userEntity.getUsername();
                    }
                    if (userHeadPicture != null) {
                        userHeadImg = userHeadPicture.getUserHeadMappingThumbnailUrl();
                    }
                    messageEntity.setUsername(username);
                    messageEntity.setMessageCreateTime(TimeUtil.exchangeTimeTypeDateToString(messageEntity.getCreateTime()));
                    messageEntity.setMessageUpdateTime(TimeUtil.exchangeTimeTypeDateToString(messageEntity.getUpdateTime()));
                    messageEntity.setMessageUserHeadImg(userHeadImg);
                    messageEntity.setMessageCommentTotal(messageCommentTotal);
                }
                log.info("用户{}获取留言列表成功", userPrivateId);
            } else {
                log.warn("用户{}获取留言列表失败，原因：未查到相关数据", userPrivateId);
            }
        } catch (Exception e) {
            log.error("用户{}获取留言列表失败，原因：{}", userPrivateId, e.toString());
        }
        return messageEntityList;
    }

    /**
     * 新增留言
     *
     * @param messageEntity 留言类
     * @param bindingResult 绑定结果
     * @return 成功或异常
     */

    @Override
    public ResultSet insertMessage(MessageEntity messageEntity, BindingResult bindingResult) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        String messagePrivateId = messageEntity.getMessagePrivateId();
        try {
            //  检查是否登录状态
            if (!ValidationUtil.validationUserIsOnline()) {
                log.warn("新增留言{}错误，原因：用户未登录", messagePrivateId);
                resultSet.unauthorized();
                return resultSet;
            }
            //  对于传入值一般校验
            if (bindingResult.hasErrors()) {
                resultSet.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
                return resultSet;
            }
            messageMapper.insertMessage(messageEntity);
            resultSet.ok("留言成功");
            log.info("用户{}新增留言列表成功", userPrivateId);
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}新增留言列表失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 获取留言
     *
     * @param messagePrivateId 留言私有ID
     * @return 留言类
     */

    @Override
    public MessageEntity getMessage(String messagePrivateId) {
        MessageEntity messageEntity = null;
        try {
            messageEntity = messageMapper.getMessage(messagePrivateId);
            if (messageEntity != null) {
                String username = "已注销用户", userHeadImg = "/static/images/picture/login/login-ico.svg";
                int messageCommentTotal = commentMessageMapper.getCommentMessageTotal(messagePrivateId);
                UserEntity userEntity = userMapper.getUserByPrivateId(messageEntity.getMessageUserPrivateId());
                UserHeadPictureEntity userHeadPicture = userHeadPictureMapper.getUserHeadPicture(messageEntity.getMessageUserPrivateId());
                if (userEntity != null) {
                    username = userEntity.getUsername();
                }
                if (userHeadPicture != null) {
                    userHeadImg = userHeadPicture.getUserHeadMappingThumbnailUrl();
                }
                messageEntity.setUsername(username);
                messageEntity.setMessageCreateTime(TimeUtil.exchangeTimeTypeDateToString(messageEntity.getCreateTime()));
                messageEntity.setMessageUpdateTime(TimeUtil.exchangeTimeTypeDateToString(messageEntity.getUpdateTime()));
                messageEntity.setMessageUserHeadImg(userHeadImg);
                messageEntity.setMessageCommentTotal(messageCommentTotal);
            } else {
                log.warn("获取留言{}详细信息失败，原因：未查到相关数据", messagePrivateId);
            }
        } catch (Exception e) {
            log.error("获取留言{}详细信息失败，原因：{}", messagePrivateId, e.toString());
        }
        return messageEntity;
    }
}
