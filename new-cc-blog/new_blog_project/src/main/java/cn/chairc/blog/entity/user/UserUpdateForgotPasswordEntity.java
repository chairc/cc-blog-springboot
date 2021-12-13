package cn.chairc.blog.entity.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author chairc
 * @date 2021/9/1 17:49
 */
public class UserUpdateForgotPasswordEntity {

    @NotBlank(message = "注册邮箱不能为空")
    @NotNull(message = "注册邮箱不能为空")
    @Email(message = "注册邮箱格式不正确")
    private String forgotEmail;

    @NotBlank(message = "新密码不能为空")
    @NotNull(message = "新密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度不符合要求，要求长度大于6小于16")
    private String forgotPassword;

    @NotBlank(message = "新密码不能为空")
    @NotNull(message = "新密码不能为空")
    @Length(min = 6,max = 16,message = "密码长度不符合要求，要求长度大于6小于16")
    private String forgotRetypePassword;

    @NotBlank(message = "验证码不能为空")
    @NotNull(message = "验证码不能为空")
    @Pattern(regexp = "^\\d{6}$",message = "验证码格式不正确")
    private String forgotVerificationCode;

    public String getForgotEmail() {
        return forgotEmail;
    }

    public void setForgotEmail(String forgotEmail) {
        this.forgotEmail = forgotEmail;
    }

    public String getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(String forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public String getForgotRetypePassword() {
        return forgotRetypePassword;
    }

    public void setForgotRetypePassword(String forgotRetypePassword) {
        this.forgotRetypePassword = forgotRetypePassword;
    }

    public String getForgotVerificationCode() {
        return forgotVerificationCode;
    }

    public void setForgotVerificationCode(String forgotVerificationCode) {
        this.forgotVerificationCode = forgotVerificationCode;
    }

    @Override
    public String toString() {
        return "UserUpdateForgoPassword{" +
                "forgotEmail='" + forgotEmail + '\'' +
                ", forgotPassword='" + forgotPassword + '\'' +
                ", forgotRetypePassword='" + forgotRetypePassword + '\'' +
                ", forgotVerificationCode='" + forgotVerificationCode + '\'' +
                '}';
    }
}
