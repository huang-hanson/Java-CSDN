package com.hanson.eventlogging.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TrackLogUtils
 * @Description 埋点工具类
 * @date 2024/7/30 9:53
 **/
@Slf4j
public class TrackLogUtils {
    public static void traceLog(String msg){
        log.trace(msg);
    }
}