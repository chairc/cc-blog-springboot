package cn.chairc.blog.entity.log;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/6/30 15:33
 */
public class LogUserLoginEntity {

    private long id;
    private String userPrivateId;
    private String userIp;
    private String userBrowser;
    private String userSystem;
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

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserBrowser() {
        return userBrowser;
    }

    public void setUserBrowser(String userBrowser) {
        this.userBrowser = userBrowser;
    }

    public String getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(String userSystem) {
        this.userSystem = userSystem;
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
        return "UserLoginLogEntity{" +
                "id=" + id +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", userIp='" + userIp + '\'' +
                ", userBrowser='" + userBrowser + '\'' +
                ", userSystem='" + userSystem + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
