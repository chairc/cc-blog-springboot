package cn.chairc.blog.service;

import cn.chairc.blog.model.DataResultSet;
import cn.chairc.blog.model.ResultSet;
import cn.chairc.blog.model.Article;

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

    DataResultSet getArticleAllByAdmin(int pageNum);

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
     * 更新文章基本信息（不编辑文章内容）
     *
     * @param article
     * @return ResultSet
     */
    ResultSet updateArticle(Article article);

    /**
     * 编辑文章内容
     *
     * @param article
     * @return ResultSet
     */

    ResultSet editArticleByPrivateId(Article article);

    /**
     * 获取文章条数
     *
     * @return
     */

    Integer getArticleCount();
}
