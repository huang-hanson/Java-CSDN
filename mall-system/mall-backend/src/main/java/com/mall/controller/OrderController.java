package com.mall.controller;

import com.mall.common.Result;
import com.mall.entity.Order;
import com.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("/list/{userId}")
    public Result<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.getByUserId(userId);
        return Result.success(orders);
    }
    
    @GetMapping("/{id}")
    public Result<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        if (order != null) {
            return Result.success(order);
        }
        return Result.error("Order not found");
    }
    
    @GetMapping("/no/{orderNo}")
    public Result<Order> getOrderByOrderNo(@PathVariable String orderNo) {
        Order order = orderService.getByOrderNo(orderNo);
        if (order != null) {
            return Result.success(order);
        }
        return Result.error("Order not found");
    }
    
    @PostMapping("/create")
    public Result<String> createOrder(@RequestBody Order order) {
        boolean success = orderService.createOrder(order);
        if (success) {
            return Result.success("Order created successfully");
        }
        return Result.error("Failed to create order");
    }
    
    @PutMapping("/pay/{id}")
    public Result<String> payOrder(@PathVariable Long id) {
        boolean success = orderService.payOrder(id);
        if (success) {
            return Result.success("Payment successful");
        }
        return Result.error("Failed to pay order");
    }
    
    @PutMapping("/deliver/{id}")
    public Result<String> deliverOrder(@PathVariable Long id) {
        boolean success = orderService.deliverOrder(id);
        if (success) {
            return Result.success("Order delivered successfully");
        }
        return Result.error("Failed to deliver order");
    }
    
    @PutMapping("/cancel/{id}")
    public Result<String> cancelOrder(@PathVariable Long id) {
        boolean success = orderService.cancelOrder(id);
        if (success) {
            return Result.success("Order cancelled successfully");
        }
        return Result.error("Failed to cancel order");
    }
}