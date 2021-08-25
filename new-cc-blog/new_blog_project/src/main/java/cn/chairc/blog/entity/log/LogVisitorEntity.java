package cn.chairc.blog.entity.log;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/27 16:43
 */
public class LogVisitorEntity {

    private long id;
    private String logVisitorPrivateId;
    private String logVisitorMethodName;
    private String logVisitorRequestUri;
    private String logVisitorRequestUrl;
    private String logVisitorParameter;
    private String logVisitorValue;
    private String logVisitorUsername;
    private String logVisitorIp;
    private String logVisitorBrowserVersion;
    private String logVisitorSystemVersion;
    private String logVisitorLevel;
    private Date createTime;
    private Date updateTime;

    private String logVisitorCreateTime;
    private String logVisitorUpdateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogVisitorPrivateId() {
        return logVisitorPrivateId;
    }

    public void setLogVisitorPrivateId(String logVisitorPrivateId) {
        this.logVisitorPrivateId = logVisitorPrivateId;
    }

    public String getLogVisitorMethodName() {
        return logVisitorMethodName;
    }

    public void setLogVisitorMethodName(String logVisitorMethodName) {
        this.logVisitorMethodName = logVisitorMethodName;
    }

    public String getLogVisitorRequestUri() {
        return logVisitorRequestUri;
    }

    public void setLogVisitorRequestUri(String logVisitorRequestUri) {
        this.logVisitorRequestUri = logVisitorRequestUri;
    }

    public String getLogVisitorRequestUrl() {
        return logVisitorRequestUrl;
    }

    public void setLogVisitorRequestUrl(String logVisitorRequestUrl) {
        this.logVisitorRequestUrl = logVisitorRequestUrl;
    }

    public String getLogVisitorParameter() {
        return logVisitorParameter;
    }

    public void setLogVisitorParameter(String logVisitorParameter) {
        this.logVisitorParameter = logVisitorParameter;
    }

    public String getLogVisitorValue() {
        return logVisitorValue;
    }

    public void setLogVisitorValue(String logVisitorValue) {
        this.logVisitorValue = logVisitorValue;
    }

    public String getLogVisitorUsername() {
        return logVisitorUsername;
    }

    public void setLogVisitorUsername(String logVisitorUsername) {
        this.logVisitorUsername = logVisitorUsername;
    }

    public String getLogVisitorIp() {
        return logVisitorIp;
    }

    public void setLogVisitorIp(String logVisitorIp) {
        this.logVisitorIp = logVisitorIp;
    }

    public String getLogVisitorBrowserVersion() {
        return logVisitorBrowserVersion;
    }

    public void setLogVisitorBrowserVersion(String logVisitorBrowserVersion) {
        this.logVisitorBrowserVersion = logVisitorBrowserVersion;
    }

    public String getLogVisitorSystemVersion() {
        return logVisitorSystemVersion;
    }

    public void setLogVisitorSystemVersion(String logVisitorSystemVersion) {
        this.logVisitorSystemVersion = logVisitorSystemVersion;
    }

    public String getLogVisitorLevel() {
        return logVisitorLevel;
    }

    public void setLogVisitorLevel(String logVisitorLevel) {
        this.logVisitorLevel = logVisitorLevel;
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

    public String getLogVisitorCreateTime() {
        return logVisitorCreateTime;
    }

    public void setLogVisitorCreateTime(String logVisitorCreateTime) {
        this.logVisitorCreateTime = logVisitorCreateTime;
    }

    public String getLogVisitorUpdateTime() {
        return logVisitorUpdateTime;
    }

    public void setLogVisitorUpdateTime(String logVisitorUpdateTime) {
        this.logVisitorUpdateTime = logVisitorUpdateTime;
    }

    @Override
    public String toString() {
        return "LogVisitorEntity{" +
                "id=" + id +
                ", logVisitorPrivateId='" + logVisitorPrivateId + '\'' +
                ", logVisitorMethodName='" + logVisitorMethodName + '\'' +
                ", logVisitorRequestUri='" + logVisitorRequestUri + '\'' +
                ", logVisitorRequestUrl='" + logVisitorRequestUrl + '\'' +
                ", logVisitorParameter='" + logVisitorParameter + '\'' +
                ", logVisitorValue='" + logVisitorValue + '\'' +
                ", logVisitorUsername='" + logVisitorUsername + '\'' +
                ", logVisitorIp='" + logVisitorIp + '\'' +
                ", logVisitorBrowserVersion='" + logVisitorBrowserVersion + '\'' +
                ", logVisitorSystemVersion='" + logVisitorSystemVersion + '\'' +
                ", logVisitorLevel='" + logVisitorLevel + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", logVisitorCreateTime='" + logVisitorCreateTime + '\'' +
                ", logVisitorUpdateTime='" + logVisitorUpdateTime + '\'' +
                '}';
    }
}
