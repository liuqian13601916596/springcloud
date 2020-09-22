package com.ljs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/*服务提供者的启动类*/
@SpringBootApplication()
/*@EnableEurekaClient*/
@EnableDiscoveryClient //*加入客户端*/

public class ProviderApplication {
    static Logger logger = LoggerFactory.getLogger(ProviderApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
        logger.info("服务提供者启动");
    }
}
