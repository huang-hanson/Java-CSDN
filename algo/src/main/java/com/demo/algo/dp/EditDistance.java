package com.demo.algo.dp;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName EditDistance
 * @Description 编辑距离
 *
 * 软考2021年下半年试题4
 *
 * 生物学上通常采用编辑距离来定义两个物种DNA序列的相似性，从而刻画物种之间的进化关系。
 * 具体来说，编辑距离是指将一个字符串变换为另一个字符所需要的最小操作次数。
 *
 * demo
 *
 * 计算 "CTGA" → "ACGCTA" 的编辑距离
 * 我们逐步分析如何从 A = "CTGA" 变成 B = "ACGCTA"：
 *
 * 步骤	操作	当前字符串	说明
 * 1	插入 'A'	ACTGA	在开头插入 'A'
 * 2	替换 'T' → 'G'	ACGGA	将第 3 个字符 'T' 替换为 'G'
 * 3	替换 'G' → 'C'	ACGCA	将第 4 个字符 'G' 替换为 'C'
 * 4	插入 'T'	ACGCTA	在末尾插入 'T'
 * 总操作次数：4 次（2 次插入 + 2 次替换）。
 *
 * @date 2025/5/21 19:46
 **/
public class EditDistance {

    private static final int N = 100;
    private static char[] A = "CTGA".toCharArray();
    private static char[] B = "ACGCTA".toCharArray();
    private static int[][] d = new int[N][N];

    // 辅助函数：返回两个整数中的较小值
    private static int min(int a, int b) {
        return a < b ? a : b;
    }

    // 计算编辑距离的核心函数
    public static int editDistance(char[] str1, int len1, char[] str2, int len2) {
        int i, j, diff, temp;

        // 初始化边界条件：str1的前i个字符与空字符串的编辑距离
        for (i = 0; i <= len1; i++) {
            d[i][0] = i;
        }

        // 初始化边界条件：空字符串与str2的前j个字符的编辑距离
        for (j = 0; j <= len2; j++) {
            d[0][j] = j;  // (1) 填空位置
        }

        // 动态规划填充表格
        for (i = 1; i <= len1; i++) {
            for (j = 1; j <= len2; j++) {
                if (str1[i - 1] == str2[j - 1]) {  // (2) 填空位置：字符相等时无需操作
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    temp = min(d[i - 1][j] + 1, d[i][j - 1] + 1);  // 删除或插入操作
                    d[i][j] = min(temp, d[i - 1][j - 1] + 1);     // (3) 填空位置：替换操作
                }
            }
        }

        return d[len1][len2];  // (4) 填空位置：返回最终结果
    }

    public static void main(String[] args) {
        int distance = editDistance(A, A.length, B, B.length);
        System.out.println("编辑距离: " + distance);  // 输出应为3
    }

}