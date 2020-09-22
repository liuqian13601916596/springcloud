package com.ljs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*服务提供者的启动类*/
@SpringBootApplication()
/*@EnableEurekaClient*/
@EnableDiscoveryClient //*加入客户端*/  /*创建两个服务提供者测试*/
public class providerApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(providerApplication2.class,args);
    }
}
