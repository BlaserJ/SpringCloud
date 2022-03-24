package com.blaser.springcloud;

import com.blaser.ribbon.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author: Blaser
 * @create: 2022-03-22 09:39
 **/

@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "PROVIDER-PAYMENT-SERVICE", configuration = MyRule.class)
public class ConsumerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80.class, args);
    }
}
