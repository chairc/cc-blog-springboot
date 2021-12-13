package cn.chairc.blog.entity.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 注册实体类，方便后期的自定义拓展
 *
 * @author chairc
 * @date 2021/6/6 22:54
 */
public class UserRegisteredEntity {

    @NotBlank(message = "注册用户名不能为空")
    @NotNull(message = "注册用户名不能为空")
    @Length(min = 1,max = 16,message = "用户名长度不符合要求，要求长度大于1小于16")
    @Pattern(regexp = "^\\w+$",message = "用户名格式不正确，要求为字母、数字或下划线")
    private String registeredUsername;

    @NotBlank(message = "注册邮箱不能为空")
    @NotNull(message = "注册邮箱不能为空")
    @Email(message = "注册邮箱格式不正确")
    private String registeredEmail;

    @NotBlank(message = "注册密码不能为空")
    @NotNull(message = "注册密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度不符合要求，要求长度大于6小于16")
    private String registeredPassword;

    @NotBlank(message = "注册密码不能为空")
    @NotNull(message = "注册密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度不符合要求，要求长度大于6小于16")
    private String registeredRetypePassword;

    @NotBlank(message = "验证码不能为空")
    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{6}$",message = "验证码格式不正确")
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
