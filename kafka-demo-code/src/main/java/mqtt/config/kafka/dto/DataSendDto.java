package mqtt.config.kafka.dto;


import cn.hutool.json.JSONObject;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.domain.mqtt.dto.common
 * @ClassName: DataSendDto
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/1/21 13:50
 * @Version: 1.0
 */
public class DataSendDto {

    private String content;

    private String topic;

    private String channelType;

    private JSONObject propertyJson;



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public JSONObject getPropertyJson() {
        return propertyJson;
    }

    public void setPropertyJson(JSONObject propertyJson) {
        this.propertyJson = propertyJson;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

}
