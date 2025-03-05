package com.demo.grpc.service;

import com.demo.grpc.order.AddCartResponse;
import com.demo.grpc.order.CartServiceGrpc;
import com.demo.grpc.order.ProductOrder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcCartClientService
 * @date 2025/3/5 21:49
 **/
@Service
@Slf4j
public class GrpcCartClientService {

    @GrpcClient("grpc-server")
    private CartServiceGrpc.CartServiceStub cartServiceStub;

    public String addToCart(int count) {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        // responseObserver的onNext和onCompleted会在另一个线程中被执行，
        // ExtendResponseObserver继承自StreamObserver
        ExtendResponseObserver<AddCartResponse> responseObserver = new ExtendResponseObserver<AddCartResponse>() {

            String extraStr;

            @Override
            public String getExtra() {
                return extraStr;
            }

            private int code;

            private String message;

            @Override
            public void onNext(AddCartResponse value) {
                log.info("on next");
                code = value.getCode();
                message = value.getMessage();
            }

            @Override
            public void onError(Throwable t) {
                log.error("gRPC request error", t);
                extraStr = "gRPC error, " + t.getMessage();
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                log.info("on complete");
                extraStr = String.format("返回码[%d]，返回信息:%s" , code, message);
                countDownLatch.countDown();
            }
        };

        // 远程调用，此时数据还没有给到服务端
        StreamObserver<ProductOrder> requestObserver = cartServiceStub.addToCart(responseObserver);

        for(int i=0; i<count; i++) {
            // 发送一笔数据到服务端
            requestObserver.onNext(build(101 + i, 1 + i));
        }

        // 客户端告诉服务端：数据已经发完了
        requestObserver.onCompleted();

        try {
            // 开始等待，如果服务端处理完成，那么responseObserver的onCompleted方法会在另一个线程被执行，
            // 那里会执行countDownLatch的countDown方法，一但countDown被执行，下面的await就执行完毕了，
            // await的超时时间设置为2秒
            countDownLatch.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("countDownLatch await error", e);
        }

        log.info("service finish");
        // 服务端返回的内容被放置在requestObserver中，从getExtra方法可以取得
        return responseObserver.getExtra();
    }

    /**
     * 创建ProductOrder对象
     * @param productId
     * @param num
     * @return
     */
    private static ProductOrder build(int productId, int num) {
        return ProductOrder.newBuilder().setProductId(productId).setNumber(num).build();
    }

}