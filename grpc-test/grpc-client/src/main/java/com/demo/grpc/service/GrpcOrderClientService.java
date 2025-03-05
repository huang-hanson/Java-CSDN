package com.demo.grpc.service;

import com.demo.grpc.entity.OrderVO;
import com.demo.grpc.order.Buyer;
import com.demo.grpc.order.Order;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcOrderClientService
 * @date 2025/3/5 21:11
 **/
@Slf4j
@Service
public class GrpcOrderClientService {

    @GrpcClient("grpc-server")
    private com.demo.grpc.order.OrderQueryGrpc.OrderQueryBlockingStub orderQueryBlockingStub;

    public List<OrderVO> listOrders(final String name) {
        // gRPC的请求参数
        Buyer buyer = Buyer.newBuilder().setBuyerId(101).build();

        // gRPC的响应
        Iterator<Order> orderIterator;

        // 当前方法的返回值
        List<OrderVO> orders = new ArrayList<>();

        // 通过stub发起远程gRPC请求
        try {
            orderIterator = orderQueryBlockingStub.listOrders(buyer);
        } catch (final StatusRuntimeException e) {
            log.error("error grpc invoke", e);
            return new ArrayList<>();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        log.info("start put order to list");
        while (orderIterator.hasNext()) {
            Order order = orderIterator.next();

            orders.add(new OrderVO(order.getOrderId(),
                    order.getProductId(),
                    // 使用DateTimeFormatter将时间戳转为字符串
                    dtf.format(LocalDateTime.ofEpochSecond(order.getOrderTime(), 0, ZoneOffset.of("+8"))),
                    order.getBuyerRemark()));
            log.info("");
        }

        log.info("end put order to list");

        return orders;
    }
}