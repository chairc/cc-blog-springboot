package cn.chairc.blog.entity.friend;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author chairc
 * @date 2021/9/29 14:56
 */
public class FriendInsertEntity {

    @NotBlank(message = "友链标题不能为空")
    @NotNull(message = "友链标题不能为空")
    private String friendWebTitle;

    @NotBlank(message = "友链网站不能为空")
    @NotNull(message = "友链网站不能为空")
    @Pattern(regexp = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]", message = "友链网站格式错误")
    private String friendWebsite;

    @NotBlank(message = "友链描述不能为空")
    @NotNull(message = "友链描述不能为空")
    private String friendIntroduction;

    @NotBlank(message = "友链头像链接不能为空")
    @NotNull(message = "友链头像链接不能为空")
    @Pattern(regexp = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]", message = "友链头像链接格式错误")
    private String friendHeadUrl;

    public String getFriendWebTitle() {
        return friendWebTitle;
    }

    public void setFriendWebTitle(String friendWebTitle) {
        this.friendWebTitle = friendWebTitle;
    }

    public String getFriendWebsite() {
        return friendWebsite;
    }

    public void setFriendWebsite(String friendWebsite) {
        this.friendWebsite = friendWebsite;
    }

    public String getFriendIntroduction() {
        return friendIntroduction;
    }

    public void setFriendIntroduction(String friendIntroduction) {
        this.friendIntroduction = friendIntroduction;
    }

    public String getFriendHeadUrl() {
        return friendHeadUrl;
    }

    public void setFriendHeadUrl(String friendHeadUrl) {
        this.friendHeadUrl = friendHeadUrl;
    }

    @Override
    public String toString() {
        return "FriendInsertEntity{" +
                "friendWebTitle='" + friendWebTitle + '\'' +
                ", friendWebsite='" + friendWebsite + '\'' +
                ", friendIntroduction='" + friendIntroduction + '\'' +
                ", friendHeadUrl='" + friendHeadUrl + '\'' +
                '}';
    }
}
