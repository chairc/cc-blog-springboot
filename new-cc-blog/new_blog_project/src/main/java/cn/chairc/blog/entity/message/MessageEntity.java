package cn.chairc.blog.entity.message;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/8/22 19:10
 */
public class MessageEntity{
    private long id;
    private String messagePrivateId;
    private String messageUserPrivateId;
    private String messageContent;
    private int messageIsDelete;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String messageUserHeadImg;
    private String messageCreateTime;
    private String messageUpdateTime;
    private int messageCommentTotal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessagePrivateId() {
        return messagePrivateId;
    }

    public void setMessagePrivateId(String messagePrivateId) {
        this.messagePrivateId = messagePrivateId;
    }

    public String getMessageUserPrivateId() {
        return messageUserPrivateId;
    }

    public void setMessageUserPrivateId(String messageUserPrivateId) {
        this.messageUserPrivateId = messageUserPrivateId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageIsDelete() {
        return messageIsDelete;
    }

    public void setMessageIsDelete(int messageIsDelete) {
        this.messageIsDelete = messageIsDelete;
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

    public String getMessageUserHeadImg() {
        return messageUserHeadImg;
    }

    public void setMessageUserHeadImg(String messageUserHeadImg) {
        this.messageUserHeadImg = messageUserHeadImg;
    }

    public String getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(String messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public String getMessageUpdateTime() {
        return messageUpdateTime;
    }

    public void setMessageUpdateTime(String messageUpdateTime) {
        this.messageUpdateTime = messageUpdateTime;
    }

    public int getMessageCommentTotal() {
        return messageCommentTotal;
    }

    public void setMessageCommentTotal(int messageCommentTotal) {
        this.messageCommentTotal = messageCommentTotal;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", messagePrivateId='" + messagePrivateId + '\'' +
                ", messageUserPrivateId='" + messageUserPrivateId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageIsDelete=" + messageIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", messageUserHeadImg='" + messageUserHeadImg + '\'' +
                ", messageCreateTime='" + messageCreateTime + '\'' +
                ", messageUpdateTime='" + messageUpdateTime + '\'' +
                ", messageCommentTotal=" + messageCommentTotal +
                '}';
    }
}
