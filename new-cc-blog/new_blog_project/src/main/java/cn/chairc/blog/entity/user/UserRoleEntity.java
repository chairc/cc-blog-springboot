package cn.chairc.blog.entity.user;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/6/7 13:27
 */
public class UserRoleEntity {
    private long id;
    private String userPrivateId;
    private String userRole;
    private int userRoleStatus;
    private int userRoleIsDelete;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String userRoleCreateTime;
    private String userRoleUpdateTime;

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

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getUserRoleStatus() {
        return userRoleStatus;
    }

    public void setUserRoleStatus(int userRoleStatus) {
        this.userRoleStatus = userRoleStatus;
    }

    public int getUserRoleIsDelete() {
        return userRoleIsDelete;
    }

    public void setUserRoleIsDelete(int userRoleIsDelete) {
        this.userRoleIsDelete = userRoleIsDelete;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRoleCreateTime() {
        return userRoleCreateTime;
    }

    public void setUserRoleCreateTime(String userRoleCreateTime) {
        this.userRoleCreateTime = userRoleCreateTime;
    }

    public String getUserRoleUpdateTime() {
        return userRoleUpdateTime;
    }

    public void setUserRoleUpdateTime(String userRoleUpdateTime) {
        this.userRoleUpdateTime = userRoleUpdateTime;
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userRoleStatus=" + userRoleStatus +
                ", userRoleIsDelete=" + userRoleIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", userRoleCreateTime='" + userRoleCreateTime + '\'' +
                ", userRoleUpdateTime='" + userRoleUpdateTime + '\'' +
                '}';
    }
}
