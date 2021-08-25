package cn.chairc.blog.entity.user;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/7/21 17:22
 */
public class UserHeadPictureEntity {

    private long id;
    private String userPrivateId;
    private String userHeadMappingUrl;
    private String userHeadMappingThumbnailUrl;
    private int userHeadIsDelete;
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

    public String getUserHeadMappingUrl() {
        return userHeadMappingUrl;
    }

    public void setUserHeadMappingUrl(String userHeadMappingUrl) {
        this.userHeadMappingUrl = userHeadMappingUrl;
    }

    public String getUserHeadMappingThumbnailUrl() {
        return userHeadMappingThumbnailUrl;
    }

    public void setUserHeadMappingThumbnailUrl(String userHeadMappingThumbnailUrl) {
        this.userHeadMappingThumbnailUrl = userHeadMappingThumbnailUrl;
    }

    public int getUserHeadIsDelete() {
        return userHeadIsDelete;
    }

    public void setUserHeadIsDelete(int userHeadIsDelete) {
        this.userHeadIsDelete = userHeadIsDelete;
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
        return "UserHeadPictureEntity{" +
                "id=" + id +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", userHeadMappingUrl='" + userHeadMappingUrl + '\'' +
                ", userHeadMappingThumbnailUrl='" + userHeadMappingThumbnailUrl + '\'' +
                ", userHeadIsDelete=" + userHeadIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
