package cn.chairc.blog.entity.verification;

import java.util.Date;

/**
 * 用户验证码类
 *
 * @author chairc
 * @date 2021/6/7 13:54
 */
public class VerificationCodeEntity {
    private long id;
    private String userPrivateId;
    private String userEmail;
    private String userVerificationCode;
    private String userVerificationCodeType;
    private String userVerificationCodeIsDone;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserPrivateId() {
        return userPrivateId;
    }

    public void setUserPrivateId(String userPrivateId) {
        this.userPrivateId = userPrivateId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserVerificationCode() {
        return userVerificationCode;
    }

    public void setUserVerificationCode(String userVerificationCode) {
        this.userVerificationCode = userVerificationCode;
    }

    public String getUserVerificationCodeType() {
        return userVerificationCodeType;
    }

    public void setUserVerificationCodeType(String userVerificationCodeType) {
        this.userVerificationCodeType = userVerificationCodeType;
    }

    public String getUserVerificationCodeIsDone() {
        return userVerificationCodeIsDone;
    }

    public void setUserVerificationCodeIsDone(String userVerificationCodeIsDone) {
        this.userVerificationCodeIsDone = userVerificationCodeIsDone;
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
        return "VerificationCodeEntity{" +
                "id=" + id +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userVerificationCode='" + userVerificationCode + '\'' +
                ", userVerificationCodeType='" + userVerificationCodeType + '\'' +
                ", userVerificationCodeIsDone='" + userVerificationCodeIsDone + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
