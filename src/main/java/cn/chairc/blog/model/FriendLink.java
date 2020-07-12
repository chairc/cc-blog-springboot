package cn.chairc.blog.model;

public class FriendLink {

    private int friend_link_id;             //  友链id
    private String friend_link_private_id;  //  友链私有id
    private String friend_link_user;        //  友链用户名
    private String friend_link_url;         //  友链博客地址
    private int friend_link_check;          //  友链添加请求SuperAdmin确认标识位，用于显示到friend_link.html

    public int getFriend_link_id() {
        return friend_link_id;
    }

    public void setFriend_link_id(int friend_link_id) {
        this.friend_link_id = friend_link_id;
    }

    public String getFriend_link_private_id() {
        return friend_link_private_id;
    }

    public void setFriend_link_private_id(String friend_link_private_id) {
        this.friend_link_private_id = friend_link_private_id;
    }

    public String getFriend_link_user() {
        return friend_link_user;
    }

    public void setFriend_link_user(String friend_link_user) {
        this.friend_link_user = friend_link_user;
    }

    public String getFriend_link_url() {
        return friend_link_url;
    }

    public void setFriend_link_url(String friend_link_url) {
        this.friend_link_url = friend_link_url;
    }

    public int getFriend_link_check() {
        return friend_link_check;
    }

    public void setFriend_link_check(int friend_link_check) {
        this.friend_link_check = friend_link_check;
    }

    @Override
    public String toString() {
        return "FriendLink{" +
                "friend_link_id=" + friend_link_id +
                ", friend_link_private_id='" + friend_link_private_id + '\'' +
                ", friend_link_user='" + friend_link_user + '\'' +
                ", friend_link_url='" + friend_link_url + '\'' +
                ", friend_link_check=" + friend_link_check +
                '}';
    }
}
