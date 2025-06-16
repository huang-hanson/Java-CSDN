package com.fileimport.export.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StudentInfoImportCountVO
 * @Description 学生信息导入计数器
 * @date 2025/6/16 16:30
 **/
@Data
public class StudentInfoImportCountVO {

    /**
     * 导入成功数量
     */
    private AtomicInteger successCount;

    /**
     * 导入失败数量
     */
    private AtomicInteger failureCount;

    /**
     * 导入失败的行号
     */
    private List<Integer> failureRows;

    /**
     * 新增数量
     */
    private AtomicInteger insertCount;

    /**
     * 更新覆盖数量
     */
    private AtomicInteger updateCount;

    public StudentInfoImportCountVO() {
        this.successCount = new AtomicInteger(0);
        this.failureCount = new AtomicInteger(0);
        this.insertCount = new AtomicInteger(0);
        this.updateCount = new AtomicInteger(0);
        this.failureRows = new ArrayList<>();
    }

    /**
     * 新增成功自增计数
     */
    public StudentInfoImportCountVO insertSuccessIncrement() {
        this.getSuccessCount().incrementAndGet();
        this.getInsertCount().incrementAndGet();
        return this;
    }

    /**
     * 更新成功自增计数
     */
    public StudentInfoImportCountVO updateSuccessIncrement() {
        this.getSuccessCount().incrementAndGet();
        this.getUpdateCount().incrementAndGet();
        return this;
    }

    /**
     * 导入失败自增计数
     */
    public StudentInfoImportCountVO failureIncrement(Integer rowNum) {
        this.getFailureCount().incrementAndGet();
        this.getFailureRows().add(rowNum);
        return this;
    }
}