package cn.chairc.blog.entity.permission;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/5 20:46
 */
public class PermissionEntity {

    private long id;
    private String permissionName;
    private String permissionDescription;
    private int permissionIsDelete;
    private Date createTime;
    private Date updateTime;

    private String permissionNameBefore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public int getPermissionIsDelete() {
        return permissionIsDelete;
    }

    public void setPermissionIsDelete(int permissionIsDelete) {
        this.permissionIsDelete = permissionIsDelete;
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

    public String getPermissionNameBefore() {
        return permissionNameBefore;
    }

    public void setPermissionNameBefore(String permissionNameBefore) {
        this.permissionNameBefore = permissionNameBefore;
    }

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", permissionIsDelete=" + permissionIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", permissionNameBefore='" + permissionNameBefore + '\'' +
                '}';
    }
}
