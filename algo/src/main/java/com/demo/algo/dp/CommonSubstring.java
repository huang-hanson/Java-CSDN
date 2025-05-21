package com.demo.algo.dp;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName CommonSubstring
 * @Description 公共子串
 * 给定两个只包含小写字母的字符串，计算两个字符串的最大公共子串的长度。
 * 注：子串的定义指一个字符串删掉其部分前缀和后缀（也可以不删）后形成的字符串。
 * 数据范围：字符串长度：1\le s\le 150\1≤s≤150
 * 进阶：时间复杂度：O(n^3)\O(n 3 ) ，空间复杂度：O(n)\O(n)
 * <p>
 * 输入描述:输入两个只包含小写字母的字符串
 * 输出描述:输出一个整数，代表最大公共子串的长度
 * <p>
 * 输入:
 * asdfas
 * werasdfaswer
 * 输出:
 * 6
 **/
public class CommonSubstring {

    public static void main(String[] args) {
        System.out.println(getMaxLen("asdfas", "werasdfaswer"));
    }

    private static int getMaxLen(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int maxLen = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (maxLen < dp[i][j]) {
                    maxLen = dp[i][j];
                }
            }
        }
        return maxLen;
    }

}