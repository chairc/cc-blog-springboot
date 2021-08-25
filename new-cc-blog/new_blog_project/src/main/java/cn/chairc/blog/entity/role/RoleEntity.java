package cn.chairc.blog.entity.role;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/6/7 13:26
 */
public class RoleEntity {

    private long id;
    private String roleName;
    private String roleDescription;
    private int roleIsDelete;
    private Date createTime;
    private Date updateTime;

    private String roleNameBefore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public int getRoleIsDelete() {
        return roleIsDelete;
    }

    public void setRoleIsDelete(int roleIsDelete) {
        this.roleIsDelete = roleIsDelete;
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

    public String getRoleNameBefore() {
        return roleNameBefore;
    }

    public void setRoleNameBefore(String roleNameBefore) {
        this.roleNameBefore = roleNameBefore;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleIsDelete=" + roleIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", roleNameBefore='" + roleNameBefore + '\'' +
                '}';
    }
}
