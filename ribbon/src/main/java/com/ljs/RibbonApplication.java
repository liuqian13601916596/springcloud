package com.ljs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RibbonApplication {
    public static void main(String[] args) {

        SpringApplication.run(RibbonApplication.class,args);
    }

 @LoadBalanced //*负载均衡*/
 @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
