package cn.chairc.blog.entity.user;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/5 20:57
 */
public class UserPermissionEntity {

    private long id;
    private String userPrivateId;
    private String userPermission;
    private int userPermissionStatus;
    private int userPermissionIsDelete;
    private Date createTime;
    private Date updateTime;

    private String username;
    private String userPermissionCreateTime;
    private String userPermissionUpdateTime;

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

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public int getUserPermissionStatus() {
        return userPermissionStatus;
    }

    public void setUserPermissionStatus(int userPermissionStatus) {
        this.userPermissionStatus = userPermissionStatus;
    }

    public int getUserPermissionIsDelete() {
        return userPermissionIsDelete;
    }

    public void setUserPermissionIsDelete(int userPermissionIsDelete) {
        this.userPermissionIsDelete = userPermissionIsDelete;
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

    public String getUserPermissionCreateTime() {
        return userPermissionCreateTime;
    }

    public void setUserPermissionCreateTime(String userPermissionCreateTime) {
        this.userPermissionCreateTime = userPermissionCreateTime;
    }

    public String getUserPermissionUpdateTime() {
        return userPermissionUpdateTime;
    }

    public void setUserPermissionUpdateTime(String userPermissionUpdateTime) {
        this.userPermissionUpdateTime = userPermissionUpdateTime;
    }

    @Override
    public String toString() {
        return "UserPermissionEntity{" +
                "id=" + id +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", userPermission='" + userPermission + '\'' +
                ", userPermissionStatus=" + userPermissionStatus +
                ", userPermissionIsDelete=" + userPermissionIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", username='" + username + '\'' +
                ", userPermissionCreateTime='" + userPermissionCreateTime + '\'' +
                ", userPermissionUpdateTime='" + userPermissionUpdateTime + '\'' +
                '}';
    }
}
