package com.demo.algo.dp;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName ConsecutiveArrayMaxSum
 * @Description 连续数组求和
 * 给定一个数组 array[1, 4, -5, 9, 8, 3, -6]，在这个数字中有多个子数组，子数组和最大的应该是：[9, 8, 3]，输出20，再比如数组为[1, -2, 3, 10, -4, 7, 2, -5]，和最大的子数组为[3, 10, -4, 7, 2]，输出18。
 *
 * 状态方程式： max( dp[ i ] ) = getMax( max( dp[ i -1 ] ) + arr[ i ] ,arr[ i ] )
 * 上面式子的意义是：我们从头开始遍历数组，遍历到数组元素 arr[ i ] 时，连续的最大的和 可能为 max( dp[ i -1 ] ) + arr[ i ] ，也可能为 arr[ i ] ，做比较即可得出哪个更大，取最大值。时间复杂度为 n。
 * dp[i] 就是以数组下标为 i 的数做为结尾的最大子序列和，注意是以 i 为结尾，比如说现在有一个数组{6,-3,-2,7,-15,1,2,2}，dp[2]就是以-2为结尾的，那么显然dp[2]的最大值就是1咯
 * @date 2025/5/21 16:50
 **/
public class ConsecutiveArrayMaxSum {

    private static int getMax(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int sum = arr[0];// 临时最大值
        int maxSum = arr[0];// 比较后最大值
        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(sum + arr[i], arr[i]);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, -5, 9, 8, 3, -6};
        System.out.println(getMax(arr));
    }

}