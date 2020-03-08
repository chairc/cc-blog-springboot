package com.cc.blog.model;

public class Message {
    private int message_id;
    private String message_private_id;
    private String message_username;
    private String message_main;
    private String message_time;
    private String message_ip;
    private String message_weight;

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

    public String getMessage_weight() {
        return message_weight;
    }

    public void setMessage_weight(String message_weight) {
        this.message_weight = message_weight;
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
                ", message_weight='" + message_weight + '\'' +
                '}';
    }
}
