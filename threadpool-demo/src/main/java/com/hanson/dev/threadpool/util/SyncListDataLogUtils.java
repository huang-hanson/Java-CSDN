package com.hanson.dev.threadpool.util;

import com.hanson.dev.threadpool.entity.dto.SyncMapTOCopyMapDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SyncListDataLogUtils
 * @date 2024/12/11 15:30
 **/
@Slf4j
public class SyncListDataLogUtils {
    public static void syncToMongoOnLastUpdateSubTaskLog(SyncMapTOCopyMapDTO result, int subTaskNumber) {
        log.info("List同步至copyList定时同步子任务{}完成，耗时：{}毫秒。List总数：{}；需要同步的List总数：{}；同步成功的List总数：{}；同步失败或异常的List总数：{}；同步失败或异常的List列表：{}",
                subTaskNumber,
                result.getTimeCost(),
                result.getTotalCount(),
                result.getNeedSyncCount(),
                result.getSuccessCount(),
                result.getFailCount(),
                result.getFailList());
    }

    public static void syncToMongoOnLastUpdateLog(SyncMapTOCopyMapDTO result, long getResumeTimeCost) {
        log.info("List定时同步任务完成，总耗时：{}毫秒。获取List列表耗时：{}毫秒；List总数：{}；需要同步的List总数：{}；同步成功的List总数：{}；同步失败或异常的List总数：{}；同步失败或异常的List id列表：{}；",
                result.getTimeCost(),
                getResumeTimeCost,
                result.getTotalCount(),
                result.getNeedSyncCount(),
                result.getSuccessCount(),
                result.getFailCount(),
                result.getFailList());
    }
}