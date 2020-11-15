package 动态规划.双串.公共子序列;

/**
 * The Class: LongestCommonSubsequence
 * 1143. 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * @author: Kaguya Y
 * @since: 2020-11-15 18:50
 */
public class LongestCommonSubsequence {
    /**
     * dp[i][j]表示字符串1的前i个部分和字符串2的前j个部分的最大公共子序列长度，
     * 如果text1.charAt(i) == text2.charAt(j)，则dp[i][j] = dp[i - 1][j - 1] + 1;
     * 否则，dp[i][j] = max(dp[i][j-1], dp[i-1][j])
     *
     * @param text1 .
     * @param text2 .
     * @return .
     */
    public int longestCommonSubsequenceSolution1(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (charArray1[i] == charArray2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }

        return dp[l1][l2];
    }
}
