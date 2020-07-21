package cn.chairc.blog.model;

public class HeadImage {

    private int head_image_id;              //  头像id
    private String head_image_private_id;   //  头像私有id
    private String head_image_url;          //  头像链接

    public int getHead_image_id() {
        return head_image_id;
    }

    public void setHead_image_id(int head_image_id) {
        this.head_image_id = head_image_id;
    }

    public String getHead_image_private_id() {
        return head_image_private_id;
    }

    public void setHead_image_private_id(String head_image_private_id) {
        this.head_image_private_id = head_image_private_id;
    }

    public String getHead_image_url() {
        return head_image_url;
    }

    public void setHead_image_url(String head_image_url) {
        this.head_image_url = head_image_url;
    }

    @Override
    public String toString() {
        return "HeadImage{" +
                "head_image_id=" + head_image_id +
                ", head_image_private_id='" + head_image_private_id + '\'' +
                ", head_image_url='" + head_image_url + '\'' +
                '}';
    }
}
