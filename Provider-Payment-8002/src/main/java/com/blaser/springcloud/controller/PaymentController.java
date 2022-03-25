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

    //通过service层调用mybatisplus接口
    @Resource
    private PaymentService service;

    //获取配置文件中的端口信息
    @Value("${server.port}")
    private String port;

    //配置服务发现
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 根据id从数据库查询
     * @param id 指定id
     * @return 封装了响应信息和查询结果的实体
     */
    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Long id){
        log.info("--------->端口"+port+"收到get请求：" + id);
        Payment payment = service.get(id);
        if(null != payment){
            return new CommonResult<>(200, "success, port: " + port, payment);
        }
        return new CommonResult<>(444, "cannot find the data in database, port: " + port);
    }

    /**
     * 以post方式向数据库写入一条数据
     * @param pay 从前端接收到的json格式的数据库存储对象
     * @return 封装了响应信息和存储结果的实体
     */
    @PostMapping("/add")
    public CommonResult<Payment> addPayment(@RequestBody Payment pay) {
        log.info("--------->端口"+port+"收到add请求：" + pay.getId());
        int num = service.add(pay);
        if(1 == num){
            return new CommonResult<>(200, "success, port: "+port);
        }
        return new CommonResult<>(444, "failed to process this payment, port: " + port);
    }

    /**
     * 测试注册中心提供的服务发现功能工具方法
     */
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

    //测试feign服务调用功能对服务提供者调用超时的工具方法
    @GetMapping("/timeout")
    public String timeOut(){
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return port;
    }

    /**
     * 测试轮询算法是否有效的微服务端口显示的工具方法
     * @return 被调用微服务的端口号
     */
    @GetMapping("/port")
    public String port(){
        log.info("--------->收到port请求");
        return port;
    }
}
