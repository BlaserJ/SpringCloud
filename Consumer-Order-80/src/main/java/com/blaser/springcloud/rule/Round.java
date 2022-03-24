package com.blaser.springcloud.rule;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface Round {
    //选取服务接口
    ServiceInstance choose(List<ServiceInstance> list);

    //算法辅助函数
    int incrementAndGet(AtomicInteger count);
}
