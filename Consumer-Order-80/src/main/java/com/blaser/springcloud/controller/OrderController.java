package com.blaser.springcloud.controller;

import com.blaser.springcloud.bo.CommonResult;
import com.blaser.springcloud.entity.Payment;
import com.blaser.springcloud.rule.impl.RoundRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author: Blaser
 * @create: 2022-03-22 09:42
 **/

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    //private static final String PAYMENT_URL = "http://localhost:8001";
    private static final String PAYMENT_URL = "http://PROVIDER-PAYMENT-SERVICE";

    @Resource
    private RestTemplate template;

    @Resource
    private DiscoveryClient discoveryClient;

    private RoundRule rule = new RoundRule();


    @PostMapping("/create")
    public CommonResult<Payment> create(Payment pay){
        log.info("--------->收到create请求：id: " + pay.getId() + " | serial: " + pay.getSerial());
        return template.postForObject(PAYMENT_URL+ "/pay/add", pay, CommonResult.class);
    }

    @GetMapping("/get/{id}")
    public CommonResult<Payment> get(@PathVariable Long id){
        log.info("--------->收到get请求：" + id);
        return template.getForObject(PAYMENT_URL+"/pay/get/"+id, CommonResult.class);
    }

    @PostMapping("/createE")
    public CommonResult<Payment> createE(Payment pay){
        log.info("--------->收到postForEntity请求：id: " + pay.getId() + " | serial: " + pay.getSerial());
        //通过postForEntity函数返回的结果是包含响应头、响应体、数据体等的一个ResponseEntity实体
        ResponseEntity<CommonResult> entity = template.postForEntity(PAYMENT_URL + "/pay/add", pay, CommonResult.class);
        //通过提取响应头判断请求结果
        return getPaymentCommonResult(entity);
    }

    @GetMapping("/getE/{id}")
    public CommonResult<Payment> getE(@PathVariable Long id){
        log.info("--------->收到getForEntity请求：id: " + id);
        //获取ResponseEntity实体而非简单的CommonResult实体
        ResponseEntity<CommonResult> entity = template.getForEntity(PAYMENT_URL + "/pay/get/" + id, CommonResult.class);
        //判断状态码
        return getPaymentCommonResult(entity);
    }

    @GetMapping("/port")
    public String port(){
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT-SERVICE");
        ServiceInstance instance = rule.choose(instances);
        URI uri = instance.getUri();
        return template.getForObject(uri+"/pay/port", String.class);
    }


    //抽取的工具函数
    private CommonResult<Payment> getPaymentCommonResult(ResponseEntity<CommonResult> entity) {
        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            CommonResult body = entity.getBody();
            if(null != body){
                return body;
            }else {
                return new CommonResult<>(444, "service failed for " + entity.getStatusCode());
            }
        }
    }
}
