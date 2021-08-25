package cn.chairc.blog.entity.information;

/**
 * 公共实体类
 *
 * @author chairc
 * @date 2021/6/27 23:48
 */
public class InformationCommonEntity {

    private String headUrl;
    private int status;
    private int messageNumber;

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    @Override
    public String toString() {
        return "CommonEntity{" +
                "headUrl='" + headUrl + '\'' +
                ", status=" + status +
                ", messageNumber=" + messageNumber +
                '}';
    }
}
