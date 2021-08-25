package cn.chairc.blog.mapper.article;

import cn.chairc.blog.entity.article.ArticleLabelEntity;
import cn.chairc.blog.entity.article.ArticleLabelInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章标签类与文章标签信息Mapper接口
 * 接口说明：该接口对应两个数据表
 * 1.article_label表：该表用于存储所有标签类型
 * 2.article_label_info表：该表用户存储文章与标签对应关系
 *
 * @author chairc
 * @date 2021/7/8 18:04
 */
@Mapper
public interface ArticleLabelMapper {

    /*
     * article_label表
     */

    /**
     * 获取所有标签
     *
     * @return List<ArticleLabelEntity>
     */

    List<ArticleLabelEntity> listArticleLabel();

    /**
     * 通过标签名获取标签
     * @param labelName 标签名称
     * @return ArticleLabelEntity
     */

    ArticleLabelEntity getArticleLabel(String labelName);

    /**
     * 新增标签
     *
     * @param articleLabelEntity 标签类
     */

    void insertArticleLabel(ArticleLabelEntity articleLabelEntity);

    /**
     * 更新标签
     *
     * @param articleLabelEntity 标签类
     */

    void updateArticleLabel(ArticleLabelEntity articleLabelEntity);

    /**
     * 查询标签是否存在
     *
     * @param labelName 标签名称
     * @return 存在或不存在
     */

    boolean getArticleLabelIsExist(String labelName);

    /*
     * article_label_info表
     */

    /**
     * 获取所有文章与对应标签信息
     *
     * @return List<ArticleLabelInfoEntity>
     */

    List<ArticleLabelInfoEntity> listArticleLabelInfo();

    /**
     * 通过标签类型获得文章私有ID信息
     *
     * @param labelType 标签类型
     * @return List<ArticleLabelInfoEntity>
     */

    List<ArticleLabelInfoEntity> listArticleLabelInfoByLabelType(String labelType);

    /**
     * 通过文章私有ID获取文章所属标签
     *
     * @param articlePrivateId 文章私有ID
     * @return ArticleLabelInfoEntity
     */

    ArticleLabelInfoEntity getArticleLabelInfo(String articlePrivateId);

    /**
     * 查找该文章标签是否存在
     *
     * @param articleLabelInfoEntity 文章标签类
     * @return 存在或不存在
     */

    boolean getArticleLabelInfoIsExist(ArticleLabelInfoEntity articleLabelInfoEntity);

    /**
     * 新增文章与标签对应信息
     *
     * @param articleLabelInfoEntity 文章标签类
     */

    void insertArticleLabelInfo(ArticleLabelInfoEntity articleLabelInfoEntity);

    /**
     * 更新文章与标签对应信息
     *
     * @param articleLabelInfoEntity 文章标签类
     */

    void updateArticleLabelInfo(ArticleLabelInfoEntity articleLabelInfoEntity);
}
