package cn.chairc.blog.entity.article;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/7/8 18:05
 */
public class ArticleLabelEntity {

    private long id;
    private String labelName;
    private int labelIsDelete;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getLabelIsDelete() {
        return labelIsDelete;
    }

    public void setLabelIsDelete(int labelIsDelete) {
        this.labelIsDelete = labelIsDelete;
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

    @Override
    public String toString() {
        return "ArticleLabelEntity{" +
                "id=" + id +
                ", labelName='" + labelName + '\'' +
                ", labelIsDelete=" + labelIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
