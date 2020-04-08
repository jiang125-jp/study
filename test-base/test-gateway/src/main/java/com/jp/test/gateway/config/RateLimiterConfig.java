package com.jp.test.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 路由限流配置
 */
@Configuration
public class RateLimiterConfig {

    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        //ip限流
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
        //接口地址限流
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
        //用户id限流
//        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }
}
