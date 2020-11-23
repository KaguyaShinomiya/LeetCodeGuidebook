package 动态规划.双串.矩阵;

/**
 * The Class: MaximalSquare
 * 221. 最大正方形
 * https://leetcode-cn.com/problems/maximal-square/
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * @author: Kaguya Y
 * @since: 2020-11-23 21:49
 */
public class MaximalSquare {

    /**
     * 解法1: 动态规划
     *
     * dp[i][j]表示以(i, j)为右下角的最大正方形边长，则
     * 若matrix[i][j]为0时，dp[i][j]必然为0
     * 若matrix[i][j]为1时，dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     * 参考https://leetcode-cn.com/problems/count-square-submatrices-with-all-ones/solution/tong-ji-quan-wei-1-de-zheng-fang-xing-zi-ju-zhen-2/
     * 对于i或j等于0的边界条件做特殊处理
     *
     * @param matrix .
     * @return .
     */
    public int maximalSquareSolution1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxSide = 0;
        // dp[i][j]表示以(i, j)为右下角的最大正方形边长
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(dp[i][j], maxSide);
                }
            }
        }
        return maxSide * maxSide;
    }
}
