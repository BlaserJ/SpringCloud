package com.blaser.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: Blaser
 * @create: 2022-03-22 11:07
 **/

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer1 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer1.class, args);
    }
}
