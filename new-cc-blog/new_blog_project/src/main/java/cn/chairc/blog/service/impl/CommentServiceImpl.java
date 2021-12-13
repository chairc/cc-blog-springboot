package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.comment.CommentMessageEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.user.UserEntity;
import cn.chairc.blog.entity.user.UserHeadPictureEntity;
import cn.chairc.blog.mapper.comment.CommentMessageMapper;
import cn.chairc.blog.mapper.user.UserHeadPictureMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.service.CommentService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import cn.chairc.blog.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chairc
 * @date 2021/5/9 21:58
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private static final int MAX_LENGTH = 255;

    private CommentMessageMapper commentMessageMapper;

    private UserHeadPictureMapper userHeadPictureMapper;

    private UserMapper userMapper;

    @Autowired
    public CommentServiceImpl(CommentMessageMapper commentMessageMapper, UserHeadPictureMapper userHeadPictureMapper,
                              UserMapper userMapper) {
        this.commentMessageMapper = commentMessageMapper;
        this.userHeadPictureMapper = userHeadPictureMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<CommentMessageEntity> listCommentMessage(String type, String messagePrivateId) {
        List<CommentMessageEntity> commentMessageEntityList = new ArrayList<>();
        try {
            commentMessageEntityList = commentMessageMapper.listCommentMessage(messagePrivateId);
            if (commentMessageEntityList != null) {
                for (CommentMessageEntity commentMessageEntity : commentMessageEntityList) {
                    String username = "已注销用户", userHeadImg = "/static/images/picture/login/login-ico.svg";
                    UserEntity userEntity = userMapper.getUserByPrivateId(commentMessageEntity.getCommentMessageUserPrivateId());
                    UserHeadPictureEntity userHeadPicture = userHeadPictureMapper.getUserHeadPicture(commentMessageEntity.getCommentMessageUserPrivateId());
                    if (userEntity != null) {
                        username = userEntity.getUsername();
                    }
                    if (userHeadPicture != null) {
                        userHeadImg = userHeadPicture.getUserHeadMappingThumbnailUrl();
                    }
                    commentMessageEntity.setUsername(username);
                    commentMessageEntity.setUserHeadImg(userHeadImg);
                    commentMessageEntity.setCommentMessageCreateTime(TimeUtil.exchangeTimeTypeDateToString(commentMessageEntity.getCreateTime()));
                }
            } else {
                log.warn("留言{}暂无评论", messagePrivateId);
            }
        } catch (Exception e) {
            log.error("留言{}发送错误，原因：{}", messagePrivateId, e.toString());
        }
        return commentMessageEntityList;
    }

    /**
     * 新增留言评论
     *
     * @param commentMessageEntity 留言评论类
     * @param bindingResult        绑定结果
     * @return 成功或异常
     */

    @Override
    public ResultSet insertCommentMessage(CommentMessageEntity commentMessageEntity, BindingResult bindingResult) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        String messagePrivateId = commentMessageEntity.getMessagePrivateId();
        String commentMessagePrivateId = commentMessageEntity.getCommentMessagePrivateId();
        try {
            if (!ValidationUtil.validationUserIsOnline()) {
                log.warn("新增留言{}评论{}错误，原因：用户未登录", messagePrivateId, commentMessagePrivateId);
                resultSet.unauthorized();
                return resultSet;
            }
            //  对于传入值一般校验
            if (bindingResult.hasErrors()) {
                resultSet.fail(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
                return resultSet;
            }
            commentMessageMapper.insertCommentMessage(commentMessageEntity);
            resultSet.ok("评论成功");
            log.info("用户{}新增留言评论成功", userPrivateId);
        } catch (Exception e) {
            log.error("新增留言{}评论{}错误，原因：{}", messagePrivateId, commentMessagePrivateId, e.toString());
        }
        return resultSet;
    }
}
