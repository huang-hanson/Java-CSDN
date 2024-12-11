package com.hanson.eventlogging.controller;

import com.hanson.eventlogging.entity.request.PaymentRequest;
import com.hanson.eventlogging.service.impl.AopTestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AopTestController
 * @Description TODO
 * @date 2024/12/9 14:32
 **/
@RestController
public class AopTestController {

    @Resource
    private AopTestService paymentService;

    @PostMapping("/api/v1/payment")
    public String processPayment() {
        PaymentRequest request = new PaymentRequest();
        request.setOrderId("123456789");
        request.setAmount("100.00");
        request.setCurrency("USD");
        request.setPaymentMethod("credit card");
        request.setPaymentStatus("success");
        request.setPaymentTime("2023-10-15 12:30:00");
        paymentService.processPayment(request);
        return "Payment processed successfully!";
    }
}