package com.blaser.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //开启Feign服务
public class ConsumerFeignOrder {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignOrder.class, args);
    }
}
