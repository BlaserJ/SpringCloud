package com.blaser.springcloud.rule.impl;

import com.blaser.springcloud.rule.Round;
import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRule implements Round {

    //生成一个原子Integer对象统计调用次数(保证线程安全)
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public ServiceInstance choose(List<ServiceInstance> list) {
        //获取服务数组长度
        int length = list.size();

        //退化判断
        if(0 == length){
            return null;
        }

        //调用工具函数增加一次调用
        int new_count = incrementAndGet(count);

        //计算本次调用的下标
        int index = new_count % length;

        //返回该下标对应的服务
        return list.get(index);
    }

    @Override
    public int incrementAndGet(AtomicInteger count) {
        //使用乐观锁
        int current_count;
        int next;
        for(;;){
            //获取目前调用次数
            current_count = count.get();
            //次数+1
            next = (current_count >= Integer.MAX_VALUE ? 0 : current_count + 1);
            //自旋
            if(count.compareAndSet(current_count, next)){ //参数一代表期望原子整型count当前的值，参数二代表若如期望所示，则赋予count的新值
                return next;
            }
        }
    }
}
