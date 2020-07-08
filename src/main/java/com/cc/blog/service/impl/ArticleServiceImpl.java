package com.cc.blog.service.impl;

import com.cc.blog.mapper.ArticleDao;
import com.cc.blog.model.Article;
import com.cc.blog.model.ResultSet;
import com.cc.blog.model.User;
import com.cc.blog.service.ArticleService;
import com.cc.blog.service.UserService;
import com.cc.blog.util.Tools;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private UserService userService;

    /**
     * 主页获取文章
     *
     * @return
     */

    @Override
    public List<Article> getArticleAllToIndex() {
        return articleDao.getArticleAllToIndex();
    }

    /**
     * 获取文章（降序）
     *
     * @return
     */

    @Override
    public List<Article> getArticleAll() {
        return articleDao.getArticleAll();
    }

    /**
     * 获取文章（升序）
     *
     * @return
     */

    @Override
    public List<Article> getArticleAllByAscendingOrder() {
        return articleDao.getArticleAllByAscendingOrder();
    }

    /**
     * 文章私有ID获取文章
     *
     * @param articlePrivateId
     * @return
     */

    @Override
    public Article getArticleByPrivateId(String articlePrivateId) {
        return articleDao.getArticleByPrivateId(articlePrivateId);
    }

    /**
     * 管理员获取文章
     *
     * @return
     */

    @Override
    public ResultSet getArticleAllByAdmin(int pageNum){
        ResultSet resultSet = new ResultSet();
        try {
            String username = Tools.usernameSessionValidate();
            User admin = userService.getUserByUsername(username);
            if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
                Page<Article> articlePages = PageHelper.startPage(pageNum, 10);
                List<Article> articleList = articleDao.getArticleAllByAdmin();
                resultSet.success("超级管理员文章列表获取成功");
                resultSet.setData(articleList);
            }else {
                resultSet.fail("超级管理员文章列表获取失败");
            }
        } catch (Exception e) {
            resultSet.error();
        }
        return resultSet;
    }

    /**
     * 新增文章
     *
     * @param article
     */

    @Override
    public void insertArticle(Article article) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            articleDao.insertArticle(article);
        }
    }

    /**
     * 通过私有ID删除文章
     *
     * @param privateId
     */

    @Override
    public void deleteArticleByPrivateId(String privateId) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            articleDao.deleteArticleByPrivateId(privateId);
        }
    }

    /**
     * 更新文章
     *
     * @param article
     */

    @Override
    public void updateArticle(Article article) {
        String username = Tools.usernameSessionValidate();
        User admin = userService.getUserByUsername(username);
        if(Tools.usernameSessionIsAdminValidate(admin.getUser_safe_role())){
            articleDao.updateArticle(article);
        }

    }

    /**
     * 获取文章条数
     *
     * @return 文章条数
     */

    @Override
    public Integer getArticleCount(){
        return articleDao.getArticleCount();
    }
}
