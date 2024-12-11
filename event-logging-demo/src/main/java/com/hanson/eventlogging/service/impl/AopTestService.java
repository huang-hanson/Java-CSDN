package com.hanson.eventlogging.service.impl;

import com.hanson.eventlogging.aop.LogContext;
import com.hanson.eventlogging.entity.request.PaymentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AopTestService
 * @Description TODO
 * @date 2024/12/9 14:32
 **/
@Service
public class AopTestService {
    private static final Logger logger = LoggerFactory.getLogger(AopTestService.class);

    public void processPayment(PaymentRequest request) {
        // 从 LogContext 获取当前接口名称
        String interfaceName = LogContext.getInterfaceName();

        // 假设支付处理逻辑成功
        logger.info("n时间: {} 接口名称: {} 请求参数: {}", System.currentTimeMillis(), interfaceName, request);

        // 清理 ThreadLocal，避免内存泄漏
        LogContext.clear();
    }
}