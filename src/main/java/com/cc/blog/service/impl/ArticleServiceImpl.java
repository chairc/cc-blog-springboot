package com.cc.blog.service.impl;

import com.cc.blog.mapper.ArticleDao;
import com.cc.blog.model.Article;
import com.cc.blog.service.ArticleService;
import com.cc.blog.util.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

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
     * 管理员获取文章
     *
     * @param request
     * @return
     */

    public List<Article> getArticleAllByAdmin(HttpServletRequest request){
        if(Tools.usernameSessionIsAdminValidate(request)){
            return articleDao.getArticleAllByAdmin();
        }
        return null;
    }

    /**
     * 新增文章
     *
     * @param article
     */

    public void insertArticle(Article article,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            articleDao.insertArticle(article);
        }
    }

    /**
     * 通过私有ID删除文章
     *
     * @param privateId
     */

    public void deleteArticleByPrivateId(String privateId,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            articleDao.deleteArticleByPrivateId(privateId);
        }
    }

    /**
     * 更新文章
     *
     * @param article
     */

    public void updateArticle(Article article,HttpServletRequest request) {
        if(Tools.usernameSessionIsAdminValidate(request)){
            articleDao.updateArticle(article);
        }

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
