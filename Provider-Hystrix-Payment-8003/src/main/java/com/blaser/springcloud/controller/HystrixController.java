package com.blaser.springcloud.controller;

import com.blaser.springcloud.service.HystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/pay")
public class HystrixController {

    @Resource
    private HystrixService service;

    @RequestMapping("/hystrix/normal/{id}")
    public String normal(@PathVariable Integer id){
        String result = service.normal(id);
        log.info("--------->result: " + result);
        return result;
    }

    @RequestMapping("/hystrix/timeout/{id}")
    public String timeout(@PathVariable Integer id){
        String result = service.timeout(id);
        log.info("--------->result: " + result);
        return result;
    }
}
