package com.hanson.dev.threadpool.service.impl;

import com.csdn.dev.common.util.JacksonUtils;
import com.hanson.dev.threadpool.constant.Constant;
import com.hanson.dev.threadpool.entity.dto.SyncMapTOCopyMapDTO;
import com.hanson.dev.threadpool.service.TestThreadPoolService;
import com.hanson.dev.threadpool.util.SyncListDataLogUtils;
import com.hanson.dev.threadpool.util.TaskServiceUtils;
import com.hanson.dev.threadpool.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName TestThreadPoolServiceImpl
 * @date 2024/12/11 14:29
 **/
@Slf4j
@Service
public class TestThreadPoolServiceImpl implements TestThreadPoolService {

    @Resource
    ThreadPoolUtils threadPool;

    @Value("${test.sync.taskexecutor.group.num:2}")
    private int groupNum;

    @Resource(name="testAsyncExecutor")
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static final List<String> LIST = fillListWithRandomData(new ArrayList<>(), 101);

    private static List<String> COPYLIST = new ArrayList<>();

    @Override
    public SyncMapTOCopyMapDTO syncMapTOCopyMap(List<String> list, List<String> copyList) {
        list = LIST;
        Instant startInstant = Instant.now();
        List<Future<SyncMapTOCopyMapDTO>> futureList = new ArrayList<>();
        //子任务切分
        int splitLength = list.size() / Constant.CORE_POOL_SIZE + 1;
        int subTaskCount = 0;
        for (int i = 0; i < list.size(); i += splitLength) {
            List<String> subList = list.subList(i, Math.min(i + splitLength, list.size()));
            final int subTaskNumber = subTaskCount;
            Future<SyncMapTOCopyMapDTO> future = threadPool.getPool().submit(new Callable<SyncMapTOCopyMapDTO>() {
                @Override
                public SyncMapTOCopyMapDTO call() throws Exception {
                    return syncListDataTOCopyList(subList, subTaskNumber);
                }
            });
            futureList.add(future);
            subTaskCount++;
        }

        //子任务执行结果汇总
        SyncMapTOCopyMapDTO syncResult = new SyncMapTOCopyMapDTO();
        syncResult.setTotalCount(list.size());
        syncResult.setNeedSyncCount(0);
        syncResult.setSuccessCount(0);
        syncResult.setFailCount(0);
        syncResult.setFailList(new ArrayList<>());
        for (Future<SyncMapTOCopyMapDTO> future : futureList) {
            try {
                SyncMapTOCopyMapDTO taskResult = future.get();
                if (taskResult == null) {
                    log.error("获取子任务结果异常，子任务结果为null");
                } else {
                    syncResult.setNeedSyncCount(syncResult.getNeedSyncCount() + taskResult.getNeedSyncCount());
                    syncResult.setSuccessCount(syncResult.getSuccessCount() + taskResult.getSuccessCount());
                    syncResult.setFailCount(syncResult.getFailCount() + taskResult.getFailCount());
                    syncResult.getFailList().addAll(taskResult.getFailList());
                }
            } catch (Exception e) {
                log.error("简历同步定时任务，获取子任务结果异常", e);
            }
        }
        Instant endInstant = Instant.now();
        syncResult.setTimeCost(endInstant.toEpochMilli() - startInstant.toEpochMilli());
        SyncListDataLogUtils.syncToMongoOnLastUpdateLog(syncResult, syncResult.getTimeCost());
        return syncResult;
    }

