package com.ywltest.springdemo.service;

import com.ywltest.springdemo.SpringdemoApplicationTests;
import com.ywltest.springdemo.domain.dto.DataSendDto;
import com.ywltest.springdemo.kafka.KafkaProducer;



import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * ehcache缓存测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-16 16:58
 */
@Slf4j
public class UserServiceTest extends SpringdemoApplicationTests {

//    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    KafkaProducer kafkaProducer;


    @Test
    public void sendMessage(){

        DataSendDto dataSendDto = new DataSendDto();
        dataSendDto.setTopic("xxx-yyyy");
        dataSendDto.setContent("1111111111111111");
        kafkaProducer.send(dataSendDto);

    }
}
