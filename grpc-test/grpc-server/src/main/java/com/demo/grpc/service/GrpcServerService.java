package com.demo.grpc.service;

import com.demo.grpc.helloworld.HelloRequest;
import com.demo.grpc.helloworld.HelloResponse;
import com.demo.grpc.helloworld.HelloWorldServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Date;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcServerService
 * @Description grpc服务类
 * @date 2025/3/5 16:07
 **/
@GrpcService
public class GrpcServerService extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        HelloResponse response = HelloResponse.newBuilder().setMessage("Hello " + request.getName() + ", " + new Date()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}