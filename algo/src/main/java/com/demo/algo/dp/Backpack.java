package com.demo.algo.dp;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName Backpack
 * @Description 01背包问题
 * 有N件物品和一个容量为V的背包。第i件物品的费用是c[i]，价值是w[i]。求解将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 * @date 2025/5/21 18:10
 **/
public class Backpack {

    public static void main(String[] args) {
        int[] val = {3, 4, 5, 8};
        int[] weight = {2, 3, 4, 5};
        int W = 8;
        System.out.println(func(val, weight, W));
    }

    /**
     * 0-1 背包
     *
     * @param val    价值
     * @param weight 重量
     * @param W      背包容量
     * @return 最优解
     */
    private static int func(int[] val, int[] weight, int W) {
        int N = weight.length;   //记录所有的物品数
        int[][] V = new int[N + 1][W + 1];  //创建背包矩阵
        //初始化矩阵 列，背包容量为0
        for (int col = 0; col <= W; col++) {
            V[0][col] = 0;
        }
        for (int row = 0; row <= N; row++) {
            V[row][0] = 0;
        }
        for (int i = 1; i <= N; i++) {  //一行一行填充值
            for (int j = 1; j <= W; j++) {  //一列一列填充值
                if (weight[i - 1] <= j) {  //如果当前物品重量小于等于背包中的当前重量 i为1是，weight[0]是第一个物品的重量
                    //比较不加入该物品时该重量的最大价值（前一行）与当前物品的价值+可以容纳的剩余重量的价值
                    V[i][j] = Math.max(val[i - 1] + V[i - 1][j - weight[i - 1]], V[i - 1][j]);
                } else { //如果当前物品重量大于背包中的当前重量
                    V[i][j] = V[i - 1][j];  //直接使用前一行的最优解
                }
            }
        }
        return V[N][W];
    }

}