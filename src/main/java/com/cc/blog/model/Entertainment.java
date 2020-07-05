package com.cc.blog.model;

public class Entertainment {
    //wps自动邀请（待更新）
    private int wps_id;         //wps的id
    private String wps_sid;     //wps邀请人的sid

    public int getWps_id() {
        return wps_id;
    }

    public void setWps_id(int wps_id) {
        this.wps_id = wps_id;
    }

    public String getWps_sid() {
        return wps_sid;
    }

    public void setWps_sid(String wps_sid) {
        this.wps_sid = wps_sid;
    }

    @Override
    public String toString() {
        return "Entertainment{" +
                "wps_id=" + wps_id +
                ", wps_sid='" + wps_sid + '\'' +
                '}';
    }
}
