package com.blaser.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class HystrixService {

    public String normal(Integer i){
        return "线程池：" + Thread.currentThread().getName() + " 方法：normal(Integer)  id: " + i + "\t______OK";
    }

    @HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String timeout(Integer i){
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " 方法：timeout(Integer)  id: " + i + "\t______OK";
    }

    public String timeoutHandler(Integer i){
        return "线程池：" + Thread.currentThread().getName() + " 响应超时~  id: " + i + "\t______BAD";
    }
}
