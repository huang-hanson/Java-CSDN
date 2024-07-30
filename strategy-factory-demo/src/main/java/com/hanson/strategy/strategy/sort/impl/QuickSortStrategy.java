package com.hanson.strategy.strategy.sort.impl;

import org.springframework.stereotype.Component;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName QuickSortStrategy
 * @Description TODO
 * @date 2024/7/30 19:50
 **/
@Component
public class QuickSortStrategy extends AbstractSortStrategy {

    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        printStrategyUsed();
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    @Override
    public String getStrategyName() {
        return "快速排序";
    }
}