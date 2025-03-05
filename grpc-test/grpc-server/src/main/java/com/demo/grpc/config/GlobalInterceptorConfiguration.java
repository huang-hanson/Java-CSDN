package com.demo.grpc.config;

import com.demo.grpc.interceptor.LogGrpcInterceptor;
import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GlobalInterceptorConfiguration
 * @Description 拦截器配置
 * @date 2025/3/5 16:05
 **/
@Configuration(proxyBeanMethods = false)
public class GlobalInterceptorConfiguration {

    @GrpcGlobalServerInterceptor
    ServerInterceptor logServerInterceptor(){
        return new LogGrpcInterceptor();
    }
}