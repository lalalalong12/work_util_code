package common.util.common;

import cn.hutool.core.date.DateUtil;

import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.common.util
 * @ClassName: Dateutil
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/1/11 10:56
 * @Version: 1.0
 */
public class DateTimeUtil {

    /**
     * 记录时间格式,小时
     */
    private final static String DATE_TIME_FORMATTER = "yyyyMMddHH";

    /**
     * 记录时间格式,天
     */
    private final static String DATE_DAY_FORMATTER = "yyyyMMdd";

    /**
     * 获取当前时间上一个小时，格式yyyyMMddHH
     *
     * @return String
     */
    public static String getLastTime() {

        //可以对du每个时间域zhi单独修改
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - 1);
        return DateUtil.format(c.getTime(), DATE_TIME_FORMATTER);
    }

    /**
     * 获取当前时间，格式yyyyMMddHH
     *
     * @return String
     */
    public static String getNowTime() {
        return DateUtil.format(new Date(), DATE_TIME_FORMATTER);
    }



    /**
     * 获取当天，格式yyyyMMdd
     *
     * @return String
     */
    public static String getNowDay() {

        return DateUtil.format(new Date(), DATE_DAY_FORMATTER);
    }


    /**
     * 获取前一天，格式yyyyMMdd
     *
     * @return String
     */
    public static String getBeforeDayTime() {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 1);
        return DateUtil.format(c.getTime(), DATE_DAY_FORMATTER);
    }


    /**
     * 获取当前时间前7天，格式yyyyMMddHH
     *
     * @return String
     */
    public static String getLastDayTime() {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) - 7);
        return DateUtil.format(c.getTime(), DATE_TIME_FORMATTER);

    }

}
