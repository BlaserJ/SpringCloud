package com.blaser.springcloud.controller;

import com.blaser.springcloud.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/FHOrder")
public class FHController {

    @Resource
    private FeignService service;

    @RequestMapping("/hystrix/normal/{id}")
    public String normal(@PathVariable Integer id){
        return service.normal(id);
    }

    @RequestMapping("/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout(@PathVariable Integer id){
        return service.timeout(id);
    }


    public String timeoutHandler(@PathVariable Integer id){
        return "微服务 响应超时~  id: " + id + "\t______BAD";
    }

}
