package com.blaser.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("PROVIDER-PAYMENT-HYSTRIX-SERVICE")
public interface FeignService {

    @RequestMapping("/pay/hystrix/normal/{id}")
    String normal(@PathVariable("id") Integer id);

    @RequestMapping("/pay/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);
}
