package 动态规划.双串.矩阵;

/**
 * The Class: MinFallingPathSum
 * 931. 下降路径最小和
 * https://leetcode-cn.com/problems/minimum-falling-path-sum/
 *
 * 给定一个方形整数数组 A，我们想要得到通过 A 的下降路径的最小和。
 *
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-23 22:45
 */
public class MinFallingPathSum {
    /**
     * 解法1: 动态规划
     *
     * dp[i][j]表示以(i, j)为终点的下降路径最小和，则对于中间列而言
     * dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + A[i][j]
     *
     * @param A .
     * @return .
     */
    public int minFallingPathSumSolution1(int[][] A) {
        if (A == null || A.length == 0 || A[0].length == 0) {
            return 0;
        }
        int m = A.length;
        int n = A[0].length;
        // 如果数组只有1列，则只有1条下降路径
        if (n < 2) {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += A[i][0];
            }
            return ans;
        }

        int[][] dp = new int[m][n];
        // dp[i][j]表示以（i, j）为终点的下降路径最小和
        for (int j = 0; j < n; j++) {
            dp[0][j] = A[0][j];
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + A[i][0];
            for (int j = 1; j < n - 1; j++) {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + A[i][j];
            }
            dp[i][n - 1] = Math.min(dp[i - 1][n - 2], dp[i - 1][n - 1]) + A[i][n - 1];
        }
        int minSum = dp[m - 1][0];
        for (int j = 1; j < n; j++) {
            minSum = Math.min(minSum, dp[m - 1][j]);
        }
        return minSum;
    }
}
