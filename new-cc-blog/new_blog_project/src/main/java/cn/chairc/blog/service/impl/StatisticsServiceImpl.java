package cn.chairc.blog.service.impl;

import cn.chairc.blog.entity.statistics.StatisticsAdminEntity;
import cn.chairc.blog.entity.statistics.StatisticsDataResultSet;
import cn.chairc.blog.mapper.log.LogUserLoginMapper;
import cn.chairc.blog.mapper.log.LogVisitorMapper;
import cn.chairc.blog.mapper.user.UserMapper;
import cn.chairc.blog.service.StatisticsService;
import cn.chairc.blog.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chairc
 * @date 2021/5/24 16:27
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger log = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private LogVisitorMapper logVisitorMapper;

    private LogUserLoginMapper logUserLoginMapper;

    private UserMapper userMapper;

    @Autowired
    public StatisticsServiceImpl(LogVisitorMapper logVisitorMapper, LogUserLoginMapper logUserLoginMapper,
                                 UserMapper userMapper) {
        this.logVisitorMapper = logVisitorMapper;
        this.logUserLoginMapper = logUserLoginMapper;
        this.userMapper = userMapper;
    }

    /**
     * 获取管理员统计基本数据
     *
     * @return 管理员统计类
     */

    @Override
    public StatisticsAdminEntity getAdminStatistics() {
        StatisticsAdminEntity statisticsAdminEntity = new StatisticsAdminEntity();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            //  设置时间范围为7天
            int selectDay = 7;
            //  设置的时间内访问数
            int currentVisitNumber = logVisitorMapper.getCurrentVisitorNumber(selectDay);
            //  设置时间内的活跃数
            int currentActiveData = logUserLoginMapper.getUserActiveData(selectDay);
            //  获取总人数
            int allUserNumber = userMapper.getUserTotal();
            //  对活跃度比例进行除法操作，在设置时间内的活跃数除以总人数并保留三位小数
            BigDecimal bigDecimal = new BigDecimal((double) currentActiveData / allUserNumber);
            double currentActiveDataRate = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
            //  统计数据封装
            statisticsAdminEntity.setCurrentVisitNumberInWeek(String.valueOf(currentVisitNumber));
            statisticsAdminEntity.setCurrentActiveDataInWeek(String.valueOf(currentActiveDataRate * 100));
            statisticsAdminEntity.setCurrentMessageAlerts(String.valueOf(0));
            statisticsAdminEntity.setInformationScaleMapNumber(String.valueOf(0));
            log.info("用户{}仪表面板数据获取成功", userPrivateId);
        } catch (Exception e) {
            log.error("用户{}仪表面板数据获取失败,原因：{}", userPrivateId, e.toString());
        }
        return statisticsAdminEntity;
    }

    /**
     * 获取指定日期内用户访问数
     *
     * @param day 日期范围
     * @return 访问量
     */

    @Override
    public List<StatisticsDataResultSet> listCurrentVisitorNumber(int day) {
        List<StatisticsDataResultSet> statisticsDataResultSetList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            statisticsDataResultSetList = logVisitorMapper.listCurrentVisitorNumber(day);
            log.info("用户{}最近一周访问量数据获取成功", userPrivateId);
        } catch (Exception e) {
            log.error("用户{}最近一周访问量数据获取失败,原因：{}", userPrivateId, e.toString());
        }
        return statisticsDataResultSetList;
    }

    /**
     * 获取指定日期内用户每日活跃数
     *
     * @param day 日期范围
     * @return 活跃数表
     */

    @Override
    public List<StatisticsDataResultSet> listCurrentActiveData(int day) {
        List<StatisticsDataResultSet> statisticsDataResultSetList = new ArrayList<>();
        String userPrivateId = CommonUtil.sessionValidate("userPrivateId");
        try {
            statisticsDataResultSetList = logUserLoginMapper.listCurrentActiveData(day);
            log.info("用户{}最近一周每日活跃用户数据获取成功", userPrivateId);
        } catch (Exception e) {
            log.error("用户{}最近一周每日活跃用户数据获取失败,原因：{}", userPrivateId, e.toString());
        }
        return statisticsDataResultSetList;
    }
}
