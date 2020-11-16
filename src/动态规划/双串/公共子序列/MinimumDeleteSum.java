package 动态规划.双串.公共子序列;

/**
 * The Class: MinimumDeleteSum
 * 712. 两个字符串的最小ASCII删除和
 * https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings/
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 * @author: Kaguya Y
 * @since: 2020-11-16 23:03
 */
public class MinimumDeleteSum {
    /**
     * 解法1: 直接套用LCS的方法计算
     *
     * dp[i][j]表示s1前i个字符和s2前j个字符的最小删除和
     * 如果s1.charAt[i]==s2.charAt[j] dp[i][j] = dp[i - 1][j - 1]
     * 否则，dp[i][j]=Math.min(dp[i - 1][j] + s1.codePointAt(i), dp[i][j - 1] + s2.codePointAt(j))
     *
     * @param s1 .
     * @param s2 .
     * @return .
     */
    public int minimumDeleteSumSolution1(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        //注意处理i=0或j=0下的边界条件，这里和LCS不同，dp[i][0]不为0
        for (int i = 0; i < l1; i++) {
            dp[i + 1][0] = dp[i][0] + s1.codePointAt(i);
        }
        for (int j = 0; j < l2; j++) {
            dp[0][j + 1] = dp[0][j] + s2.codePointAt(j);
        }

        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.min(dp[i][j + 1] + s1.codePointAt(i),
                            dp[i + 1][j] + s2.codePointAt(j));
                }
            }
        }
        return dp[l1][l2];
    }

    /**
     * 解法2: 计算s1总和+s2总和-2*LCS的最大字符和，即为最小删除和
     * 通过降维进行优化
     *
     * @param s1 .
     * @param s2 .
     * @return .
     */
    public int minimumDeleteSumSolution2(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();
        char[] s1CharArray = s1.toCharArray();
        char[] s2CharArray = s2.toCharArray();
        int[] dp = new int[l2 + 1];
        for (int i = 0; i < l1; i++) {
            int pre = 0;
            for (int j = 0; j < l2; j++) {
                int temp = dp[j + 1];
                if (s1CharArray[i] == s2CharArray[j]) {
                    dp[j + 1] = pre + s1CharArray[i];
                } else {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j]);
                }
                pre = temp;
            }
        }
        int sum = 0;
        // 统计s1、s2的字符总和
        for (int i = 0; i < l1; i++) {
            sum += s1CharArray[i];
        }
        for (int i = 0; i < l2; i++) {
            sum += s2CharArray[i];
        }
        return sum - 2 * dp[l2];
    }
}
