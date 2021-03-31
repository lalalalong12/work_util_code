package com.ywltest.springdemo.util;

/**
 * @ProjectName: work_util_code
 * @Package: com.ywltest.springdemo.util
 * @ClassName: SplitTest
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/3/9 8:42
 * @Version: 1.0
 */
public class SplitTest {


    public static void main(String[] args) {

        String clientid =  "GW_${GATEWAYAP_MQTT_FX}_${Client}";

        //分割字符串时特殊字符采用\\转义
        String[] split = clientid.split("_\\u0024");

        String[] split1 = clientid.split("_\\$");

        System.out.println(split[0]);
    }
}
