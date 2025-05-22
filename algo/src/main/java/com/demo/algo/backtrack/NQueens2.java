package com.demo.algo.backtrack;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NQueens2
 * @Description N皇后问题 II
 * @date 2025/5/22 11:23
 **/
public class NQueens2 {

    private static int n = 4; // 皇后数量
    private static int[] queen = new int[n + 1]; // 皇后位置数组，索引从1开始

    // 输出所有皇后摆放方案
    private static void show() {
        System.out.print("(");
        for (int i = 1; i <= n; i++) {
            System.out.print(" " + queen[i]);
        }
        System.out.println(" )");
    }

    // 检查当前列能否放置皇后
    private static boolean place(int j) {
        for (int i = 1; i < j; i++) {
            // 检查与已摆放的皇后是否在同一列或者同一斜线上
            if (queen[i] == queen[j] || Math.abs(queen[i] - queen[j]) == (j - i)) {
                return false;
            }
        }
        return true;
    }

    // N皇后回溯算法
    private static void nQueen(int j) {
        for (int i = 1; i <= n; i++) {
            queen[j] = i; // 尝试在第j行的第i列放置皇后
            if (place(j)) { // 如果当前位置合法
                if (j == n) { // 如果所有皇后都摆放好
                    show(); // 输出当前摆放方案
                } else { // 否则继续摆放下一个皇后
                    nQueen(j + 1); // 递归处理下一行
                }
            }
        }
    }

    public static void main(String[] args) {
        nQueen(1); // 从第1行开始摆放皇后
    }

}