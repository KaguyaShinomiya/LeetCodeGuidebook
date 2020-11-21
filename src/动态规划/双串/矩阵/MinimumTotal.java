package 动态规划.双串.矩阵;

import java.util.Arrays;
import java.util.List;

/**
 * The Class: MinimumTotal
 * 120.三角形最小路径和
 * https://leetcode-cn.com/problems/triangle/
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-21 00:06
 */
public class MinimumTotal {
    /**
     * 解法1: 动态规划
     *
     * dp[i][j]反应了从(0,0)走到(i,j)的最小路径和
     * 显然，在j不等于0或者i时，dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j)
     * j等于0或i时，i,j位于三角形的最左边或最右边，此时转移方程需要单独讨论。
     * 时间复杂度和空间复杂度均为O(n^2)
     *
     * @param triangle .
     * @return .
     */
    public int minimumTotalSolution1(List<List<Integer>> triangle) {
        int height = triangle.size();
        if (height < 2) {
            return triangle.get(0).get(0);
        }
        int[][] dp = new int[height][height];
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < height; i++) {
            for (int j = 0; j <= i; j++) {
                 if (j == 0) {
                     dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                 } else if (j == i) {
                     dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                 } else {
                     dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                 }
            }
        }
        int minAns = dp[height - 1][0];
        for (int j = 0; j < height; j++) {
            minAns = Math.min(minAns, dp[height - 1][j]);
        }
        return minAns;
    }

    /**
     * 解法2: 动态规划 + 空间优化
     * 使用一维数组dp记录到(i, j)的最小路径和
     * dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j)
     * 注意需要倒着遍历j，因为这里的dp[j - 1]记录的是(i - 1, j - 1)的最小路径和
     *
     * @param triangle .
     * @return .
     */
    public int minimumTotalSolution2(List<List<Integer>> triangle) {
        int height = triangle.size();
        int[] dp = new int[height];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < height; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }

        int minAns = dp[0];
        for (int j = 1; j < height; j++) {
            minAns = Math.min(minAns, dp[j]);
        }
        return minAns;
    }


}
