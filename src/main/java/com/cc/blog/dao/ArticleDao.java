package com.cc.blog.dao;

import com.cc.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

    /**
     * 主页获取文章
     *
     * @return
     */

    List<Article> getArticleAll_index();

    /**
     * 获取文章
     *
     * @return
     */

    List<Article> getArticleAll();

    /**
     * 文章私有ID获取文章
     *
     * @param articlePrivateId
     * @return
     */

    List<Article> getArticleByPrivateId(String articlePrivateId);
}
