package cn.chairc.blog.mapper.article;

import cn.chairc.blog.entity.article.ArticleTypeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章类型Mapper接口
 * 接口说明：该接口对应一个数据表
 * 1.article_type表：该表用于存储所有文章类型
 *
 * @author chairc
 * @date 2021/7/10 16:50
 */
@Mapper
public interface ArticleTypeMapper {

    /**
     * 获取所有文章类型
     *
     * @return List<ArticleTypeEntity>
     */

    List<ArticleTypeEntity> listArticleType();

    /**
     * 通过文章类型查询信息
     *
     * @param articleTypeName 文章类型
     * @return 文章类型信息
     */

    ArticleTypeEntity getArticleType(String articleTypeName);

    /**
     * 查询类型是否唯一
     *
     * @param articleTypeName 文章类型
     * @return 存在或不存在
     */

    boolean getArticleTypeIsExist(String articleTypeName);

    /**
     * 新增文章类型
     *
     * @param articleTypeEntity 文章类型类
     */

    void insertArticleType(ArticleTypeEntity articleTypeEntity);

    /**
     * 更新文章类型
     *
     * @param articleTypeEntity 文章类型类
     */

    void updateArticleType(ArticleTypeEntity articleTypeEntity);
}
