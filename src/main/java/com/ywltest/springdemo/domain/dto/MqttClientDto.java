package com.ywltest.springdemo.domain.dto;

import java.util.List;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.config.mqtt
 * @ClassName: MqttClientDto
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2020/11/26 11:00
 * @Version: 1.0
 */

public class MqttClientDto {

    private String url;


    private List<String> hosts;


    private boolean enabled;


    private int timeout;


    private int keepalive;


    private String userName;


    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getKeepalive() {
        return keepalive;
    }

    public void setKeepalive(int keepalive) {
        this.keepalive = keepalive;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHosts() {
        return hosts;
    }

    public void setHosts(List<String> hosts) {
        this.hosts = hosts;
    }
}
