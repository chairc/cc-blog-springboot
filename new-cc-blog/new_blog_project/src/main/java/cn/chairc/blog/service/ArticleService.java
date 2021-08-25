package cn.chairc.blog.service;

import cn.chairc.blog.entity.article.ArticleEntity;
import cn.chairc.blog.entity.article.ArticleLabelEntity;
import cn.chairc.blog.entity.article.ArticleTypeEntity;
import cn.chairc.blog.entity.common.ResultSet;

import java.util.List;

/**
 * 文章服务接口层
 *
 * @author chairc
 * @date 2021/5/9 21:57
 */
public interface ArticleService {

    /**
     * 通过文章分类获取文章列表
     *
     * @param type        判断是操作页面还是展示页面传入
     * @param articleType 文章类型
     * @return List<ArticleEntity>
     */

    List<ArticleEntity> listArticleByArticleType(String type, String articleType);

    /**
     * 通过标签分类获取文章列表
     *
     * @param type         判断是操作页面还是展示页面传入
     * @param articleLabel 标签类型
     * @return List<ArticleEntity>
     */

    List<ArticleEntity> listArticleByArticleLabel(String type, String articleLabel);

    /**
     * 通过文章私有ID获取文章
     *
     * @param articlePrivateId 文章私有ID
     * @param type             判断是操作页面还是展示页面传入
     * @return ArticleEntity
     */

    ArticleEntity getArticleByPrivateId(String articlePrivateId, String type);

    /**
     * 新增文章
     *
     * @param articleEntity 文章类
     * @return 成功或异常
     */

    ResultSet insertArticle(ArticleEntity articleEntity);

    /**
     * 更新文章
     *
     * @param articleEntity 文章类
     * @return 成功或异常
     */

    ResultSet updateArticle(ArticleEntity articleEntity);

    /**
     * 获取文章标签表
     *
     * @return List<ArticleLabelEntity>
     */

    List<ArticleLabelEntity> listArticleLabel();

    /**
     * 新增文章标签
     *
     * @param articleLabelEntity 文章标签类
     * @return 成功或异常
     */

    ResultSet insertArticleLabel(ArticleLabelEntity articleLabelEntity);

    /**
     * 更新文章标签
     *
     * @param articleLabelEntity 文章标签类
     * @return 成功或异常
     */

    ResultSet updateArticleLabel(String oldArticleLabel, ArticleLabelEntity articleLabelEntity);

    /**
     * 获取文章分类表
     *
     * @return List<ArticleTypeEntity>
     */

    List<ArticleTypeEntity> listArticleType();

    /**
     * 新增文章类型
     *
     * @param articleTypeEntity 文章类型类
     * @return 成功或异常
     */

    ResultSet insertArticleType(ArticleTypeEntity articleTypeEntity);

    /**
     * 更新文章类型
     *
     * @param articleTypeEntity 文章类型类
     * @return 成功或异常
     */

    ResultSet updateArticleType(String oldArticleType, ArticleTypeEntity articleTypeEntity);

}
