package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.article.ArticleEntity;
import cn.chairc.blog.entity.article.ArticleLabelEntity;
import cn.chairc.blog.entity.article.ArticleLabelInfoEntity;
import cn.chairc.blog.entity.article.ArticleTypeEntity;
import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.mapper.article.ArticleLabelMapper;
import cn.chairc.blog.mapper.article.ArticleMapper;
import cn.chairc.blog.mapper.article.ArticleTypeMapper;
import cn.chairc.blog.service.ArticleService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章服务层
 *
 * @author chairc
 * @date 2021/5/9 21:57
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private static Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private static final String ADMIN_STRING = "admin";

    private ArticleMapper articleMapper;

    private ArticleLabelMapper articleLabelMapper;

    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleLabelMapper articleLabelMapper,
                              ArticleTypeMapper articleTypeMapper) {
        this.articleMapper = articleMapper;
        this.articleLabelMapper = articleLabelMapper;
        this.articleTypeMapper = articleTypeMapper;
    }

    /**
     * 通过文章分类获取文章列表
     *
     * @param type        判断是操作页面还是展示页面传入
     * @param articleType 文章类型
     * @return List<ArticleEntity>
     */

    @Override
    public List<ArticleEntity> listArticleByArticleType(String type, String articleType) {
        List<ArticleEntity> articleEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            /*
             * 判断界面是admin还是index
             * 对文章类型进行判断，all为查询所有文章，其余根据articleType的值进行查询
             */
            if (ADMIN_STRING.equals(type)) {
                //  管理员查询
                articleEntityList = articleMapper.listArticleByArticleTypeByAdmin(articleType);
            } else {
                //  非管理员查询
                articleEntityList = articleMapper.listArticleByArticleType(articleType);
            }
            if (articleEntityList != null) {
                for (ArticleEntity articleEntity : articleEntityList) {
                    articleEntity.setArticleCreateTime(TimeUtil.exchangeTimeTypeDateToString(articleEntity.getCreateTime()));
                    articleEntity.setArticleUpdateTime(TimeUtil.exchangeTimeTypeDateToString(articleEntity.getUpdateTime()));
                }
                log.info("用户{}获取归档列表-{} 成功", userPrivateId, articleType);
            } else {
                log.warn("用户{}获取归档列表-{} 失败，原因：未查到相关数据",userPrivateId, articleType);
            }

        } catch (Exception e) {
            log.error("用户{}获取归档列表-{} 失败，原因：{}", userPrivateId, articleType, e.toString());
        }
        return articleEntityList;
    }

    /**
     * 通过标签分类获取文章列表
     *
     * @param type         判断是操作页面还是展示页面传入
     * @param articleLabel 标签类型
     * @return List<ArticleEntity>
     */

    @Override
    public List<ArticleEntity> listArticleByArticleLabel(String type, String articleLabel) {
        List<ArticleEntity> articleEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            List<ArticleLabelInfoEntity> articleLabelInfoEntityList = articleLabelMapper.listArticleLabelInfoByLabelType(articleLabel);
            for (ArticleLabelInfoEntity articleLabelInfoEntity : articleLabelInfoEntityList) {
                ArticleEntity articleEntity;
                if (ADMIN_STRING.equals(type)) {
                    //  管理员查询
                    articleEntity = articleMapper.getArticleByAdmin(articleLabelInfoEntity.getArticlePrivateId());

                } else {
                    //  非管理员查询
                    articleEntity = articleMapper.getArticleByPrivateId(articleLabelInfoEntity.getArticlePrivateId());
                }
                if (articleEntity != null) {
                    articleEntity.setArticleCreateTime(TimeUtil.exchangeTimeTypeDateToString(articleEntity.getCreateTime()));
                    articleEntity.setArticleUpdateTime(TimeUtil.exchangeTimeTypeDateToString(articleEntity.getUpdateTime()));
                    articleEntityList.add(articleEntity);
                }
            }
            log.info("用户{}获取归档列表-{} 成功", userPrivateId, articleLabel);
        } catch (Exception e) {
            log.error("用户{}获取归档列表-{} 失败，原因：{}",userPrivateId, articleLabel, e.toString());
        }
        return articleEntityList;
    }

    /**
     * 通过文章私有ID获取文章
     *
     * @param articlePrivateId 文章私有ID
     * @param type             判断是操作页面还是展示页面传入
     * @return ArticleEntity
     */

    @Override
    public ArticleEntity getArticleByPrivateId(String articlePrivateId, String type) {
        ArticleEntity articleEntity = new ArticleEntity();
        try {
            if (ADMIN_STRING.equals(type)) {
                //  管理员查询
                articleEntity = articleMapper.getArticleByAdmin(articlePrivateId);
            } else {
                //  非管理员查询
                articleEntity = articleMapper.getArticleByPrivateId(articlePrivateId);
            }
            //  通过文章私有ID获取文章标签信息
            ArticleLabelInfoEntity articleLabelInfoEntity = articleLabelMapper.getArticleLabelInfo(articlePrivateId);
            articleEntity.setArticleLabel1(articleLabelInfoEntity.getArticleLabelOne());
            articleEntity.setArticleLabel2(articleLabelInfoEntity.getArticleLabelTwo());
            articleEntity.setArticleCreateTime(TimeUtil.exchangeTimeTypeDateToString(articleEntity.getCreateTime()));
            log.info("文章{}获取成功", articlePrivateId);
        } catch (Exception e) {
            log.error("文章{}获取失败，原因：{}", articlePrivateId, e.toString());
        }
        return articleEntity;
    }

    /**
     * 新增文章
     *
     * @param articleEntity 文章类
     * @return 成功或异常
     */

    @Override
    public ResultSet insertArticle(ArticleEntity articleEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            ArticleLabelInfoEntity articleLabelInfoEntity = new ArticleLabelInfoEntity();
            articleLabelInfoEntity.setArticlePrivateId(articleEntity.getArticlePrivateId());
            articleLabelInfoEntity.setArticleLabelOne(articleEntity.getArticleLabel1());
            articleLabelInfoEntity.setArticleLabelTwo(articleEntity.getArticleLabel2());
            articleMapper.insertArticle(articleEntity);
            articleLabelMapper.insertArticleLabelInfo(articleLabelInfoEntity);
            resultSet.ok("新增文章成功");
            log.info("用户{}新增文章{}成功", userPrivateId, articleEntity.getArticlePrivateId());
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}新增文章{}失败，原因：{}", userPrivateId, articleEntity.getArticlePrivateId(), e.toString());
        }
        return resultSet;
    }

    /**
     * 更新文章
     *
     * @param articleEntity 文章类
     * @return 成功或异常
     */

    @Override
    public ResultSet updateArticle(ArticleEntity articleEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            ArticleLabelInfoEntity articleLabelInfoEntity = new ArticleLabelInfoEntity();
            articleLabelInfoEntity.setArticlePrivateId(articleEntity.getArticlePrivateId());
            articleLabelInfoEntity.setArticleLabelOne(articleEntity.getArticleLabel1());
            articleLabelInfoEntity.setArticleLabelTwo(articleEntity.getArticleLabel2());
            articleMapper.updateArticle(articleEntity);
            if (!articleLabelMapper.getArticleLabelInfoIsExist(articleLabelInfoEntity)) {
                //  修改标签信息
                articleLabelMapper.updateArticleLabelInfo(articleLabelInfoEntity);
            }
            resultSet.ok("文章更新成功");
            log.info("用户{}更新文章{}成功", userPrivateId, articleEntity.getArticlePrivateId());
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}更新文章{}失败，原因：{}", userPrivateId, articleEntity.getArticlePrivateId(), e.toString());
        }
        return resultSet;
    }

    /**
     * 获取文章标签表
     *
     * @return List<ArticleLabelEntity>
     */

    @Override
    public List<ArticleLabelEntity> listArticleLabel() {
        List<ArticleLabelEntity> articleLabelEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            articleLabelEntityList = articleLabelMapper.listArticleLabel();
            log.info("用户{}获取文章标签成功", userPrivateId);
        } catch (Exception e) {
            log.error("用户{}获取文章标签失败，原因：{}", userPrivateId, e.toString());
        }
        return articleLabelEntityList;
    }

    /**
     * 新增文章标签
     *
     * @param articleLabelEntity 文章标签类
     * @return 成功或异常
     */

    @Override
    public ResultSet insertArticleLabel(ArticleLabelEntity articleLabelEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            articleLabelMapper.insertArticleLabel(articleLabelEntity);
            resultSet.ok("新增标签成功");
            log.info("用户{}新增文章标签-{}-成功", userPrivateId, articleLabelEntity.getLabelName());
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}新增文章标签失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 更新文章标签
     *
     * @param articleLabelEntity 文章标签类
     * @return 成功或异常
     */

    @Override
    public ResultSet updateArticleLabel(String oldArticleLabel, ArticleLabelEntity articleLabelEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            if (articleLabelMapper.getArticleLabelIsExist(oldArticleLabel)) {
                ArticleLabelEntity articleLabelTempEntity = articleLabelMapper.getArticleLabel(oldArticleLabel);
                //  将临时变量中的ID取出，用于做修改时的关键标识
                articleLabelEntity.setId(articleLabelTempEntity.getId());
                articleLabelMapper.updateArticleLabel(articleLabelEntity);
                resultSet.ok("修改标签成功");
                log.info("用户{}更新文章标签-{}-为-{}-成功", userPrivateId, oldArticleLabel, articleLabelEntity.getLabelName());
            } else {
                resultSet.fail("修改标签失败，原有标签不存在");
                log.warn("用户{}更新文章标签-{}-为-{}-失败", userPrivateId, oldArticleLabel, articleLabelEntity.getLabelName());
            }
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}更新文章标签失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 获取文章分类表
     *
     * @return List<ArticleTypeEntity>
     */

    @Override
    public List<ArticleTypeEntity> listArticleType() {
        List<ArticleTypeEntity> articleTypeEntityList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            articleTypeEntityList = articleTypeMapper.listArticleType();
            log.info("用户{}获取文章分类成功", userPrivateId);
        } catch (Exception e) {
            log.error("用户{}获取文章分类失败，原因：{}", userPrivateId, e.toString());
        }
        return articleTypeEntityList;
    }

    /**
     * 新增文章类型
     *
     * @param articleTypeEntity 文章类型类
     * @return 成功或异常
     */

    @Override
    public ResultSet insertArticleType(ArticleTypeEntity articleTypeEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            articleTypeMapper.insertArticleType(articleTypeEntity);
            resultSet.ok("新增文章类型成功");
            log.info("用户{}新增文章类型-{}-成功", userPrivateId, articleTypeEntity.getArticleTypeName());
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}新增文章类型失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 更新文章类型
     *
     * @param articleTypeEntity 文章类型类
     * @return 成功或异常
     */

    @Override
    public ResultSet updateArticleType(String oldArticleType, ArticleTypeEntity articleTypeEntity) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            if (articleTypeMapper.getArticleTypeIsExist(oldArticleType)) {
                ArticleTypeEntity articleTypeTempEntity = articleTypeMapper.getArticleType(oldArticleType);
                //  将临时变量中的ID取出，用于做修改时的关键标识
                articleTypeEntity.setId(articleTypeTempEntity.getId());
                articleTypeMapper.updateArticleType(articleTypeEntity);
                resultSet.ok("修改类型成功");
                log.info("用户{}更新文章类型-{}-为-{}-成功", userPrivateId, oldArticleType, articleTypeEntity.getArticleTypeName());
            } else {
                resultSet.fail("修改标签失败，原有标签不存在");
                log.warn("用户{}更新文章标签-{}-为-{}-失败", userPrivateId, oldArticleType, articleTypeEntity.getArticleTypeName());
            }
        } catch (Exception e) {
            resultSet.interServerError();
            log.error("用户{}更新文章类型失败，原因：{}", userPrivateId, e.toString());
        }
        return resultSet;
    }

}
