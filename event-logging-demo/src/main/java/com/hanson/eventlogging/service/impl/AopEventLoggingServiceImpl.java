package com.hanson.eventlogging.service.impl;

import com.hanson.eventlogging.service.AopEventLoggingService;
import com.hanson.eventlogging.util.TrackLogUtils;
import org.springframework.stereotype.Service;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AopEventLoggingServiceImpl
 * @Description TODO
 * @date 2024/7/30 11:17
 **/
@Service
public class AopEventLoggingServiceImpl implements AopEventLoggingService {

    @Override
    public void test() {
        TrackLogUtils.traceLog("Aop event logging");
    }
}