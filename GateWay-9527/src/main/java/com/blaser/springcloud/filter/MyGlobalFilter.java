package com.blaser.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

//在此实现两个接口，以定义自己的全局过滤器
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {

    /**
     * 过滤方法
     * @param exchange 服务网络交换器，可以从此对象中获取request response 和http请求中附带的实用属性
     * @param chain gateway定义的一系列过滤器链，类似于web中的过滤器，若过滤通过，则将exchange对象传递给此链
     * @return 单一信号发射器（订阅发布模式）
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("-------->进入了" + MyGlobalFilter.class.getName() + ", 时间: " + new Date());
        String name = exchange.getRequest()
                .getQueryParams()
                .getFirst("name"); //根据exchange对象，首先获取ServerHttpRequest请求实例，然后获取其请求参数map，最后根据key获取指定属性

        if(null == name){
            //在此进行过滤失败的演示
            log.info("--------->name属性为空，将其退回");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE); //设置响应的状态码
            return exchange.getResponse().setComplete();  //将此此响应返回
        }

        //在此进行过滤成功的演示
        return chain.filter(exchange);
    }

    /**
     * 此函数设置加载本过滤器的优先级
     * @return 优先级，越小越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
