package com.hanson.freemarker.model;

import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @date 2024/10/24 13:28
 **/
@Data
public class Order {
    private Long orderId;
    private String orderName;
    private Price price;
}