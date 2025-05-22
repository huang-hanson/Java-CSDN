package com.demo.algo.partition;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MergeSort
 * @Description 归并排序
 * @date 2025/5/22 14:29
 **/
public class MergeSort {
    private static final int MAX = 65536; // 定义哨兵值

    // 合并两个已排序的子数组
    private static void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int[] left = new int[n1 + 1];
        int[] right = new int[n2 + 1];

        // 拷贝左半部分到left数组
        for (int i = 0; i < n1; i++) {
            left[i] = arr[p + i];
        }
        left[n1] = MAX; // 设置哨兵

        // 拷贝右半部分到right数组
        for (int i = 0; i < n2; i++) {
            right[i] = arr[q + 1 + i];
        }
        right[n2] = MAX; // 设置哨兵

        int i = 0, j = 0;
        // 合并两个子数组
        for (int k = p; k <= r; k++) {  // (1) 循环条件
            if (left[i] > right[j]) {
                arr[k] = right[j];      // (2) 赋值操作
                j++;
            } else {
                arr[k] = left[i];
                i++;
            }
        }
    }

    // 归并排序主函数
    public static void mergeSort(int[] arr, int begin, int end) {
        if (begin < end) {              // (3) 递归终止条件
            int mid = (begin + end) / 2;
            mergeSort(arr, begin, mid);
            mergeSort(arr, mid + 1, end); // (4) 排序右半部分
            merge(arr, begin, mid, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("排序前:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("\n排序后:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}