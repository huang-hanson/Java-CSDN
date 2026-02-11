package com.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.entity.*;
import com.mall.mapper.OrderItemMapper;
import com.mall.mapper.OrderMapper;
import com.mall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderItemMapper orderItemMapper;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private ProductService productService;
    
    @Override
    public Order getById(Long id) {
        Order order = orderMapper.selectById(id);
        if (order != null) {
            loadOrderItems(order);
        }
        return order;
    }
    
    @Override
    public Order getByOrderNo(String orderNo) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getOrderNo, orderNo);
        Order order = orderMapper.selectOne(wrapper);
        if (order != null) {
            loadOrderItems(order);
        }
        return order;
    }
    
    @Override
    public List<Order> getByUserId(Long userId) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Order::getUserId, userId)
               .orderByDesc(Order::getCreateTime);
        List<Order> orders = orderMapper.selectList(wrapper);
        for (Order order : orders) {
            loadOrderItems(order);
        }
        return orders;
    }
    
    @Override
    @Transactional
    public boolean createOrder(Order order) {
        String orderNo = generateOrderNo();
        order.setOrderNo(orderNo);
        order.setStatus(0);
        
        List<Cart> carts = cartService.getByUserId(order.getUserId());
        if (carts == null || carts.isEmpty()) {
            return false;
        }
        
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Cart cart : carts) {
            if (cart.getChecked() == 1) {
                OrderItem item = new OrderItem();
                item.setOrderId(order.getId());
                item.setProductId(cart.getProductId());
                Product product = cart.getProduct();
                item.setProductName(product.getName());
                item.setProductImage(product.getImage());
                item.setPrice(product.getPrice());
                item.setQuantity(cart.getQuantity());
                item.setTotalPrice(product.getPrice().multiply(new BigDecimal(cart.getQuantity())));
                totalAmount = totalAmount.add(item.getTotalPrice());
                order.getOrderItems().add(item);
            }
        }
        
        order.setTotalAmount(totalAmount);
        order.setPaymentAmount(totalAmount);
        
        int result = orderMapper.insert(order);
        if (result > 0) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrderId(order.getId());
                orderItemMapper.insert(item);
            }
            cartService.deleteByUserId(order.getUserId());
            return true;
        }
        return false;
    }
    
    @Override
    public boolean updateOrder(Order order) {
        return orderMapper.updateById(order) > 0;
    }
    
    @Override
    public boolean payOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order != null && order.getStatus() == 0) {
            order.setStatus(1);
            order.setPaymentTime(new Date());
            return orderMapper.updateById(order) > 0;
        }
        return false;
    }
    
    @Override
    public boolean deliverOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order != null && order.getStatus() == 1) {
            order.setStatus(2);
            order.setDeliveryTime(new Date());
            return orderMapper.updateById(order) > 0;
        }
        return false;
    }
    
    @Override
    public boolean cancelOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order != null && order.getStatus() == 0) {
            order.setStatus(4);
            return orderMapper.updateById(order) > 0;
        }
        return false;
    }
    
    private String generateOrderNo() {
        return System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    private void loadOrderItems(Order order) {
        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderItem::getOrderId, order.getId());
        order.setOrderItems(orderItemMapper.selectList(wrapper));
    }
}