package com.ywltest.springdemo.kafka;


import com.ywltest.springdemo.domain.dto.DataSendDto;
import com.ywltest.springdemo.kafka.constants.KafkaConsts;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.kafka
 * @ClassName: kafkaProducer
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/1/21 10:01
 * @Version: 1.0
 */
@Component
//@Slf4j
public class KafkaProducer {


    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static boolean flag;

    public boolean send(DataSendDto dataSendDto) {
        log.debug("ddddddddddddddd");

        log.debug("kafka准备发送消息为：{}",  dataSendDto.getContent());

        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(dataSendDto.getTopic(), dataSendDto.getContent());
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.warn(KafkaConsts.RULE_TELEMETRY + " - 生产者 发送消息失败：" + throwable.getMessage());
                flag = false;
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.debug(KafkaConsts.RULE_TELEMETRY + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
                flag = true;
            }
        });

        System.out.println(flag);
        return flag;
    }
}

