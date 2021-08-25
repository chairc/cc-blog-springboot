package cn.chairc.blog.service;

import cn.chairc.blog.entity.statistics.StatisticsAdminEntity;
import cn.chairc.blog.entity.statistics.StatisticsDataResultSet;

import java.util.List;

/**
 * @author chairc
 * @date 2021/5/24 16:27
 */
public interface StatisticsService {

    /**
     * 获取管理员统计基本数据
     *
     * @return 管理员统计类
     */

    StatisticsAdminEntity getAdminStatistics();

    /**
     * 获取指定日期内用户访问数
     *
     * @param day 日期范围
     * @return 访问量
     */

    List<StatisticsDataResultSet> listCurrentVisitorNumber(int day);

    /**
     * 获取指定日期内用户每日活跃数
     *
     * @param day 日期范围
     * @return 活跃数表
     */

    List<StatisticsDataResultSet> listCurrentActiveData(int day);
}
