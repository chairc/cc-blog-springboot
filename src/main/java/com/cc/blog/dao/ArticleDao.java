package com.cc.blog.dao;

import com.cc.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {
    List<Article> getArticleAll_index();

    List<Article> getArticleAll();

    List<Article> getArticleByPrivateId(String articlePrivateId);
}
