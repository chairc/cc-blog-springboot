package cn.chairc.blog.mapper;

import cn.chairc.blog.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDao {

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

    List<Article> getArticleAllByAdmin();

    /**
     * 新增文章
     *
     * @param article
     */

    void insertArticle(Article article);

    /**
     * 通过私有ID删除文章
     *
     * @param articlePrivateId
     */

    void deleteArticleByPrivateId(String articlePrivateId);

    /**
     * 更新文章基本信息（不编辑文章）
     *
     * @param article
     */

    void updateArticle(Article article);

    /**
     * 编辑文章
     * @param article
     */

    void editArticleByPrivateId(Article article);

    /**
     * 获取文章条数
     *
     * @return
     */
    Integer getArticleCount();
}
