package com.hanson.eventlogging.controller;

import com.hanson.eventlogging.service.AopEventLoggingService;
import com.hanson.eventlogging.util.TrackLogUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName EventLoggingController
 * @Description TODO
 * @date 2024/7/30 10:23
 **/
@RestController
public class EventLoggingController {

    @Resource
    private AopEventLoggingService aopEventLoggingService;

    @GetMapping("/test")
    public void test() {
        recordProductClick("123","1");
    }

    @GetMapping("/aop")
    public void aop() {
        aopEventLoggingService.test();
    }

    /**
     * 在用户点击商品时记录
     * @param productId 商品id
     * @param userId 用户id
     */
    private static void recordProductClick(String productId, String userId){
        String logMessage = String.format("user %s clicked on product %s", userId, productId);
        TrackLogUtils.traceLog(logMessage);
    }
}