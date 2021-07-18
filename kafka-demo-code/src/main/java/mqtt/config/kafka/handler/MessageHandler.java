package mqtt.config.kafka.handler;


import mqtt.config.kafka.constants.KafkaConsts;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息处理器
 * </p>
 *
 * @package: com.xkcoding.mq.kafka.handler
 * @description: 消息处理器
 * @author: yangkai.shen
 * @date: Created in 2019-01-07 14:58
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public class MessageHandler {

    private static final Logger log = LoggerFactory.getLogger(MessageHandler.class);

    @KafkaListener(topics = KafkaConsts.RULE_TELEMETRY, containerFactory = "ackContainerFactory")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment) {
        try {
            String message = (String) record.value();
            log.info("收到消息: {}", message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
