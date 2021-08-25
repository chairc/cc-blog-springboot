package cn.chairc.blog.mapper.article;

import cn.chairc.blog.entity.article.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章类Mapper接口
 *
 * @author chairc
 * @date 2021/6/1 17:19
 */
@Mapper
public interface ArticleMapper {

    /**
     * 通过文章类型获得文章列表
     *
     * @param articleType 文章类型
     * @return List<ArticleEntity>
     */

    List<ArticleEntity> listArticleByArticleType(String articleType);

    /**
     * 管理员通过文章类型获得文章列表
     *
     * @param articleType 文章类型
     * @return List<ArticleEntity>
     */

    List<ArticleEntity> listArticleByArticleTypeByAdmin(String articleType);

    /**
     * 通过文章私有ID获得文章
     *
     * @param articlePrivateId 文章私有ID
     * @return ArticleEntity
     */

    ArticleEntity getArticleByPrivateId(String articlePrivateId);

    /**
     * 管理员通过文章私有ID获得文章
     *
     * @param articlePrivateId 文章私有ID
     * @return ArticleEntity
     */

    ArticleEntity getArticleByAdmin(String articlePrivateId);

    /**
     * 新增文章
     *
     * @param articleEntity 文章类
     */

    void insertArticle(ArticleEntity articleEntity);

    /**
     * 删除文章（数据库中永久删除）
     *
     * @param articlePrivateId 文章私有ID
     */

    void deleteArticle(String articlePrivateId);

    /**
     * 更新文章（一般用于更新文章、删除文章、隐藏文章）
     *
     * @param articleEntity 文章类
     */

    void updateArticle(ArticleEntity articleEntity);
}
