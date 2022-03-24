package com.blaser.springcloud.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: Blaser
 * @create: 2022-03-22 09:45
 **/

@Configuration
public class BeanConfig {

    @Bean
    //@LoadBalanced //轮询负载均衡机制
    public RestTemplate getInstance(){
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return builder.build();
    }
}
