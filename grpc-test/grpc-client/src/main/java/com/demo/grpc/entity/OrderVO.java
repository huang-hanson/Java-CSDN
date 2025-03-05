package com.demo.grpc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName OrderVO
 * @Description 订单
 * @date 2025/3/5 20:49
 **/
@Data
@AllArgsConstructor
public class OrderVO {

    private int orderId;
    private int productId;
    private String orderTime;
    private String buyerRemark;
}