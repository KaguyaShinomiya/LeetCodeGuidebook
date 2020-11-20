package 动态规划.区间规划.回文;

/**
 * The Class: LongestPanlindromeSubseq
 * 516. 最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 *
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-20 23:54
 */
public class LongestPanlindromeSubseq {
    /**
     * 解法1: 动态规划
     * dp[i][j]记录从第i个元素到第j个元素之间最长的回文子序列长度
     * 显然，对于一般的情况，dp[i][j]在第i个元素和第j个元素相同时，取值为dp[i + 1][j - 1] + 2
     * 在不相同时，取值为Math.max(dp[i + 1][j], dp[i][j - 1])
     * 注意对于i到j之间的距离为0或1的特殊情况进行讨论，此时区间[i+1, j-1]是不存在的
     *
     * @param s .
     * @return .
     */
    public int lonestPanlindromeSubseqSolution1(String s) {
        int sLen = s.length();
        if (sLen < 2) {
            return sLen;
        }
        int[][] dp = new int[sLen][sLen];
        char[] charArray = s.toCharArray();
        for (int len = 0; len <= sLen; len++) {
            // len表示了区间的长度
            for (int i = 0; i + len < sLen; i++) {
                int j = i + len;
                // i和j分别表示区间的起点和终点
                if (len == 0) {
                    dp[i][j] = 1;
                } else if (len == 1) {
                    dp[i][j] = charArray[i] == charArray[j]? 2 : 1;
                } else if (charArray[i] == charArray[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][sLen - 1];
    }
}
