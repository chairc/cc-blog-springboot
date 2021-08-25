package cn.chairc.blog.entity.article;

import java.util.Date;

/**
 * 文章实体类
 *
 * @author chairc
 * @date 2021/6/1 17:19
 */
public class ArticleEntity {

    /*
     * 数据库字段数据
     */

    /**
     * 自增ID
     */

    private long id;

    /**
     * 文章私有ID
     */

    private String articlePrivateId;

    /**
     * 文章题目
     */

    private String articleTitle;

    /**
     * 文章作者
     */

    private String articleAuthor;

    /**
     * 文章介绍
     */

    private String articleIntroduction;

    /**
     * 文章正文
     */

    private String articleContent;

    /**
     * 文章类型
     */

    private String articleType;

    /**
     * 文章点击数
     */

    private int articleClickNum;

    /**
     * 文章是否删除
     * 说明：1为未删除，-1为删除
     */

    private int articleIsDelete;

    /**
     * 文章是否隐藏
     * 说明：1为显示，-1为隐藏
     */

    private int articleIsHide;

    /**
     * 创建时间
     */

    private Date createTime;

    /**
     * 更新时间
     */

    private Date updateTime;

    /*
     * 非数据库字段数据
     */

    /**
     * 文章创建时间的String类
     */

    private String articleCreateTime;

    /**
     * 文章更新时间的String类
     */

    private String articleUpdateTime;

    /**
     * 标签1
     */

    private String articleLabel1;

    /**
     * 标签2
     */

    private String articleLabel2;

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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleIntroduction() {
        return articleIntroduction;
    }

    public void setArticleIntroduction(String articleIntroduction) {
        this.articleIntroduction = articleIntroduction;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public int getArticleClickNum() {
        return articleClickNum;
    }

    public void setArticleClickNum(int articleClickNum) {
        this.articleClickNum = articleClickNum;
    }

    public int getArticleIsDelete() {
        return articleIsDelete;
    }

    public void setArticleIsDelete(int articleIsDelete) {
        this.articleIsDelete = articleIsDelete;
    }

    public int getArticleIsHide() {
        return articleIsHide;
    }

    public void setArticleIsHide(int articleIsHide) {
        this.articleIsHide = articleIsHide;
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

    public String getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(String articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public String getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(String articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }

    public String getArticleLabel1() {
        return articleLabel1;
    }

    public void setArticleLabel1(String articleLabel1) {
        this.articleLabel1 = articleLabel1;
    }

    public String getArticleLabel2() {
        return articleLabel2;
    }

    public void setArticleLabel2(String articleLabel2) {
        this.articleLabel2 = articleLabel2;
    }

    @Override
    public String toString() {
        return "ArticleEntity{" +
                "id=" + id +
                ", articlePrivateId='" + articlePrivateId + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleIntroduction='" + articleIntroduction + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articleType='" + articleType + '\'' +
                ", articleClickNum=" + articleClickNum +
                ", articleIsDelete=" + articleIsDelete +
                ", articleIsHide=" + articleIsHide +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", articleCreateTime='" + articleCreateTime + '\'' +
                ", articleUpdateTime='" + articleUpdateTime + '\'' +
                ", articleLabel1='" + articleLabel1 + '\'' +
                ", articleLabel2='" + articleLabel2 + '\'' +
                '}';
    }
}
