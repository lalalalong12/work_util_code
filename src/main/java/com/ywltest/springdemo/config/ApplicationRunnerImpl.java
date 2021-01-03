package com.ywltest.springdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yangWenlong
 * @date 2020/12/1- ${time}
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationRunnerImpl.class);

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("application start...");

        try {
            log.info("\n----------------------------------------------------------\n\tApplication '{}' is running! Access URLs:\n\tLocal: \t\t{}://localhost:{}\n\tExternal: \t{}://{}:{}\n\tProfile(s): \t{}\n----------------------------------------------------------", "spring.application.name", "protocol", "server.port", "protocol", InetAddress.getLocalHost().getHostAddress(), "server.port", "env.getActiveProfiles()");
        } catch (UnknownHostException var8) {
            var8.printStackTrace();
        }
    }
}