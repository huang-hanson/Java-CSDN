package com.hanson.strategy.strategy.sort.impl;

import org.springframework.stereotype.Component;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName SelectionSortStrategy
 * @Description TODO
 * @date 2024/7/30 19:50
 **/
@Component
public class SelectionSortStrategy extends AbstractSortStrategy {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换 array[i] 和 array[minIndex]
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        printStrategyUsed();
    }

    @Override
    public String getStrategyName() {
        return "选择排序";
    }
}