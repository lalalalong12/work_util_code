package com.ywltest.springdemo.config.kafka.constants;

/**
 * <p>
 * kafka 常量池
 * </p>
 *
 * @package: com.xkcoding.mq.kafka.constants
 * @description: kafka 常量池
 * @author: yangwenlongb
 * @date: Created in 2020-01-21 14:52
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 */
public interface KafkaConsts {
    /**
     * 默认分区大小
     */
    Integer DEFAULT_PARTITION_NUM = 3;


    /**
     * 数据上报遥测主题
     */
    String RULE_TELEMETRY = "tos-telemetry-report";


    /**
     * 信令下发回复：指令监听主题
     */
     String INVOKE_REPLY = "tos-function-invoke-reply";

    /**
     * 设备链路在线状态监听
     */
     String CHAIN_REPLY = "tos-chain-status-reply";
}
