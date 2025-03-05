package com.demo.grpc.service;

import com.demo.grpc.order.DeductResponse;
import com.demo.grpc.order.ProductOrder;
import com.demo.grpc.order.StockServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcStockServerService
 * @date 2025/3/5 22:11
 **/
@GrpcService
@Slf4j
public class GrpcStockServerService extends StockServiceGrpc.StockServiceImplBase {

    @Override
    public StreamObserver<ProductOrder> batchDeduct(StreamObserver<DeductResponse> responseObserver) {
        // 返回匿名类，给上层框架使用
        return new StreamObserver<ProductOrder>() {

            private int totalCount = 0;

            @Override
            public void onNext(ProductOrder value) {
                log.info("正在处理商品[{}]，数量为[{}]",
                        value.getProductId(),
                        value.getNumber());

                // 增加总量
                totalCount += value.getNumber();

                int code;
                String message;

                // 假设单数的都有库存不足的问题
                if (0 == value.getNumber() % 2) {
                    code = 10000;
                    message = String.format("商品[%d]扣减库存数[%d]成功", value.getProductId(), value.getNumber());
                } else {
                    code = 10001;
                    message = String.format("商品[%d]扣减库存数[%d]失败", value.getProductId(), value.getNumber());
                }

                responseObserver.onNext(DeductResponse.newBuilder()
                        .setCode(code)
                        .setMessage(message)
                        .build());
            }

            @Override
            public void onError(Throwable t) {
                log.error("批量减扣库存异常", t);
            }

            @Override
            public void onCompleted() {
                log.info("批量减扣库存完成，共计[{}]件商品", totalCount);
                responseObserver.onCompleted();
            }
        };
    }
}