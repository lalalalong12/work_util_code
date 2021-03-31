package com.ywltest.springdemo.config.mqtt.config;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ywltest.springdemo.domain.dto.MqttClientDto;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
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
public class EnnMqttGatewayClient {
//    @Value("${mqtt-gateway.url}")
//    private String gatewayurl;
//
////    @Value("${mqtt-gateway.hosts}")
////    private String[] gatewayhosts;
//
//    @Value("${mqtt-gateway.enabled}")
//    private boolean gatewayenabled;
//
//    @Value("${mqtt-gateway.timeout}")
//    private int gatewaytimeout;
//
//    @Value("${mqtt-gateway.keepalive}")
//    private int gatewaykeepalive;
//
//    @Value("${mqtt-gateway.userName}")
//    private String gatewayuserName;
//
//    @Value("${mqtt-gateway.password}")
//    private String gatewaypassword;

    /**
     * 客户端-向下连接通信网关
     */
    private static MqttAsyncClient GatewayCLIENT;


    private synchronized static void setMqttClient(MqttAsyncClient mqttClient) {
        EnnMqttGatewayClient.GatewayCLIENT = mqttClient;
    }


    public MqttAsyncClient getMqttClient() {
        return EnnMqttGatewayClient.GatewayCLIENT;
    }



    @Autowired
    private MqttClientTool mqttClientTool;

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EnnMqttGatewayClient.class);
    /**
     * 初始化mqtt
     *
     * @param
     * @return void
     * @method init
     */
    public void init() {
        try {
            // 订阅消息
            connect();
            List<Integer> integers = new ArrayList<Integer>();
            int[] qos;
            String[] topics = mqttClientTool.getTopics();
            if (topics != null) {
                for (String topic : topics) {
                    int i = 2;
                    integers.add(i);
                }
                qos = integers.stream().mapToInt(Integer::valueOf).toArray();
                Thread.sleep(1000);
                GatewayCLIENT.subscribe(topics, qos);
            }

        } catch (Exception e) {
            LOG.error("== mqtt客户端初始化异常：{}", e);
        }
    }

    public Boolean subscribe( String[] topics){
        List<Integer> integers = new ArrayList<Integer>();
        int[] qos;
        for (String topic : topics) {
            int i = 2;
            integers.add(i);
        }
        qos = integers.stream().mapToInt(Integer::valueOf).toArray();
        try {
            GatewayCLIENT.subscribe(topics, qos);
        } catch (Exception e) {
            LOG.error("== mqtt客户端topic订阅失败：{}", e);
            return false;
        }
        return true;
    }

    public Boolean unSubscribe( String[] topics){

        try {
            GatewayCLIENT.unsubscribe(topics);
        } catch (Exception e) {
            LOG.error("== mqtt客户端topic取消订阅失败：{}", e);
            return false;
        }
        return true;
    }

    /**
     * 连接客户端
     *
     * @param
     * @return void
     * @method connect
     */
    public Boolean connect() throws MqttException, UnknownHostException {

//        MqttCallback gatewayCallback = new EnnMqttGatewayClient.PushCallback();
//        MqttClientDto gatewayClientDto = new MqttClientDto();
//        gatewayClientDto.setEnabled(gatewayenabled);
//        gatewayClientDto.setKeepalive(gatewaykeepalive);
//        gatewayClientDto.setTimeout(gatewaytimeout);
//        gatewayClientDto.setUrl(gatewayurl);
//        gatewayClientDto.setUserName(gatewayuserName);
//        gatewayClientDto.setPassword(gatewaypassword);
//        MqttAsyncClient connect = mqttClientTool.connect(gatewayCallback,gatewayClientDto);
//        setMqttClient(connect);

        return true;
    }

    /**
     * 发布消息
     *
     * @param topic
     * @param qos
     * @param content
     * @return void
     * @method publish
     */
    public boolean publish(String topic, int qos, String content) {
        try {
            MqttMessage message = new MqttMessage(content.getBytes("UTF-8"));
            message.setQos(qos);
            GatewayCLIENT.publish(topic, message);
            LOG.debug("[MQTT发送消息TO网关侧-Topic发送成功:{}]", topic);
            LOG.debug("[MQTT发送消息TO网关侧-content发送成功:{}]", content);
            return true;
        } catch (Exception e) {
            LOG.error("\n== mqtt发布消息系统异常,cause:[{}]", e.getMessage());
           return false;
        }
    }

    /**
     * 回调
     */
    class PushCallback implements MqttCallbackExtended {



        /**
         * 连接完成后调用
         */
        @Override
        public void connectComplete(boolean reconnect, String serverUrl) {
            //如果是重新连接上的
            if(reconnect) {
                LOG.info("==> GatewayCLIENT重新连接成功!");
            }
        }


        @Override
        public void connectionLost(Throwable cause) {
            // 连接丢失后，一般在这里面进行重连
            LOG.warn("==> GatewayCLIENT连接断开，cause：【{}】",cause.getMessage());
            LOG.info("==> GatewayCLIENT自动连接服务器 broker开始");
            try {
                GatewayCLIENT.reconnect();
            } catch (MqttException e) {
                e.printStackTrace();
                LOG.info("==> GatewayCLIENT自动连接服务器异常：【{}】",e.getMessage());
            }
            LOG.info("==> GatewayCLIENT自动连接服务器 broker结束");
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
            LOG.debug("== deliveryComplete---------:{}", token.isComplete());
        }

        /**
         * 监听消息
         *
         * @param topic
         * @param message
         * @return void
         * @method messageArrived
         */
        @Override
        public void messageArrived(String topic, MqttMessage message) {
            // subscribe后得到的消息会执行到这里面
            try {
                String content = new String(message.getPayload(), "utf-8");

                LOG.info("接收到网关侧消息：","\n== 接收消息主题 :{}", topic);
                LOG.debug("== 接收消息Qos :{}\n== 接收消息内容 :{}", message.getQos(),content);
            } catch (Exception e) {
                LOG.warn("== error mqtt(网关侧)监听执行错误：topic[{}],cause[{}]", topic,e);
            }
        }




        /**
         * 直连设备固件升级状态、升级进度上报：resultMessage中文编码处理
         * @param message:
         * @param topic:
         * @return :
         */
        private String firmwareUpgradeMessageEncoding(String topic, MqttMessage message) {
            String result = "";

            String gbkStr = null;
            try {
                gbkStr = new String(message.getPayload(), "GBK");
            } catch (UnsupportedEncodingException e) {
                LOG.debug("直连设备固件升级状态/进度上报消息编码处理失败, message：{}", message.getPayload());
            }
            String utf8Str = new String(message.getPayload(), StandardCharsets.UTF_8);
            if (JSONUtil.isJsonObj(gbkStr)) {
                JSONObject jsonObject1 = JSONUtil.parseObj(gbkStr);
                JSONObject jsonObject2 = JSONUtil.parseObj(utf8Str);
                jsonObject2.replace("resultMessage", jsonObject1.get("resultMessage"));
                result = JSONUtil.toJsonPrettyStr(jsonObject2);
            }
            return result;
        }

    }
}
