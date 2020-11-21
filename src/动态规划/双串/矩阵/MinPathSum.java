package 动态规划.双串.矩阵;

/**
 * The Class: MinPathSum
 * 64. 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * @author: Kaguya Y
 * @since: 2020-11-21 18:30
 */
public class MinPathSum {
    /**
     * 解法1: 动态规划
     * dp[i][j]反应了从(0,0)走到(i,j)的最小路径和
     *
     * @param grid .
     * @return .
     */
    public int minPathSumSolution1(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 解法2: 动态规划，空间优化
     * 用一维数组记录到当前行中各列的最小路径和
     * 需要对第一行和第一列做特殊处理
     *
     * @param grid .
     * @return .
     */
    public int minPathSumSolution2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }
}
