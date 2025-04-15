package com.hanson.java.base;

import java.util.Arrays;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName HeapSort
 * @Description TODO
 * @date 2025/4/11 15:34
 **/
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[] { 5, 2, 1, 6, 4, 8, 11, 34, 56, 17, 26 };// 测试数组
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            adjustSort(arr, i, arr.length);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            adjustSort(arr, 0, i);
        }
    }

    public static void adjustSort(int[] arr, int parent, int lenght) {
        int temp = arr[parent];
        int Child = 2 * parent + 1;
        while (Child < lenght) {
            // 找到左右子节点中较大的那个节点，如果左边大就用child，如果右边大就用child++
            if (Child + 1 < lenght && arr[Child] < arr[Child + 1]) {
                Child++;
            }
            if (temp >= arr[Child]) {
                break;
            }
            arr[parent] = arr[Child];

            parent = Child;
            // 查找当前节点的子节点，如果有子节点，继续调整
            Child = parent * 2 + 1;
        }
        // 交换数据
        arr[parent] = temp;
    }
}