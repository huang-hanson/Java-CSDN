package com.hanson.dev.threadpool.controller;

import com.hanson.dev.threadpool.entity.dto.SyncMapTOCopyMapDTO;
import com.hanson.dev.threadpool.service.TestThreadPoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestController
 * @date 2024/12/11 15:48
 **/
@RestController
public class TestController {

    @Resource
    private TestThreadPoolService testThreadPoolService;

    @GetMapping("/test")
    public Object test() {
        SyncMapTOCopyMapDTO syncMapTOCopyMapDTO = testThreadPoolService.syncMapTOCopyMap(null, null);
        return syncMapTOCopyMapDTO;
    }
}