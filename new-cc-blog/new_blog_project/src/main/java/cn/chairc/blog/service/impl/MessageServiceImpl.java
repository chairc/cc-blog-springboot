package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.message.MessageEntity;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.mapper.message.MessageMapper;
import cn.chairc.blog.mapper.user.UserHeadPictureMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.service.MessageService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairc
 * @date 2021/5/9 21:58
 */
@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

    private MessageMapper messageMapper;

    private UserMapper userMapper;

    private UserHeadPictureMapper userHeadPictureMapper;

    @Autowired
    public MessageServiceImpl(MessageMapper messageMapper, UserMapper userMapper,
                              UserHeadPictureMapper userHeadPictureMapper) {
        this.messageMapper = messageMapper;
        this.userMapper = userMapper;
        this.userHeadPictureMapper = userHeadPictureMapper;
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
     * @return 成功或异常
     */

    @Override
    public ResultSet insertMessage(MessageEntity messageEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            //  检查是否登录状态
            if (messageEntity.getMessageUserPrivateId() == null) {
                resultSet.unauthorized();
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
}
