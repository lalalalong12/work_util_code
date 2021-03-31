package com.ywltest.springdemo.config.mqtt.config;


import cn.hutool.extra.spring.SpringUtil;
import com.ywltest.springdemo.domain.dto.MqttClientDto;
import com.ywltest.springdemo.util.common.SystemUtil;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.config.mqtt
 * @ClassName: EnnMqttClient
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2020/10/13 9:19
 * @Version: 1.0
 */
@Component
public class MqttClientTool {


    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MqttClientTool.class);



    @Autowired
    EnnMqttGatewayClient gatewayClient;



    private static List<String> topicsGateway = new ArrayList<>();;

    private static List<String> topicsClient = new ArrayList<>();;

    /**
     * 监听中心侧下发的topic
     */
    public String[] getTopics() {
        //默认监听指令下发
        topicsClient.add("/+/+/function/invoke");
        String[] topic= topicsClient.toArray(new String[0]);
        return topic;
    }




    /**
     * 连接客户端
     *
     * @param
     * @return void
     * @method connect
     */
    public MqttAsyncClient connect(MqttCallback mqttCallback, MqttClientDto mqttClientDto) throws MqttException, UnknownHostException {
        /**
         * host为主机名，clientId即连接MQTT的客户端ID，一般以唯一标识符表示，
         * MemoryPersistence设置clientId的保存形式，默认为以内存保存
         */
        // 获取clientId：系统服务的ip
        String clientId = SystemUtil.getNowIpPort();
//        String clientId = "GW_{svds}_{vsdv}";
        LOGGER.info("== clientId:{}", clientId);
        MqttAsyncClient newClient = new MqttAsyncClient(mqttClientDto.getUrl(), clientId, new MemoryPersistence());
        // MQTT的连接设置
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(false);
        // 设置连接的用户名
        options.setUserName(mqttClientDto.getUserName());
        // 设置连接的密码
        options.setPassword(mqttClientDto.getPassword().toCharArray());
        //启用自动重新连接
        options.setAutomaticReconnect(true);
        // 设置超时时间 单位为秒
        options.setConnectionTimeout(10);
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
        options.setKeepAliveInterval(60);
        // 设置监听，回调
        newClient.setCallback(mqttCallback);
        /* setWill方法，如果项目中需要知道客户端是否掉线可以调用该方法。设置最终端口的通知消息
           options.setWill(topic, "close".getBytes(), 2, true);
        */
        // 设置连接集群
        if (mqttClientDto.getHosts()!=null) {
            String[] hosts = mqttClientDto.getHosts().toArray(new String[0]);
            options.setServerURIs(hosts);
        }
        options.setMaxInflight(100000);

        newClient.connect(options);

        return newClient;
    }


}
