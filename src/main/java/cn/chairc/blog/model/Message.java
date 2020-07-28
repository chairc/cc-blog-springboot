package cn.chairc.blog.model;

public class Message {

    private int message_id;                 //  留言id
    private String message_private_id;      //  留言私有id
    private String message_username;        //  留言用户
    private String message_main;            //  留言主体
    private String message_time;            //  留言时间
    private String message_ip;              //  留言时ip
    private String message_system;          //  留言所用系统
    private String message_browser;         //  留言所用浏览器
    private String message_weight;          //  留言权重
    private String message_user_private_id; //  留言用户的私有ID

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getMessage_private_id() {
        return message_private_id;
    }

    public void setMessage_private_id(String message_private_id) {
        this.message_private_id = message_private_id;
    }

    public String getMessage_username() {
        return message_username;
    }

    public void setMessage_username(String message_username) {
        this.message_username = message_username;
    }

    public String getMessage_main() {
        return message_main;
    }

    public void setMessage_main(String message_main) {
        this.message_main = message_main;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public String getMessage_ip() {
        return message_ip;
    }

    public void setMessage_ip(String message_ip) {
        this.message_ip = message_ip;
    }

    public String getMessage_system() {
        return message_system;
    }

    public void setMessage_system(String message_system) {
        this.message_system = message_system;
    }

    public String getMessage_browser() {
        return message_browser;
    }

    public void setMessage_browser(String message_browser) {
        this.message_browser = message_browser;
    }

    public String getMessage_weight() {
        return message_weight;
    }

    public void setMessage_weight(String message_weight) {
        this.message_weight = message_weight;
    }

    public String getMessage_user_private_id() {
        return message_user_private_id;
    }

    public void setMessage_user_private_id(String message_user_private_id) {
        this.message_user_private_id = message_user_private_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", message_private_id='" + message_private_id + '\'' +
                ", message_username='" + message_username + '\'' +
                ", message_main='" + message_main + '\'' +
                ", message_time='" + message_time + '\'' +
                ", message_ip='" + message_ip + '\'' +
                ", message_system='" + message_system + '\'' +
                ", message_browser='" + message_browser + '\'' +
                ", message_weight='" + message_weight + '\'' +
                ", message_user_private_id='" + message_user_private_id + '\'' +
                '}';
    }
}
