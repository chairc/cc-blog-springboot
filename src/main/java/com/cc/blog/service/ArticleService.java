package com.cc.blog.service;

import com.cc.blog.model.Article;

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
    List<Article> getArticleByPrivateId(String articlePrivateId);

    /**
     * 新增文章
     *
     * @param article
     */
    void insertArticle(Article article);

    /**
     * 通过私有ID删除文章
     *
     * @param privateId
     */
    void deleteArticleByPrivateId(String privateId);

    /**
     * 更新文章
     *
     * @param article
     */
    void updateArticle(Article article);

    /**
     * 获取文章条数
     *
     * @return
     */

    Integer getArticleCount();
}
