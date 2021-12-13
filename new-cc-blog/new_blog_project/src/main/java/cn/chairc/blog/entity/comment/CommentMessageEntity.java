package cn.chairc.blog.entity.comment;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/8/22 19:02
 */
public class CommentMessageEntity {

    private long id;
    private String commentMessagePrivateId;
    private String messagePrivateId;
    private String commentMessageUserPrivateId;
    private String commentMessageContent;
    private String commentMessagePrivateIdReply;
    private String commentMessageUserPrivateIdReply;
    private int commentMessageIsDelete;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String userHeadImg;
    private String commentMessageCreateTime;

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

    public String getMessagePrivateId() {
        return messagePrivateId;
    }

    public void setMessagePrivateId(String messagePrivateId) {
        this.messagePrivateId = messagePrivateId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getCommentMessageCreateTime() {
        return commentMessageCreateTime;
    }

    public void setCommentMessageCreateTime(String commentMessageCreateTime) {
        this.commentMessageCreateTime = commentMessageCreateTime;
    }

    @Override
    public String toString() {
        return "CommentMessageEntity{" +
                "id=" + id +
                ", commentMessagePrivateId='" + commentMessagePrivateId + '\'' +
                ", messagePrivateId='" + messagePrivateId + '\'' +
                ", commentMessageUserPrivateId='" + commentMessageUserPrivateId + '\'' +
                ", commentMessageContent='" + commentMessageContent + '\'' +
                ", commentMessagePrivateIdReply='" + commentMessagePrivateIdReply + '\'' +
                ", commentMessageUserPrivateIdReply='" + commentMessageUserPrivateIdReply + '\'' +
                ", commentMessageIsDelete=" + commentMessageIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", userHeadImg='" + userHeadImg + '\'' +
                ", commentMessageCreateTime='" + commentMessageCreateTime + '\'' +
                '}';
    }
}