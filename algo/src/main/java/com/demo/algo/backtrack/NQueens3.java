package com.demo.algo.backtrack;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName NQueens2
 * @Description N皇后问题 III
 * @date 2025/5/22 11:23
 **/
public class NQueens3 {

    private static final int n = 4; // 皇后数量
    /**
     * queen[j] = i
     * queen的索引是行数，值是列数
     *
     * 行号\列号 | 1 | 2 | 3 | 4 |
     * ---------------------------
     * 1        |   | Q |   |   |
     * 2        |   |   |   | Q |
     * 3        | Q |   |   |   |
     * 4        |   |   | Q |   |
     *
     * queen = ( 2 4 1 3 )
     *
     **/
    private static int[] queen = new int[n + 1]; // 皇后位置数组（索引从1开始）

    // 打印当前皇后摆放方案
    private static void show() {
        System.out.print("(");
        for (int i = 1; i <= n; i++) {
            System.out.print(" " + queen[i]);
        }
        System.out.println(" )");
    }

    // 检查当前位置是否合法
    private static boolean place(int j) {
        for (int i = 1; i < j; i++) {
            if (queen[i] == queen[j] || Math.abs(queen[i] - queen[j]) == j - i) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int j = 1; // 从第1行开始
        queen[j] = 0; // 初始化

        // 外层while控制行回溯
        while (j > 0) {
            queen[j]++; // 尝试下一列

            // 内层while寻找当前行的合法位置
            while (queen[j] <= n && !place(j)) {
                queen[j]++;
            }

            if (queen[j] <= n) { // 找到合法位置
                if (j == n) { // 找到完整解
                    show();
                } else { // 继续下一行
                    j++;
                    queen[j] = 0; // 下一行从第0列开始尝试
                }
            } else { // 当前行无解，回溯到上一行
                j--;
            }
        }
    }

}