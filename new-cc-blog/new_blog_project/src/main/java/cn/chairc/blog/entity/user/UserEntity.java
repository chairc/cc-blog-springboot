package cn.chairc.blog.entity.user;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/5 20:46
 */
public class UserEntity {

    //  数据库字段

    private long id;
    private String userPrivateId;
    private String username;
    private String userEmail;
    private String password;
    private int userIsBanned;
    private int userIsActivity;
    private int userIsDelete;
    private Date createTime;
    private Date updateTime;

    //  非数据库字段

    private String userIp;
    private String userBrowser;
    private String userSystem;
    private String userCreateTime;
    private String userUpdateTime;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserIsBanned() {
        return userIsBanned;
    }

    public void setUserIsBanned(int userIsBanned) {
        this.userIsBanned = userIsBanned;
    }

    public int getUserIsActivity() {
        return userIsActivity;
    }

    public void setUserIsActivity(int userIsActivity) {
        this.userIsActivity = userIsActivity;
    }

    public int getUserIsDelete() {
        return userIsDelete;
    }

    public void setUserIsDelete(int userIsDelete) {
        this.userIsDelete = userIsDelete;
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

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(String userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", username='" + username + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", userIsBanned=" + userIsBanned +
                ", userIsActivity=" + userIsActivity +
                ", userIsDelete=" + userIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userIp='" + userIp + '\'' +
                ", userBrowser='" + userBrowser + '\'' +
                ", userSystem='" + userSystem + '\'' +
                ", userCreateTime='" + userCreateTime + '\'' +
                ", userUpdateTime='" + userUpdateTime + '\'' +
                '}';
    }
}
