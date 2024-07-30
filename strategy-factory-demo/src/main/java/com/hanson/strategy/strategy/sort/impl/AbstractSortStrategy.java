package com.hanson.strategy.strategy.sort.impl;

import com.hanson.strategy.strategy.sort.SortStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName AbstractSortStrategy
 * @Description TODO
 * @date 2024/7/30 19:40
 **/
@Service
@Slf4j
public abstract class AbstractSortStrategy implements SortStrategy {

    @Override
    public abstract void sort(int[] array);

    @Override
    public abstract String getStrategyName();

    protected void printStrategyUsed() {
        log.info("使用 " + getStrategyName() + " 排序策略对数组排序");
    }
}