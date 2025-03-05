package com.demo.grpc.service;

import java.util.ArrayList;
import java.util.List;
import com.demo.grpc.order.Buyer;
import com.demo.grpc.order.Order;
import com.demo.grpc.order.OrderQueryGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcOrderServerService
 * @Description 订单grpc
 * @date 2025/3/5 20:41
 **/
@GrpcService
public class GrpcOrderServerService extends OrderQueryGrpc.OrderQueryImplBase {

    /**
     * 造一批数据
     * @return
     */
    private static List<Order> mockOrders(){
        List<Order> list = new ArrayList<>();
        Order.Builder builder = Order.newBuilder();

        for (int i = 0; i < 10; i++) {
            list.add(builder
                    .setOrderId(i)
                    .setProductId(100+i)
                    .setOrderTime(System.currentTimeMillis()/1000)
                    .setBuyerRemark(("Hanson-" + i))
                    .build());
        }

        return list;
    }

    @Override
    public void listOrders(Buyer request, StreamObserver<Order> responseObserver) {
        // 持续输出到client
        for (Order order : mockOrders()) {
            responseObserver.onNext(order);
        }
        // 结束输出
        responseObserver.onCompleted();
    }
}