package cn.chairc.blog.mapper.log;

import cn.chairc.blog.entity.log.LogUserLoginEntity;
import cn.chairc.blog.entity.statistics.StatisticsDataResultSet;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chairc
 * @date 2021/6/30 15:43
 */
@Mapper
public interface LogUserLoginMapper {

    /**
     * 获取所有登陆日志
     *
     * @return 登录日志表类
     */

    List<LogUserLoginEntity> listUserLoginLog();

    /**
     * 获取用户最近一次登录记录
     *
     * @param userPrivateId 用户私有ID
     * @return 登录日志类
     */

    LogUserLoginEntity getCurrentUserLoginLog(String userPrivateId);

    /**
     * 新增一条用户登录记录
     *
     * @param logUserLoginEntity 登录日志类
     */

    void insertUserLoginLog(LogUserLoginEntity logUserLoginEntity);

    /**
     * 获取指定日期内的登录活跃数
     *
     * @param day 日期范围
     * @return 活跃数
     */

    int getUserActiveData(int day);

    /**
     * 获取指定日期内用户每日活跃数
     *
     * @param day 日期范围
     * @return 活跃数
     */

    List<StatisticsDataResultSet> listCurrentActiveData(int day);
}
