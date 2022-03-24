package com.blaser.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix  //在客户端（消费者）使用EnableHystrix注解启动服务降级功能
public class FeignHystrixOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixOrder80.class, args);
    }
}
