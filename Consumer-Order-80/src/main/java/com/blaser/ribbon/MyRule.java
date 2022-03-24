package com.blaser.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class MyRule{
    //@Bean
    public IRule Rule(){
        //使用随机算法（默认轮询算法）
        return new RandomRule();
    }

}
