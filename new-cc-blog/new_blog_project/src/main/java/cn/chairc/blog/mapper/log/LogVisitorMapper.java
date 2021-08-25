package cn.chairc.blog.mapper.log;

import cn.chairc.blog.entity.log.LogVisitorEntity;
import cn.chairc.blog.entity.statistics.StatisticsDataResultSet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/31 21:42
 */
@Mapper
public interface LogVisitorMapper {

    /**
     * 获取访问日志列表
     *
     * @return 访问日志列表
     */

    List<LogVisitorEntity> listLogVisitor();

    /**
     * 通过访问日志私有ID获取信息
     *
     * @param logVisitorPrivateId 访问日志私有ID
     * @return 访问日志信息
     */

    LogVisitorEntity getLogVisitor(String logVisitorPrivateId);

    /**
     * 新增访问日志
     *
     * @param logVisitorEntity 访问日志
     */

    void insertLogVisitor(LogVisitorEntity logVisitorEntity);

    /**
     * 查询用户访问量
     *
     * @param day 需要查询的日期范围
     * @return 访问量
     */

    int getCurrentVisitorNumber(int day);

    /**
     * 查询指定日期范围内的每日用户访问量
     * @param day 需要查询的日期范围
     * @return 访问数据统计类
     */

    List<StatisticsDataResultSet> listCurrentVisitorNumber(int day);
}
