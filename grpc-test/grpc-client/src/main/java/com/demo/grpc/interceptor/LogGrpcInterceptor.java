package com.demo.grpc.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName LogGrpcInterceptor
 * @Description 客户端拦截器
 * @date 2025/3/5 16:31
 **/
@Slf4j
public class LogGrpcInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method,
            CallOptions callOptions,
            Channel channel) {
        log.info(method.getFullMethodName());
        return channel.newCall(method, callOptions);
    }
}