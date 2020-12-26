package com.ywltest.springdemo.service.impl;

import cn.hutool.http.HttpUtil;

/**
 * @ProjectName: work_util_code
 * @Package: com.ywltest.springdemo.service.impl
 * @ClassName: tset
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2020/12/24 10:27
 * @Version: 1.0
 */
public class tset {


    public static void main(String[] args) {


       String responseContent = HttpUtil.post("http://10.19.146.56:28013/api/iocs-communication/v2/iotDeviceController/msgPushByIds","[42]");
        System.out.println(responseContent);
    }
}
