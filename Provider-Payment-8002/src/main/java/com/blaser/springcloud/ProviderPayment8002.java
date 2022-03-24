package com.blaser.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: Blaser
 * @create: 2022-03-22 13:50
 **/

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.blaser.springcloud.mapper")
public class ProviderPayment8002 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPayment8002.class, args);
    }
}
