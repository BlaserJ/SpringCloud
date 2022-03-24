package com.blaser.springcloud.service;

import com.blaser.springcloud.bo.CommonResult;
import com.blaser.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component  //此作为服务层，需要被spring容器识别
@FeignClient("PROVIDER-PAYMENT-SERVICE")  //标记此Feign需要映射到的微服务名称
public interface FeignOrderService {

    //在此处如同声明mybatis的mapper一般，声明要调用的微服务函数

    @GetMapping("/pay/get/{id}")
    CommonResult<Payment> getPayment(@PathVariable("id") Long id);

    @PostMapping("/pay/add")
    CommonResult<Payment> addPayment(Payment pay);

    @GetMapping("/pay/discover")
    void discover();

    @GetMapping("/pay/timeout")
    String timeOut();
}
