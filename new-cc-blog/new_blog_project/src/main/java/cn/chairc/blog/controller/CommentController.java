package cn.chairc.blog.controller;

import cn.chairc.blog.annotation.LogVisitor;
import cn.chairc.blog.entity.comment.CommentMessageEntity;
import cn.chairc.blog.entity.comment.CommentMessageInsertEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.mapper.comment.CommentMessageMapper;
import cn.chairc.blog.service.CommentService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping("/api/comment")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * 新增留言评论
     *
     * @param commentMessageInsertEntity 传入留言评论类
     * @param bindingResult              绑定结果
     * @return 成功或异常
     */

    @LogVisitor(value = "ignored", level = "LEVEL-2")
    @RequestMapping("/insertCommentMessage")
    @ResponseBody
    public ResultSet insertCommentMessage(@Valid @RequestBody CommentMessageInsertEntity commentMessageInsertEntity,
                                          BindingResult bindingResult) {
        CommentMessageEntity commentMessageEntity = new CommentMessageEntity();
        try {
            Date date = TimeUtil.getServerTime();
            commentMessageEntity.setCommentMessagePrivateId(CommonUtil.createRandomPrivateId("comment_message"));
            commentMessageEntity.setMessagePrivateId(commentMessageInsertEntity.getMessagePrivateId());
            commentMessageEntity.setCommentMessageUserPrivateId(CommonUtil.sessionValidate("userPrivateId"));
            commentMessageEntity.setCommentMessageContent(commentMessageInsertEntity.getCommentMessageContent());
            commentMessageEntity.setCommentMessagePrivateIdReply("");
            commentMessageEntity.setCommentMessageUserPrivateIdReply("");
            commentMessageEntity.setCommentMessageIsDelete(1);
            commentMessageEntity.setCreateTime(date);
            commentMessageEntity.setUpdateTime(date);
        } catch (Exception e) {
            log.error("封装数据出错，原因：{}", e.toString());
        }
        return commentService.insertCommentMessage(commentMessageEntity, bindingResult);
    }
}
