package common.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ProjectName: iot-application-gateway
 * @Package: com.encdata.iot.application.gateway.common.util.common
 * @ClassName: SystemUtil
 * @Author: yangwenlongb
 * @Description: ${description}
 * @Date: 2021/3/19 10:24
 * @Version: 1.0
 */
public class SystemUtil {

    private static final Logger log = LoggerFactory.getLogger(NumberUtils.class);

    public static String getNowIpPort() {
        String clientId = null;
        try {
             clientId = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("当前主机ip获取异常，e：{}",e.getMessage());
        }
        return clientId;
    }

}
