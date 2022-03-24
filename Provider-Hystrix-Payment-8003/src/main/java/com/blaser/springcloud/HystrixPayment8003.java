package com.blaser.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker  //此注解可以激活hystrix的服务降级
public class HystrixPayment8003 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixPayment8003.class, args);
    }
}
