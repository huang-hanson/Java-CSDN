package com.hanson.eventlogging.demo;

import com.hanson.eventlogging.util.TrackLogUtils;

/**
 * @ClassName EventLoggingExample
 * @Description TODO
 * @author hanson.huang
 * @date 2024/7/30 11:11
 * @version V1.0
 **/
public class EventLoggingExample {

    public static void main(String[] args) {
        recordProductClick("123", "456");
    }

    /**
     * 1. 基础日志记录
     * 在用户点击商品时记录
     * @param productId 商品id
     * @param userId 用户id
     */
    private static void recordProductClick(String productId, String userId){
        String logMessage = String.format("user %s clicked on product %s", userId, productId);
        TrackLogUtils.traceLog(logMessage);
    }
}