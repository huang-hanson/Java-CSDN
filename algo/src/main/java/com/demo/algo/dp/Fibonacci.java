package com.demo.algo.dp;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Fibonacci
 * @Description 斐波那契数列第n项
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1     F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * @date 2025/5/21 16:22
 **/
public class Fibonacci {

    private static int fib(int n) {
        // 创建一个数组保存中间态
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n] % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(fib(5));
    }

}