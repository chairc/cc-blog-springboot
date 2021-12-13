package cn.chairc.blog.mapper.comment;

import cn.chairc.blog.entity.comment.CommentMessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/9/7 18:18
 */
@Mapper
public interface CommentMessageMapper {

    /**
     * 获取留言板评论列表
     *
     * @param messagePrivateId 留言私有ID
     * @return 留言板评论列表
     */

    List<CommentMessageEntity> listCommentMessage(String messagePrivateId);

    /**
     * 获取指定留言板评论
     *
     * @param commentMessagePrivateId 留言板评论私有ID
     * @return 留言板评论类
     */

    CommentMessageEntity getCommentMessage(String commentMessagePrivateId);

    /**
     * 获取留言的评论回复数
     *
     * @param messagePrivateId 留言私有ID
     * @return 评论回复数
     */

    Integer getCommentMessageTotal(String messagePrivateId);

    /**
     * 获取指定留言板内容是否存在
     *
     * @param commentMessagePrivateId 留言板评论私有ID
     * @return 存在或不存在
     */

    boolean getCommentMessageIsExist(String commentMessagePrivateId);

    /**
     * 新增留言板评论
     *
     * @param commentMessageEntity 留言板评论类
     */

    void insertCommentMessage(CommentMessageEntity commentMessageEntity);

    /**
     * 更新留言板评论
     *
     * @param commentMessageEntity 留言板评论类
     */

    void updateCommentMessage(CommentMessageEntity commentMessageEntity);

    /**
     * 永久删除留言板评论
     *
     * @param commentMessagePrivateId 留言板评论私有ID
     */

    void deleteCommentMessage(String commentMessagePrivateId);
}
