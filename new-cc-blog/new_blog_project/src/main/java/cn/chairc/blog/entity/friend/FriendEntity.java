package cn.chairc.blog.entity.friend;

/**
 * @author chairc
 * @date 2021/5/13 18:34
 */
public class FriendEntity{

    private long id;
    private String friendUserPrivateId;
    private String friendName;
    private String friendHeadUrl;
    private String friendIntroduction;
    private String friendEmail;
    private String friendWebsite;
    private int friendIsDelete;
    private int friendIsHide;
    private String createTime;
    private String updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFriendUserPrivateId() {
        return friendUserPrivateId;
    }

    public void setFriendUserPrivateId(String friendUserPrivateId) {
        this.friendUserPrivateId = friendUserPrivateId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendHeadUrl() {
        return friendHeadUrl;
    }

    public void setFriendHeadUrl(String friendHeadUrl) {
        this.friendHeadUrl = friendHeadUrl;
    }

    public String getFriendIntroduction() {
        return friendIntroduction;
    }

    public void setFriendIntroduction(String friendIntroduction) {
        this.friendIntroduction = friendIntroduction;
    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public String getFriendWebsite() {
        return friendWebsite;
    }

    public void setFriendWebsite(String friendWebsite) {
        this.friendWebsite = friendWebsite;
    }

    public int getFriendIsDelete() {
        return friendIsDelete;
    }

    public void setFriendIsDelete(int friendIsDelete) {
        this.friendIsDelete = friendIsDelete;
    }

    public int getFriendIsHide() {
        return friendIsHide;
    }

    public void setFriendIsHide(int friendIsHide) {
        this.friendIsHide = friendIsHide;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FriendEntity{" +
                "id=" + id +
                ", friendUserPrivateId='" + friendUserPrivateId + '\'' +
                ", friendName='" + friendName + '\'' +
                ", friendHeadUrl='" + friendHeadUrl + '\'' +
                ", friendIntroduction='" + friendIntroduction + '\'' +
                ", friendEmail='" + friendEmail + '\'' +
                ", friendWebsite='" + friendWebsite + '\'' +
                ", friendIsDelete=" + friendIsDelete +
                ", friendIsHide=" + friendIsHide +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
