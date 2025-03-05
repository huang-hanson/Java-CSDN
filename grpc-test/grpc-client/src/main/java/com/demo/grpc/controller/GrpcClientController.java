package com.demo.grpc.controller;


import com.demo.grpc.entity.OrderVO;
import com.demo.grpc.service.GrpcCartClientService;
import com.demo.grpc.service.GrpcClientService;
import com.demo.grpc.service.GrpcOrderClientService;
import com.demo.grpc.service.GrpcStockClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName GrpcClientController
 * @Description grpc测试控制类
 * @date 2025/3/5 16:52
 **/
@RestController
public class GrpcClientController {

    @Resource
    private GrpcClientService grpcClientService;

    @Resource
    private GrpcOrderClientService grpcOrderClientService;

    @Resource
    private GrpcCartClientService grpcCartClientService;

    @Resource
    private GrpcStockClientService grpcStockClientService;

    @GetMapping("/test1")
    public String printMessage(@RequestParam(defaultValue = "Hanson") String name) {
        return grpcClientService.sendMessage(name);
    }

    @GetMapping("/test2")
    public List<OrderVO> printMessage2(@RequestParam(defaultValue = "Hanson") String name) {
        return grpcOrderClientService.listOrders(name);
    }

    @GetMapping("/test3")
    public String printMessage3(@RequestParam(defaultValue = "1") int count) {
        return grpcCartClientService.addToCart(count);
    }

    @GetMapping("/test4")
    public String printMessage4(@RequestParam(defaultValue = "1") int count) {
        return grpcStockClientService.batchDeduct(count);
    }
}