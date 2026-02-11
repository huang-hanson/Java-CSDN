package com.mall.service;

import com.mall.entity.Order;
import java.util.List;

public interface OrderService {
    Order getById(Long id);
    Order getByOrderNo(String orderNo);
    List<Order> getByUserId(Long userId);
    boolean createOrder(Order order);
    boolean updateOrder(Order order);
    boolean payOrder(Long id);
    boolean deliverOrder(Long id);
    boolean cancelOrder(Long id);
}