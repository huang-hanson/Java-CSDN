package com.hanson.eventlogging.entity.request;

import lombok.Data;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PaymentRequest
 * @Description TODO
 * @date 2024/12/9 14:33
 **/
@Data
public class PaymentRequest {
    private String orderId;
    private String amount;
    private String currency;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentTime;
}