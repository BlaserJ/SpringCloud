package com.blaser.springcloud.service.fallback;

import com.blaser.springcloud.service.FeignService;
import org.springframework.stereotype.Component;

//此类实现了FeignService接口，从而可以通过在接口上的注解配置，为该接口所调用的所有服务方法添加服务降级
@Component
public class FeignServiceFallBack implements FeignService {
    @Override
    public String normal(Integer id) {
        return "欸，服务端好像没反应~ :(";
    }

    @Override
    public String timeout(Integer id) {
        return "欸，服务端好像没反应~ :(";
    }
}
