package springdemo.service.mqtt;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @ProjectName: mqttpush
 * @Package: com.example.demo.service
 * @ClassName: MqttTestService
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2020/12/16 13:46
 * @Version: 1.0
 */
public class MqttTestService {


    public static void main(String[] args) throws Exception {
// Paho MQTT客户端。
        MqttClient sampleClient = new MqttClient("tcp://10.19.151.167:1883", "GW_WR08PVQL_SDSADFF", new MemoryPersistence());

//            MqttClient clusterClient = new MqttClient("tcp://10.19.151.218:1883", InetAddress.getLocalHost().getHostAddress(), new MemoryPersistence());

// Paho MQTT连接参数。
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setKeepAliveInterval(180);
        connOpts.setUserName("admin");
        connOpts.setPassword("admin".toCharArray());
//        sampleClient.connect(connOpts);
//            clusterClient.connect(connOpts);
//            sampleClient.subscribe("/#", (s, mqttMessage) -> {
//
//                System.out.println(mqttMessage.toString());
//                    });
//                String DATA220 = "";

                String content1 = "{\n" +
                        "    \"product\": \"EIQFFIXX\",\n" +
                        "    \"data\": {\n" +
                        "        \"params\": {\n" +
                        "            \"pressure\": \"666.00\"\n" +
                        "        },\n" +
                        "        \"cmd\": \"prop\"\n" +
                        "    },\n" +
                        "    \"action\": \"event\",\n" +
                        "    \"device\": \"S0EP5YCH_EJ3NPJ59\",\n" +
                        "    \"gateway\": \"AQRKAGDL\"\n" +
                        "}";

                String content2 = "[{\"physicalDevice\":\"YWLstraightDevice1\",\"protocolGateway\":\"straightDevice\",\"data\":{\"light\":1,\"power\":1}}]";
//                MqttMessage message1 = new MqttMessage(DATA220.getBytes());
//                message1.setQos(0);
                MqttMessage message = new MqttMessage(content1.getBytes());
                message.setQos(0);

                String.valueOf(System.currentTimeMillis());

                content1.getBytes();
        System.out.println(content1.getBytes().length);

//        while (true) {
//
//
//
//
//
//            System.out.println("开始发送");
//            System.out.println(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
//
//            Thread.sleep(5000);
//            System.out.println(System.currentTimeMillis());
//            try {
//                for (int i = 0; i < 1; i++) {
//
//                    sampleClient.publish("/Qin_gateway000/werwe/werwe", message);
//    //                sampleClient.publish("/straightDevice/YWLstraightDevice1/telemetry/report", message1);
////                    sampleClient.publish("/gateway001/telemetry/report", message1);
//
////                    clusterClient.publish("/ffff/fff",message);
//
//                }
//            } catch (MqttException e) {
//                System.out.println(e);
//            }
//            System.out.println(System.currentTimeMillis());
//
//        }


/**
 * 应用网关测试
 * 1.10000网关子设备同时发送，10个节点同时发送
 * 2.直连设备10000个同时发送，
 */

    }

            }