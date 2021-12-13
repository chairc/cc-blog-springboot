package cn.chairc.blog.service;

import cn.chairc.blog.entity.comment.CommentMessageEntity;
import cn.chairc.blog.entity.common.ResultSet;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/9 21:58
 */
public interface CommentService {

    /**
     * 列出留言板评论
     *
     * @param type             用户类型
     * @param messagePrivateId 具体留言私有ID
     * @return 留言板评论列表
     */

    List<CommentMessageEntity> listCommentMessage(String type, String messagePrivateId);

    /**
     * 新增留言评论
     *
     * @param commentMessageEntity 留言评论类
     * @param bindingResult 绑定结果
     * @return 成功或异常
     */

    ResultSet insertCommentMessage(CommentMessageEntity commentMessageEntity, BindingResult bindingResult);
}
