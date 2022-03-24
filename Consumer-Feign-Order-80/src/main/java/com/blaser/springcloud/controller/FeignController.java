package com.blaser.springcloud.controller;

import com.blaser.springcloud.bo.CommonResult;
import com.blaser.springcloud.entity.Payment;
import com.blaser.springcloud.service.FeignOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignController {

    @Resource
    private FeignOrderService service;


    @GetMapping("/get/{id}")
    public CommonResult<Payment> getById(@PathVariable Long id){
        log.info("--------->收到get请求, id: " + id);
        return service.getPayment(id);
    }

    @PostMapping("/add")
    public CommonResult<Payment> add(Payment pay){
        log.info("--------->收到add请求, id: " + pay.getId() + " | " + "serial: " + pay.getSerial());
        return service.addPayment(pay);
    }

    @GetMapping("/discover")
    public void discover(){
        service.discover();
    }

    @GetMapping("/timeout")
    public String timeOut(){
        return service.timeOut();
    }

}
