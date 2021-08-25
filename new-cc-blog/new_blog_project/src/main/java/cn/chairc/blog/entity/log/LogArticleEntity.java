package cn.chairc.blog.entity.log;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/8/16 18:18
 */
public class LogArticleEntity {

    private long id;
    private String logArticlePrivateId;
    private String logArticleUserPrivateId;
    private String logArticleMethodName;
    private String logArticleOperate;
    private String logArticleLevel;
    private String logArticleDescription;
    private String logArticleIp;
    private String logArticleBrowserVersion;
    private String logArticleSystemVersion;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogArticlePrivateId() {
        return logArticlePrivateId;
    }

    public void setLogArticlePrivateId(String logArticlePrivateId) {
        this.logArticlePrivateId = logArticlePrivateId;
    }

    public String getLogArticleUserPrivateId() {
        return logArticleUserPrivateId;
    }

    public void setLogArticleUserPrivateId(String logArticleUserPrivateId) {
        this.logArticleUserPrivateId = logArticleUserPrivateId;
    }

    public String getLogArticleMethodName() {
        return logArticleMethodName;
    }

    public void setLogArticleMethodName(String logArticleMethodName) {
        this.logArticleMethodName = logArticleMethodName;
    }

    public String getLogArticleOperate() {
        return logArticleOperate;
    }

    public void setLogArticleOperate(String logArticleOperate) {
        this.logArticleOperate = logArticleOperate;
    }

    public String getLogArticleLevel() {
        return logArticleLevel;
    }

    public void setLogArticleLevel(String logArticleLevel) {
        this.logArticleLevel = logArticleLevel;
    }

    public String getLogArticleDescription() {
        return logArticleDescription;
    }

    public void setLogArticleDescription(String logArticleDescription) {
        this.logArticleDescription = logArticleDescription;
    }

    public String getLogArticleIp() {
        return logArticleIp;
    }

    public void setLogArticleIp(String logArticleIp) {
        this.logArticleIp = logArticleIp;
    }

    public String getLogArticleBrowserVersion() {
        return logArticleBrowserVersion;
    }

    public void setLogArticleBrowserVersion(String logArticleBrowserVersion) {
        this.logArticleBrowserVersion = logArticleBrowserVersion;
    }

    public String getLogArticleSystemVersion() {
        return logArticleSystemVersion;
    }

    public void setLogArticleSystemVersion(String logArticleSystemVersion) {
        this.logArticleSystemVersion = logArticleSystemVersion;
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
        return "LogArticleEntity{" +
                "id=" + id +
                ", logArticlePrivateId='" + logArticlePrivateId + '\'' +
                ", logArticleUserPrivateId='" + logArticleUserPrivateId + '\'' +
                ", logArticleMethodName='" + logArticleMethodName + '\'' +
                ", logArticleOperate='" + logArticleOperate + '\'' +
                ", logArticleLevel='" + logArticleLevel + '\'' +
                ", logArticleDescription='" + logArticleDescription + '\'' +
                ", logArticleIp='" + logArticleIp + '\'' +
                ", logArticleBrowserVersion='" + logArticleBrowserVersion + '\'' +
                ", logArticleSystemVersion='" + logArticleSystemVersion + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
