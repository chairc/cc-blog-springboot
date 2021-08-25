package cn.chairc.blog.entity.article;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/7/8 18:06
 */
public class ArticleLabelInfoEntity {
    private long id;
    private String articlePrivateId;
    private String articleLabelOne;
    private String articleLabelTwo;
    private int articleLabelIsDelete;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticlePrivateId() {
        return articlePrivateId;
    }

    public void setArticlePrivateId(String articlePrivateId) {
        this.articlePrivateId = articlePrivateId;
    }

    public String getArticleLabelOne() {
        return articleLabelOne;
    }

    public void setArticleLabelOne(String articleLabelOne) {
        this.articleLabelOne = articleLabelOne;
    }

    public String getArticleLabelTwo() {
        return articleLabelTwo;
    }

    public void setArticleLabelTwo(String articleLabelTwo) {
        this.articleLabelTwo = articleLabelTwo;
    }

    public int getArticleLabelIsDelete() {
        return articleLabelIsDelete;
    }

    public void setArticleLabelIsDelete(int articleLabelIsDelete) {
        this.articleLabelIsDelete = articleLabelIsDelete;
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
        return "ArticleLabelInfoEntity{" +
                "id=" + id +
                ", articlePrivateId='" + articlePrivateId + '\'' +
                ", articleLabelOne='" + articleLabelOne + '\'' +
                ", articleLabelTwo='" + articleLabelTwo + '\'' +
                ", articleLabelIsDelete=" + articleLabelIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
