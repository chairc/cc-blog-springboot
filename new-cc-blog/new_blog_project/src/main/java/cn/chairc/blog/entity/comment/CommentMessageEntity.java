package cn.chairc.blog.entity.comment;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/8/22 19:02
 */
public class CommentMessageEntity {

    private long id;
    private String commentMessagePrivateId;
    private String commentMessageUserPrivateId;
    private String commentMessageContent;
    private String commentMessagePrivateIdReply;
    private String commentMessageUserPrivateIdReply;
    private int commentMessageIsDelete;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCommentMessagePrivateId() {
        return commentMessagePrivateId;
    }

    public void setCommentMessagePrivateId(String commentMessagePrivateId) {
        this.commentMessagePrivateId = commentMessagePrivateId;
    }

    public String getCommentMessageUserPrivateId() {
        return commentMessageUserPrivateId;
    }

    public void setCommentMessageUserPrivateId(String commentMessageUserPrivateId) {
        this.commentMessageUserPrivateId = commentMessageUserPrivateId;
    }

    public String getCommentMessageContent() {
        return commentMessageContent;
    }

    public void setCommentMessageContent(String commentMessageContent) {
        this.commentMessageContent = commentMessageContent;
    }

    public String getCommentMessagePrivateIdReply() {
        return commentMessagePrivateIdReply;
    }

    public void setCommentMessagePrivateIdReply(String commentMessagePrivateIdReply) {
        this.commentMessagePrivateIdReply = commentMessagePrivateIdReply;
    }

    public String getCommentMessageUserPrivateIdReply() {
        return commentMessageUserPrivateIdReply;
    }

    public void setCommentMessageUserPrivateIdReply(String commentMessageUserPrivateIdReply) {
        this.commentMessageUserPrivateIdReply = commentMessageUserPrivateIdReply;
    }

    public int getCommentMessageIsDelete() {
        return commentMessageIsDelete;
    }

    public void setCommentMessageIsDelete(int commentMessageIsDelete) {
        this.commentMessageIsDelete = commentMessageIsDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CommentMessageEntity{" +
                "id=" + id +
                ", commentMessagePrivateId='" + commentMessagePrivateId + '\'' +
                ", commentMessageUserPrivateId='" + commentMessageUserPrivateId + '\'' +
                ", commentMessageContent='" + commentMessageContent + '\'' +
                ", commentMessagePrivateIdReply='" + commentMessagePrivateIdReply + '\'' +
                ", commentMessageUserPrivateIdReply='" + commentMessageUserPrivateIdReply + '\'' +
                ", commentMessageIsDelete=" + commentMessageIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
