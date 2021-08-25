package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.common.ResultSet;
import cn.chairc.blog.entity.log.LogArticleEntity;
import cn.chairc.blog.entity.log.LogVisitorEntity;
import cn.chairc.blog.mapper.log.LogArticleMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.mapper.log.LogVisitorMapper;
import cn.chairc.blog.service.LogService;
import cn.chairc.blog.utils.CommonUtil;
import cn.chairc.blog.utils.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chairc
 * @date 2021/5/25 17:46
 */
@Service
public class LogServiceImpl implements LogService {

    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    private LogVisitorMapper logVisitorMapper;

    private LogArticleMapper logArticleMapper;

    private UserMapper userMapper;

    @Autowired
    public LogServiceImpl(LogVisitorMapper logVisitorMapper,LogArticleMapper logArticleMapper,
                          UserMapper userMapper) {
        this.logVisitorMapper = logVisitorMapper;
        this.logArticleMapper = logArticleMapper;
        this.userMapper = userMapper;
    }

    /**
     * 获取访问日志列表
     *
     * @return 访问日志列表
     */

    @Override
    public List<LogVisitorEntity> listLogVisitor() {
        List<LogVisitorEntity> logVisitorEntityList = new ArrayList<>();
        try {
            logVisitorEntityList = logVisitorMapper.listLogVisitor();
            for (LogVisitorEntity logVisitorEntity : logVisitorEntityList) {
                logVisitorEntity.setLogVisitorCreateTime(TimeUtil.exchangeTimeTypeDateToString(logVisitorEntity.getCreateTime()));
            }
            /*  log.info("用户{}获取访问日志列表成功", CommonUtil.sessionValidate("userPrivateId"));*/
        } catch (Exception e) {
            log.info("用户{}获取访问日志列表失败，原因：{}", CommonUtil.sessionValidate("userPrivateId"), e.toString());
        }
        return logVisitorEntityList;
    }

    /**
     * 通过访问日志私有ID获取信息
     *
     * @param visitorLogPrivateId 访问日志私有ID
     * @return 访问日志信息
     */

    @Override
    public ResultSet getLogVisitor(String visitorLogPrivateId) {
        ResultSet resultSet = new ResultSet();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            LogVisitorEntity logVisitorEntity = logVisitorMapper.getLogVisitor(visitorLogPrivateId);
            logVisitorEntity.setLogVisitorCreateTime(TimeUtil.exchangeTimeTypeDateToString(logVisitorEntity.getCreateTime()));
            logVisitorEntity.setLogVisitorUpdateTime(TimeUtil.exchangeTimeTypeDateToString(logVisitorEntity.getUpdateTime()));
            resultSet.setData(logVisitorEntity);
            resultSet.ok("获取访问日志成功");
            log.info("用户{}获取访问日志{}成功", userPrivateId, visitorLogPrivateId);
        } catch (Exception e) {
            resultSet.fail("获取访问日志失败");
            log.info("用户{}获取访问日志{}失败，原因：{}", userPrivateId, visitorLogPrivateId, e.toString());
        }
        return resultSet;
    }

    /**
     * 新增访问日志
     *
     * @param logVisitorEntity 访问日志
     */

    @Override
    public void insertLogVisitor(LogVisitorEntity logVisitorEntity) {
        try {
            logVisitorMapper.insertLogVisitor(logVisitorEntity);
        } catch (Exception e) {
            log.error("访问日志记录失败，原因：{}", e.toString());
        }
    }

    @Override
    public void insertLogArticle(LogArticleEntity logArticleEntity) {
        try {
            logArticleMapper.insertLogArticle(logArticleEntity);
        } catch (Exception e) {
            log.error("文章日志记录失败，原因：{}", e.toString());
        }
    }
}