    /**
     * 多线程第二种方案
     */
    @Override
    public SyncMapTOCopyMapDTO method2(List<String> list, List<String> copyMap) {
        list = LIST;
        Instant startInstant = Instant.now();
        List<Future<SyncMapTOCopyMapDTO>> futureList = new ArrayList<>();

        //分n组执行任务
        List<List<String>> subList = TaskServiceUtils.averageAssign(list, groupNum);
        for (int i = 0; i < groupNum; i++) {
            int index = i;
            Future<SyncMapTOCopyMapDTO> intFuture = threadPoolTaskExecutor.submit(() -> syncListDataTOCopyList(subList.get(index), index));
            futureList.add(intFuture);
        }
        //子任务执行结果汇总
        SyncMapTOCopyMapDTO syncResult = new SyncMapTOCopyMapDTO();
        syncResult.setTotalCount(list.size());
        syncResult.setNeedSyncCount(0);
        syncResult.setSuccessCount(0);
        syncResult.setFailCount(0);
        syncResult.setFailList(new ArrayList<>());
        //收集返回结果
        int successTotal = 0;
        for (Future<SyncMapTOCopyMapDTO> intFuture : futureList) {
            try {
                SyncMapTOCopyMapDTO taskResult = TaskServiceUtils.futureGet(intFuture, 24, TimeUnit.HOURS, "同步用户标签信息到企微定时任务, 获取第" + futureList.indexOf(intFuture) + "组结果超时");
                if (taskResult == null) {
                    log.error("获取子任务结果异常，子任务结果为null");
                } else {
                    syncResult.setNeedSyncCount(syncResult.getNeedSyncCount() + taskResult.getNeedSyncCount());
                    syncResult.setSuccessCount(syncResult.getSuccessCount() + taskResult.getSuccessCount());
                    syncResult.setFailCount(syncResult.getFailCount() + taskResult.getFailCount());
                    syncResult.getFailList().addAll(taskResult.getFailList());
                }
                log.info("同步用户标签信息到企微定时任务, 获取第" + futureList.indexOf(intFuture) + "组结果成功，result:" + taskResult);
            }catch (Exception e){
                log.error("同步用户标签信息到企微定时任务, 获取执行结果失败！", e);
            }
        }
        Instant endInstant = Instant.now();
        syncResult.setTimeCost(endInstant.toEpochMilli() - startInstant.toEpochMilli());
        SyncListDataLogUtils.syncToMongoOnLastUpdateLog(syncResult, syncResult.getTimeCost());
        return syncResult;
    }


    private SyncMapTOCopyMapDTO syncListDataTOCopyList(List<String> list, int subTaskNumber) {
        Instant startInstant = Instant.now();
        SyncMapTOCopyMapDTO syncMapTOCopyMapDTO = new SyncMapTOCopyMapDTO();
        syncMapTOCopyMapDTO.setTotalCount(list.size());
        List<String> failList = new ArrayList<>();
        syncMapTOCopyMapDTO.setFailList(failList);
        // 初始化计数器
        int successCount = 0;
        int failCount = 0;
        int needSyncCount = 0;

        // 遍历列表中的每个元素
        for (String value : list) {
            COPYLIST.add(value);
            boolean isSuccess = COPYLIST.contains(value);
            if (isSuccess) {
                successCount++;
                needSyncCount++;
            } else {
                failCount++;
                failList.add(value);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("线程中断", e);
            }
        }
        // 设置统计数据
        syncMapTOCopyMapDTO.setSuccessCount(successCount);
        syncMapTOCopyMapDTO.setFailCount(failCount);
        syncMapTOCopyMapDTO.setNeedSyncCount(list.size());
        syncMapTOCopyMapDTO.setFailList(failList);
        syncMapTOCopyMapDTO.setTimeCost(Instant.now().toEpochMilli() - startInstant.toEpochMilli());
        SyncListDataLogUtils.syncToMongoOnLastUpdateSubTaskLog(syncMapTOCopyMapDTO, subTaskNumber);
        return syncMapTOCopyMapDTO;
    }

    /**
     * 向给定的 ArrayList 中添加指定数量的随机数据
     *
     * @param list  ArrayList<String> 用于存储数据
     * @param count 添加的数据条数
     * @return
     */
    public static ArrayList<String> fillListWithRandomData(ArrayList<String> list, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String value = "value" + random.nextInt(1000);
            list.add(value);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(JacksonUtils.jsonEncode(LIST));
    }
}