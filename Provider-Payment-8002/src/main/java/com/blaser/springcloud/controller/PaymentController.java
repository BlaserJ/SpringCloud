package com.blaser.springcloud.controller;

import com.blaser.springcloud.bo.CommonResult;
import com.blaser.springcloud.entity.Payment;
import com.blaser.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Blaser
 * @create: 2022-03-21 18:07
 **/

@RestController
@RequestMapping("/pay")
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService service;

    @Value("${server.port}")
    private String port;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id){
        log.info("--------->端口"+port+"收到get请求：" + id);
        Payment payment = service.get(id);
        if(null != payment){
            return new CommonResult<>(200, "success, port: " + port, payment);
        }
        return new CommonResult<>(444, "cannot find the data in database, port: " + port);
    }

    @PostMapping("/add")
    public CommonResult<Payment> addPayment(@RequestBody Payment pay) {
        log.info("--------->端口"+port+"收到add请求：" + pay.getId());
        int num = service.add(pay);
        if(1 == num){
            return new CommonResult<>(200, "success, port: " + port);
        }
        return new CommonResult<>(444, "failed to process this payment, port: " + port);
    }

    @GetMapping("/discover")
    public void discover(){
        //获取当前Cloud项目提供的具体微服务（包含组件）列表
        List<String> services = discoveryClient.getServices();
        for (String service:
                services) {
            log.info(service);
        }

        //获取指定名称微服务的具体实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVICE");
        for (ServiceInstance instance:
                instances) {
            log.info(instance.getUri().toString());
        }
    }

    @GetMapping("/timeout")
    public String timeOut(){
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return port;
    }

    @GetMapping("/port")
    public String port(){
        log.info("--------->收到port请求");
        return port;
    }
}
