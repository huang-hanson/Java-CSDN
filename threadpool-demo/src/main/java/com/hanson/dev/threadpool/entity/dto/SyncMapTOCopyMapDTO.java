package com.hanson.dev.threadpool.entity.dto;

import lombok.Data;

import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SyncMapTOCopyMapDTO
 * @date 2024/12/11 15:01
 **/
@Data
public class SyncMapTOCopyMapDTO {

    /**
     * map总数
     */
    private Integer totalCount;

    /**
     * 需要同步的简历总数
     */
    private Integer needSyncCount;

    /**
     * 同步成功的简历总数（不需要同步的，不计入此数）
     */
    private Integer successCount;

    /**
     * 同步异常，或者需要同步但同步失败的简历总数
     */
    private Integer failCount;

    private List<String> failList;

    /**
     * 同步任务总耗时，单位毫秒
     */
    private Long timeCost;
}