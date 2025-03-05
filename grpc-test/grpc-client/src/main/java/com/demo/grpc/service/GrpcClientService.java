package com.demo.grpc.service;

import com.demo.grpc.helloworld.HelloRequest;
import com.demo.grpc.helloworld.HelloResponse;
import com.demo.grpc.helloworld.HelloWorldServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcClientService
 * @Description grpc服务
 * @date 2025/3/5 16:46
 **/
@Service
public class GrpcClientService {

    @GrpcClient("grpc-server")
    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    public String sendMessage(final String name) {
        try {
            final HelloResponse response = this.helloWorldServiceBlockingStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
}