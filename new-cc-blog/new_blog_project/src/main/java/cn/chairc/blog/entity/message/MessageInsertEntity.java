package cn.chairc.blog.entity.message;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chairc
 * @date 2021/9/26 13:44
 */
public class MessageInsertEntity {

    @NotBlank(message = "留言文本不能为空")
    @NotNull(message = "留言文本不能为空")
    @Length(min = 1,max = 255,message = "留言文本不符合要求，要求长度大于0小于255")
    private String messageContent;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "MessageInsertEntity{" +
                "messageContent='" + messageContent + '\'' +
                '}';
    }
}
