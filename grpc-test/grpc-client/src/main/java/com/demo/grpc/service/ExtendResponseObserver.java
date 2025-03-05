package com.demo.grpc.service;

import io.grpc.stub.StreamObserver;

/**
 * @author hanson.huang
 * @version V1.0
 * @InterfaceName ExtendResponseObserver
 * @date 2025/3/5 21:50
 **/
public interface ExtendResponseObserver<T> extends StreamObserver<T> {
    String getExtra();
}
