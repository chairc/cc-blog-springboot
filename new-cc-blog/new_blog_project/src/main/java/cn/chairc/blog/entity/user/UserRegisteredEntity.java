package cn.chairc.blog.entity.user;

/**
 * 注册实体类，方便后期的自定义拓展
 *
 * @author chairc
 * @date 2021/6/6 22:54
 */
public class UserRegisteredEntity {

    private String registeredUsername;
    private String registeredEmail;
    private String registeredPassword;
    private String registeredRetypePassword;
    private String registeredVerificationCode;

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public void setRegisteredUsername(String registeredUsername) {
        this.registeredUsername = registeredUsername;
    }

    public String getRegisteredEmail() {
        return registeredEmail;
    }

    public void setRegisteredEmail(String registeredEmail) {
        this.registeredEmail = registeredEmail;
    }

    public String getRegisteredPassword() {
        return registeredPassword;
    }

    public void setRegisteredPassword(String registeredPassword) {
        this.registeredPassword = registeredPassword;
    }

    public String getRegisteredRetypePassword() {
        return registeredRetypePassword;
    }

    public void setRegisteredRetypePassword(String registeredRetypePassword) {
        this.registeredRetypePassword = registeredRetypePassword;
    }

    public String getRegisteredVerificationCode() {
        return registeredVerificationCode;
    }

    public void setRegisteredVerificationCode(String registeredVerificationCode) {
        this.registeredVerificationCode = registeredVerificationCode;
    }

    @Override
    public String toString() {
        return "UserRegisteredEntity{" +
                "registeredUsername='" + registeredUsername + '\'' +
                ", registeredEmail='" + registeredEmail + '\'' +
                ", registeredPassword='" + registeredPassword + '\'' +
                ", registeredRetypePassword='" + registeredRetypePassword + '\'' +
                ", registeredVerificationCode='" + registeredVerificationCode + '\'' +
                '}';
    }
}
