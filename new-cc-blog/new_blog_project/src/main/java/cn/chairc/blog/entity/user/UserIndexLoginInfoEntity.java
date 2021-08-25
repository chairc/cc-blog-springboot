package cn.chairc.blog.entity.user;

/**
 * @author chairc
 * @date 2021/7/4 17:50
 */
public class UserIndexLoginInfoEntity {

    private int userIsLogin = 0;
    private String username = "";
    private String userPrivateId = "";
    private String userEmail = "";
    private int userMessageNum = 0;
    private String userHeadUrl = "";

    public int getUserIsLogin() {
        return userIsLogin;
    }

    public void setUserIsLogin(int userIsLogin) {
        this.userIsLogin = userIsLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPrivateId() {
        return userPrivateId;
    }

    public void setUserPrivateId(String userPrivateId) {
        this.userPrivateId = userPrivateId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserMessageNum() {
        return userMessageNum;
    }

    public void setUserMessageNum(int userMessageNum) {
        this.userMessageNum = userMessageNum;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    @Override
    public String toString() {
        return "UserIndexLoginInfoEntity{" +
                "userIsLogin=" + userIsLogin +
                ", username='" + username + '\'' +
                ", userPrivateId='" + userPrivateId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userMessageNum=" + userMessageNum +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                '}';
    }
}
