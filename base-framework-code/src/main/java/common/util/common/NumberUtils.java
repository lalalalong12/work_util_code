package common.util.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * @ClassName NumberUtils
 * @Description 数值换算
 * @Author yang.zj
 * @Date 2020/12/7 15:10
 * @Version 1.0
 */

public class NumberUtils {

    private static final Logger log = LoggerFactory.getLogger(NumberUtils.class);

    private static final Double MILLION = 10000.0;
    private static final Double MILLIONS = 1000000.0;
    private static final Double BILLION = 100000000.0;
    public static final String NORMAL_UNIT = "条";
    public static final String MILLION_UNIT = "万条";
    public static final String BILLION_UNIT = "亿条";

    /**
     * 将数字转换成以万为单位或者以亿为单位
     *
     * @param amount
     * @return java.lang.String
     * @method amountConversion
     */
    public static Object amountConversion(double amount) {
        //最终返回的结果值
        HashMap<String, Object> result = new HashMap<>(2);
        //四舍五入后的值
        double value = 0;
        //转换后的值
        double tempValue = 0;
        //余数
        double remainder = 0;

        //金额大于1百万小于1亿
        if (amount > MILLIONS && amount < BILLION) {
            tempValue = amount / MILLION;
            remainder = amount % MILLION;
            log.info("tempValue=" + tempValue + ", remainder=" + remainder);

            //余数小于5000则不进行四舍五入
            if (remainder < (MILLION / 2)) {
                value = formatNumber(tempValue, 2, false);
            } else {
                value = formatNumber(tempValue, 2, true);
            }
            //如果值刚好是10000万，则要变成1亿
            if (value == MILLION) {
                result.put("unit",BILLION_UNIT);
                result.put("number",zeroFill(value / MILLION));
            } else {
//                result = zeroFill(value) + MILLION_UNIT;
                result.put("unit",MILLION_UNIT);
                result.put("number",zeroFill(value));
            }
        }
        // 金额大于1亿
        else if (amount > BILLION) {
            tempValue = amount / BILLION;
            remainder = amount % BILLION;
            log.info("tempValue=" + tempValue + ", remainder=" + remainder);

            // 余数小于50000000则不进行四舍五入
            if (remainder < (BILLION / 2)) {
                value = formatNumber(tempValue, 2, false);
            } else {
                value = formatNumber(tempValue, 2, true);
            }
//            result = zeroFill(value) + BILLION_UNIT;
            result.put("unit",BILLION_UNIT);
            result.put("number",zeroFill(value));
        } else {
//            result = zeroFill(amount);
            result.put("unit",NORMAL_UNIT);
            result.put("number",zeroFill(amount));
        }

        log.info("result=" + result);
        return result;
    }

    /**
     * 获取数值单位
     *
     * @param amount
     * @return java.lang.String
     * @method getUnit
     */
    public static String getUnit(double amount) {
        if (amount < MILLIONS) {
            return NORMAL_UNIT;
        }
        //金额大于1百万小于1亿
        if (amount > MILLIONS && amount < BILLION) {
            return MILLION_UNIT;
        }
        // 金额大于1亿
        if (amount > BILLION) {

            return BILLION_UNIT;
        }
        return null;
    }

    /**
     * 将数字转换成以万为单位
     *
     * @param amount
     * @return java.lang.String
     * @method cnvMillion
     */
    public static String cnvMillion(double amount) {
        //最终返回的结果值
        String result = String.valueOf(amount);
        //转换后的值
        double tempValue = 0;
        //金额大于1百万小于1亿
        tempValue = amount / MILLION;
        //四舍五入后的值
        double value = formatNumber(tempValue, 2, false);
        //如果值刚好是10000万，则要变成1亿
        if (value == MILLION) {
            result = zeroFill(value / MILLION) + BILLION_UNIT;
        } else {
            result = zeroFill(value);
        }
        return result;
    }

    /**
     * 将数字转换成以亿为单位
     *
     * @param amount
     * @return java.lang.String
     * @method cnvBillion
     */
    public static String cnvBillion(double amount) {
        //最终返回的结果值
        String result = String.valueOf(amount);
        //转换后的值
        double tempValue = 0;
        // 金额大于1亿
        tempValue = amount / BILLION;
        //四舍五入后的值
        double value = formatNumber(tempValue, 2, false);
        result = zeroFill(value);
        return result;
    }

    /**
     * 转换数值
     *
     * @param unit
     * @param lineCount
     * @return double
     * @method getLineCount
     */
    public static double getLineCount(String unit, double lineCount) {
        if (MILLION_UNIT.equals(unit)) {
            lineCount = Double.valueOf(cnvMillion(lineCount));
        } else if (BILLION_UNIT.equals(unit)) {
            lineCount = Double.valueOf(cnvBillion(lineCount));
        }
        return lineCount;
    }

    /**
     * 对数字进行四舍五入，保留2位小数
     *
     * @param number   要四舍五入的数字
     * @param decimal  保留的小数点数
     * @param rounding 是否四舍五入
     */
    public static Double formatNumber(double number, int decimal, boolean rounding) {
        BigDecimal bigDecimal = new BigDecimal(number);

        if (rounding) {
            return bigDecimal.setScale(decimal, RoundingMode.HALF_UP).doubleValue();
        } else {
            return bigDecimal.setScale(decimal, RoundingMode.DOWN).doubleValue();
        }
    }

    /**
     * 对四舍五入的数据进行补0显示，即显示.00
     *
     * @param number
     * @return java.lang.String
     * @method zeroFill
     */
    public static String zeroFill(double number) {
        String value = String.valueOf(number);
        int i = value.indexOf(".");
        if (i < 0) {
            value = value + ".00";
        } else {
            String decimalValue = value.substring(i + 1);

            if (decimalValue.length() < 2) {
                value = value + "0";
            }
        }
        String decimalValue = value.substring(i);
        if(".00".equals(decimalValue)){
            return value.substring(0,i);
        }
        return value;
    }

    /**
     * 测试方法入口
     *
     * @param args
     * @author
     * @version 1.00.00
     * @date 2018年1月18日
     */
//    public static void main(String[] args) throws Exception {
//
//        amountConversion(1222188.35);
//        amountConversion(129887783.5);
//
//        System.out.println(cnvMillion(100));
//    }


}