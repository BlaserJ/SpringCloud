package com.blaser.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class HystrixService {

    /**
     * 一般方法，立即返回，不会发生异常
     * @param i 打印输入参数
     * @return 向页面输出一行文字
     */
    public String normal(Integer i){
        return "线程池：" + Thread.currentThread().getName() + " 方法：normal(Integer)  id: " + i + "\t______OK";
    }

    /**
     * 特殊方法，休眠5秒后返回（一般feign调用服务的超时时间为1秒）
     * @param i 打印输入参数
     * @return 向页面输出一行文字
     */
    /*@HystrixCommand(fallbackMethod = "timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })*/
    public String timeout(Integer i){
        //故意设置线程等待5秒
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " 方法：timeout(Integer)  id: " + i + "\t______OK";
    }

    //为服务端配置的降级方法
    /*public String timeoutHandler(Integer i){
        return "线程池：" + Thread.currentThread().getName() + " 响应超时~  id: " + i + "\t______BAD";
    }*/

    @HystrixCommand(fallbackMethod = "CircuitBreakerFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //检测请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //断路后等待的时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //失败率达到指定百分比后断路
    })
    public String CircuitBreaker(Long id){
        //判断是否id为负数，若是，抛出异常
        if(id < 0){
            throw new RuntimeException("-----id is negative!");
        }
        return "线程池：" + Thread.currentThread().getName() + " 方法：CircuitBreaker(Long)  id: " + id + "\t" + IdUtil.simpleUUID();
    }

    //为服务熔断配置的降级服务
    public String CircuitBreakerFallBack(Long id){
        return "您刚刚输入了一个非法值，系统已经死掉了，正在等待复活~";
    }
}
