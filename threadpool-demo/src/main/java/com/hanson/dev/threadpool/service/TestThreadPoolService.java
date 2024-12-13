package com.hanson.dev.threadpool.service;

import com.hanson.dev.threadpool.entity.dto.SyncMapTOCopyMapDTO;

import java.util.HashMap;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestThreadPoolService
 * @date 2024/12/11 14:29
 **/
public interface TestThreadPoolService {
    SyncMapTOCopyMapDTO syncMapTOCopyMap(List<String> map, List<String> copyMap);

    SyncMapTOCopyMapDTO method2(List<String> map, List<String> copyMap);
}