package com.cc.blog.service.impl;

import com.cc.blog.mapper.ArticleDao;
import com.cc.blog.model.Article;
import com.cc.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    /**
     * 主页获取文章
     *
     * @return
     */

    public List<Article> getArticleAllToIndex() {
        return articleDao.getArticleAllToIndex();
    }

    /**
     * 获取文章（降序）
     *
     * @return
     */

    public List<Article> getArticleAll() {
        return articleDao.getArticleAll();
    }

    /**
     * 获取文章（升序）
     *
     * @return
     */

    public List<Article> getArticleAllByAscendingOrder() {
        return articleDao.getArticleAllByAscendingOrder();
    }

    /**
     * 文章私有ID获取文章
     *
     * @param articlePrivateId
     * @return
     */

    public List<Article> getArticleByPrivateId(String articlePrivateId) {
        return articleDao.getArticleByPrivateId(articlePrivateId);
    }

    /**
     * 新增文章
     *
     * @param article
     */

    public void insertArticle(Article article) {
        articleDao.insertArticle(article);
    }

    /**
     * 通过私有ID删除文章
     *
     * @param privateId
     */

    public void deleteArticleByPrivateId(String privateId) {
        articleDao.deleteArticleByPrivateId(privateId);
    }

    /**
     * 更新文章
     *
     * @param article
     */

    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    /**
     * 获取文章条数
     *
     * @return
     */

    public Integer getArticleCount(){
        return articleDao.getArticleCount();
    }
}
