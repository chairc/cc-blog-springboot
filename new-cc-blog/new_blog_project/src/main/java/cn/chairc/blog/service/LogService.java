package cn.chairc.blog.service;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.log.LogArticleEntity;
import cn.chairc.blog.entity.log.LogVisitorEntity;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/25 17:46
 */
public interface LogService {

    /**
     * 获取访问日志列表
     *
     * @return 访问日志列表
     */

    List<LogVisitorEntity> listLogVisitor();

    /**
     * 通过访问日志私有ID获取信息
     *
     * @param visitorLogPrivateId 访问日志私有ID
     * @return 访问日志信息
     */

    ResultSet getLogVisitor(String visitorLogPrivateId);

    /**
     * 新增访问日志
     *
     * @param logVisitorEntity 访问日志
     */

    void insertLogVisitor(LogVisitorEntity logVisitorEntity);

    /**
     * 新增文章日志
     * @param logArticleEntity 访问文章
     */

    void insertLogArticle(LogArticleEntity logArticleEntity);
}
