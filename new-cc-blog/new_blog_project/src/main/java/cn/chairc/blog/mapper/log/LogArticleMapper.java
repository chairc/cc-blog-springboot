package cn.chairc.blog.mapper.log;

import cn.chairc.blog.entity.log.LogArticleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/8/19 15:36
 */
@Mapper
public interface LogArticleMapper {

    /**
     * 获取访问文章日志列表
     *
     * @return 访问文章日志列表
     */

    List<LogArticleEntity> listLogArticle();

    /**
     * 通过访问文章日志私有ID获取信息
     *
     * @param logArticlePrivateId 访问文章日志私有ID
     * @return 访问文章日志信息
     */

    LogArticleEntity getLogArticle(String logArticlePrivateId);

    /**
     * 新增访问文章日志
     *
     * @param logArticleEntity 访问日志
     */

    void insertLogArticle(LogArticleEntity logArticleEntity);
}
