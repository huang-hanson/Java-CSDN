package com.hanson.strategy.strategy.sort.impl;

import org.springframework.stereotype.Component;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BubbleSortStrategy
 * @Description TODO
 * @date 2024/7/30 19:49
 **/
@Component
public class BubbleSortStrategy extends AbstractSortStrategy {

    @Override
    public void sort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // 交换 array[j] 和 array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        printStrategyUsed();
    }

    @Override
    public String getStrategyName() {
        return "冒泡排序";
    }
}