package com.cc.blog.service;

import com.cc.blog.model.Article;
import com.cc.blog.model.ResultSet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ArticleService {

    /**
     * 主页获取文章
     *
     * @return
     */

    List<Article> getArticleAllToIndex();

    /**
     * 获取文章（降序）
     *
     * @return
     */
    List<Article> getArticleAll();

    /**
     * 获取文章（升序）
     *
     * @return
     */
    List<Article> getArticleAllByAscendingOrder();

    /**
     * 文章私有ID获取文章
     *
     * @param articlePrivateId
     * @return
     */
    Article getArticleByPrivateId(String articlePrivateId);

    /**
     * 管理员获取文章
     *
     * @return
     */

    ResultSet getArticleAllByAdmin(HttpServletRequest request, int pageNum);

    /**
     * 新增文章
     *
     * @param article
     */
    void insertArticle(Article article,HttpServletRequest request);

    /**
     * 通过私有ID删除文章
     *
     * @param privateId
     */
    void deleteArticleByPrivateId(String privateId,HttpServletRequest request);

    /**
     * 更新文章
     *
     * @param article
     */
    void updateArticle(Article article,HttpServletRequest request);

    /**
     * 获取文章条数
     *
     * @return
     */

    Integer getArticleCount();
}
