package cn.chairc.blog.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chairc
 * @date 2021/5/6 18:03
 */
public class TimeUtil {

    private static final Logger log = LoggerFactory.getLogger(TimeUtil.class);

    /**
     * 获取服务器时间
     *
     * @return time
     */

    public static Date getServerTime() throws ParseException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        //ParsePosition pos = new ParsePosition(8);
        return simpleDateFormat.parse(dateString);
    }

    /**
     * String格式时间转为Date类
     *
     * @param time String类时间
     * @return Date类时间
     * @throws ParseException 解析异常
     */

    public static Date exchangeTimeTypeStringToDate(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(time);
    }

    /**
     * Date格式时间转为String
     *
     * @param time Date类时间
     * @return String类时间
     */

    public static String exchangeTimeTypeDateToString(Date time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }
}
