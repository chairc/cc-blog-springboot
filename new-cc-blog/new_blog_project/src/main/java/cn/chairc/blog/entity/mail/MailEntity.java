package cn.chairc.blog.entity.mail;

/**
 * @author chairc
 * @date 2021/5/9 22:26
 */
public class MailEntity {

    private String userPrivateId;
    private String emailType;
    private String userEmail;
    private String emailSubject;
    private String emailContent;

    public String getUserPrivateId() {
        return userPrivateId;
    }

    public void setUserPrivateId(String userPrivateId) {
        this.userPrivateId = userPrivateId;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    @Override
    public String toString() {
        return "MailEntity{" +
                "userPrivateId='" + userPrivateId + '\'' +
                ", emailType='" + emailType + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailContent='" + emailContent + '\'' +
                '}';
    }
}
