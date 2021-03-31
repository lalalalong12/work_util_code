package com.ywltest.springdemo.service.ehcacheTest.impl;

import cn.hutool.http.HttpUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: work_util_code
 * @Package: com.ywltest.springdemo.service.ehcacheTest.impl
 * @ClassName: tset
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2020/12/24 10:27
 * @Version: 1.0
 */
@Component
public class tset {

    @Scheduled(cron = "0/5 * * * * ?")
    public  void main1() {


        String responseContent = "ccccc";
//       String responseContent = HttpUtil.post
//               ("http://10.19.146.56:28013/api/iocs-communication/v2/iotDeviceController/msgPushByIds","[42]");
        System.out.println(responseContent);
    }
}
