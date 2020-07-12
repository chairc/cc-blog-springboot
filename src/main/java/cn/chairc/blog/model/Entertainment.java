package cn.chairc.blog.model;

public class Entertainment {

    //Entertainment
    private int entertainment_id;
    private String entertainment_private_id;
    private String entertainment_name;
    private String entertainment_update_time;

    //wps自动邀请（待更新）
    private int wps_id;         //wps的id
    private String wps_sid;     //wps邀请人的sid

    public int getEntertainment_id() {
        return entertainment_id;
    }

    public void setEntertainment_id(int entertainment_id) {
        this.entertainment_id = entertainment_id;
    }

    public String getEntertainment_private_id() {
        return entertainment_private_id;
    }

    public void setEntertainment_private_id(String entertainment_private_id) {
        this.entertainment_private_id = entertainment_private_id;
    }

    public String getEntertainment_name() {
        return entertainment_name;
    }

    public void setEntertainment_name(String entertainment_name) {
        this.entertainment_name = entertainment_name;
    }

    public String getEntertainment_update_time() {
        return entertainment_update_time;
    }

    public void setEntertainment_update_time(String entertainment_update_time) {
        this.entertainment_update_time = entertainment_update_time;
    }

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


}
