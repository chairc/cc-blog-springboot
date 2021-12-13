package cn.chairc.blog.entity.comment;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chairc
 * @date 2021/9/26 13:52
 */
public class CommentMessageInsertEntity {

    @NotBlank(message = "留言文本不能为空")
    @NotNull(message = "留言文本不能为空")
    private String messagePrivateId;

    @NotBlank(message = "留言文本不能为空")
    @NotNull(message = "留言文本不能为空")
    @Length(min = 1,max = 255,message = "留言文本不符合要求，要求长度大于0小于255")
    private String commentMessageContent;

    public String getMessagePrivateId() {
        return messagePrivateId;
    }

    public void setMessagePrivateId(String messagePrivateId) {
        this.messagePrivateId = messagePrivateId;
    }

    public String getCommentMessageContent() {
        return commentMessageContent;
    }

    public void setCommentMessageContent(String commentMessageContent) {
        this.commentMessageContent = commentMessageContent;
    }

    @Override
    public String toString() {
        return "CommentMessageInsertEntity{" +
                "messagePrivateId='" + messagePrivateId + '\'' +
                ", commentMessageContent='" + commentMessageContent + '\'' +
                '}';
    }
}
