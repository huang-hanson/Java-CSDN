package com.hanson.strategy.strategy.sort.impl;

import org.springframework.stereotype.Component;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName InsertionSortStrategy
 * @Description TODO
 * @date 2024/7/30 19:50
 **/
@Component
public class InsertionSortStrategy extends AbstractSortStrategy {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        printStrategyUsed();
    }

    @Override
    public String getStrategyName() {
        return "插入排序";
    }
}