package cn.chairc.blog.entity.article;

import java.util.Date;

/**
 * @author chairc
 * @date 2021/7/10 16:47
 */
public class ArticleTypeEntity {

    private long id;
    private String articleTypeName;
    private int articleTypeIsDelete;
    private Date createTime;
    private Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleTypeName() {
        return articleTypeName;
    }

    public void setArticleTypeName(String articleTypeName) {
        this.articleTypeName = articleTypeName;
    }

    public int getArticleTypeIsDelete() {
        return articleTypeIsDelete;
    }

    public void setArticleTypeIsDelete(int articleTypeIsDelete) {
        this.articleTypeIsDelete = articleTypeIsDelete;
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
        return "ArticleTypeEntity{" +
                "id=" + id +
                ", articleTypeName='" + articleTypeName + '\'' +
                ", articleTypeIsDelete=" + articleTypeIsDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
