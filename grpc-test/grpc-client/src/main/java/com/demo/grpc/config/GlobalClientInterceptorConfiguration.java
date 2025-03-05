package com.demo.grpc.config;

import com.demo.grpc.interceptor.LogGrpcInterceptor;
import io.grpc.ClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;


/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GlobalClientInterceptorConfiguration
 * @Description 客户端配置类
 * @date 2025/3/5 16:43
 **/
@Order(Ordered.LOWEST_PRECEDENCE)
@Configuration(proxyBeanMethods = false)
public class GlobalClientInterceptorConfiguration {

    @GrpcGlobalClientInterceptor
    ClientInterceptor logGrpcInterceptor() {
        return new LogGrpcInterceptor();
    }
}