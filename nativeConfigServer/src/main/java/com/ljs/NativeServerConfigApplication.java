package com.ljs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer /*声明配置中心*/
public class NativeServerConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(NativeServerConfigApplication.class,args);
    }
}
