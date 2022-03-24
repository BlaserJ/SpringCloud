package com.blaser.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 微服务提供者-支付
 * @author: Blaser
 * @create: 2022-03-21 17:38
 **/

@EnableDiscoveryClient //启动服务发现功能
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.blaser.springcloud.mapper")
public class ProviderPayment8001 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPayment8001.class, args);
    }
}
