package com.blaser.springcloud.controller;

import com.blaser.springcloud.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/FHOrder")
//@DefaultProperties(defaultFallback = "handler")
public class FHController {

    @Resource
    private FeignService service;

    @RequestMapping("/hystrix/normal/{id}")
    public String normal(@PathVariable Integer id){
        return service.normal(id);
    }

    @RequestMapping("/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    //@HystrixCommand
    public String timeout(@PathVariable Integer id){
        return service.timeout(id);
    }


    /*//由方法指定的服务降级方法
    public String timeoutHandler(@PathVariable Integer id){
        return "微服务 响应超时~  id: " + id + "\t______BAD";
    }*/

    /*//由类上的默认属性注解所指定的通用服务降级方法
    public String handler(){
        return "微服务响应超时，可能是机房着火了，请稍后再试~";
    }*/

}
