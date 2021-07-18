package springdemo.service.mqtt.mqttpw;

/**
 * @ProjectName: work_util_code
 * @Package: com.ywltest.springdemo.service.mqtt.mqttpw
 * @ClassName: Mqtt
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/3/2 10:52
 * @Version: 1.0
 */

import org.eclipse.paho.client.mqttv3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.util.UUID;

public class Mqtt {

    //TODO 将这里的用户名、密码、topic、连接地址替换成自己的
    private static final String MQTT_USERNAME="1234";
    private static final String MQTT_PASSWORD="1234";
    private static final String MQTT_TOPIC="manage-iot-hub/341/#/100/#";
    //非加密连接地址示例：`tcp://www.example.com:1883`,加密连接地址: `ssl://www.example.com:8883`
    private static final String MQTT_ADDRESS="ssl://www.smartmetering.top:8883";

    public static void main(String[] args) throws Exception {
        String clientID = UUID.randomUUID().toString();
        MqttClient mqttClient = new MqttClient(MQTT_ADDRESS, clientID);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setKeepAliveInterval(10);
        options.setUserName(MQTT_USERNAME);
        options.setPassword(MQTT_PASSWORD.toCharArray());
        //设置加密证书逻辑,内网等非加密环境可跳过此步。
        options.setSocketFactory(createSSLSocket());

        mqttClient.connect(options);
        mqttClient.subscribe(MQTT_TOPIC);

        System.out.println("success!");
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println(message.toString());
                //TODO 后续数据操作
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    //设置加密证书逻辑,内网等非加密环境可跳过此步。
    private static SSLSocketFactory createSSLSocket() throws Exception {
        SSLContext context = SSLContext.getInstance("TLSV1.2");
        context.init(null, new TrustManager[]{new CustomX509TrustManager()}, null);
        SSLSocketFactory socketFactory = context.getSocketFactory();
        return socketFactory;
    }

}
