package com.ywltest.springdemo.service.coapTest;

import org.eclipse.californium.core.*;
import org.eclipse.californium.elements.exception.ConnectorException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author yangWenlong
 * @date 2020/11/30- ${time}
 */

public class GETClient {

    public static void main(String[] args) throws URISyntaxException, ConnectorException, IOException {
        URI uri = null;
        uri = new URI("localhost:5683/timex1");  //创建一个资源请求hello资源，注意默认端口为5683
        CoapClient client = new CoapClient(uri);
        CoapResponse response = client.get();
        if (response != null) {
            System.out.println(response.getCode());  //打印请求状态码
            System.out.println(response.getOptions());  //选项参数
            System.out.println(response.getResponseText());  //获取内容文本信息
            System.out.println("\nAdvanced\n");    //
            System.out.println(Utils.prettyPrint(response));  //打印格式良好的输出
        }
    }


}
