package com.hanson.eventlogging.controller;

import com.hanson.eventlogging.aop.Timeout;
import com.hanson.eventlogging.service.AopEventLoggingService;
import com.hanson.eventlogging.util.TrackLogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName EventLoggingController
 * @Description TODO
 * @date 2024/7/30 10:23
 **/
@Slf4j
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

    @GetMapping("/testTimeOut")
    public DeferredResult<String> testTimeOut() {
        DeferredResult<String> deferredResult = new DeferredResult<>(5000L); // 设置超时时间为5秒

        deferredResult.onTimeout(() -> deferredResult.setErrorResult("Request timeout"));

        // 异步处理逻辑
        new Thread(() -> {
            try {
                Thread.sleep(10000); // 模拟长时间任务
                deferredResult.setResult("timeout");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        return deferredResult;
    }

    @Timeout(5000) // 设置超时时间为5秒
    @GetMapping("/testTimeOut/aop")
    public String testTimeOutAop() {
        try {
            Thread.sleep(10000); // 模拟长时间任务
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Task Completed";
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