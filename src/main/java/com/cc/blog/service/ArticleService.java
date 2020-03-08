package com.cc.blog.service;

import com.cc.blog.dao.ArticleDao;
import com.cc.blog.model.Article;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    public List<Article> getArticleAll_index() {
        return articleDao.getArticleAll_index();
    }

    public List<Article> getArticleAll() {
        return articleDao.getArticleAll();
    }

    public List<Article> getArticleByPrivateId(String articlePrivateId) {
        return articleDao.getArticleByPrivateId(articlePrivateId);
    }
}
