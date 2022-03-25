package com.blaser.springcloud.service;

import com.blaser.springcloud.service.fallback.FeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//在此处指定映射的微服务名称 和 配置的降级服务类
@Component
@FeignClient(value = "PROVIDER-PAYMENT-HYSTRIX-SERVICE", fallback = FeignServiceFallBack.class)
public interface FeignService {

    @RequestMapping("/pay/hystrix/normal/{id}")
    String normal(@PathVariable("id") Integer id);

    @RequestMapping("/pay/hystrix/timeout/{id}")
    String timeout(@PathVariable("id") Integer id);
}
