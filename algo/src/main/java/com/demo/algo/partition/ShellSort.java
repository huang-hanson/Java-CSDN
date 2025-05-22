package com.demo.algo.partition;

import java.util.Arrays;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ShellSort
 * @Description 希尔排序
 * @date 2025/5/22 14:15
 **/
public class ShellSort {

    public static void shellSort(int[] data) {
        int n = data.length;
        int[] delta = new int[n / 2]; // 存储增量序列
        int k = n;
        int i = 0;

        // 生成增量序列
        do {
            k = k / 2;          // (1) 增量折半
            delta[i++] = k;      // 存储增量
        } while (k > 1);         // (2) 直到 k <= 1 时停止

        i = 0;
        int dk;
        while (i < delta.length && (dk = delta[i]) > 0) { // 按增量序列排序
            for (k = dk; k < n; ++k) {
                if (data[k] < data[k - dk]) { // (3) 判断是否需要插入
                    int t = data[k];         // 待插入元素
                    int j;
                    for (j = k - dk; j >= 0 && t < data[j]; j -= dk) {
                        data[j + dk] = data[j]; // 元素后移
                    }
                    data[j + dk] = t;    // (4) 插入元素
                }
            }
            ++i;
        }
    }

    public static void main(String[] args) {
        int[] arr = {15, 9, 7, 8, 20, -1, 4};
        shellSort(arr);
        System.out.println(Arrays.toString(arr)); // 输出: [-1, 4, 7, 8, 9, 15, 20]
    }

}