package 动态规划.双串.字符串匹配;

/**
 * The Class: IsMatch
 * 10. 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-11-27 22:47
 */
public class IsMatch {
    /**
     * 解法1: 动态规划
     * 记dp[i][j]为s中[0,1...,i - 1]的字符与p中[0,1...,j - 1]字符之间是否相同
     * 显然，如果p[j - 1]不为*，则：
     * 如果p[j - 1]和s[i - 1]相同，则dp[i][j] = dp[i - 1][j - 1]；
     * 否则，dp[i][j] = false；
     *
     * 如果p[j - 1]为*，则需要和p[j - 2]放在一起考虑：
     * 如果p[j - 2]和s[i - 1]不同，例如dc和db*，则可以把p[j - 2]、p[j - 1]从p[0 : j - 1]中去掉，dp[i][j] |= dp[i][j - 2]；
     * 如果p[j - 2]和s[i - 1]相同，那么可以把s[i - 1]从s[0 : i - 1]中去掉继续匹配，因为p[j - 2]、p[j - 1]的组合可以匹配任意多个s[i - 1]
     * 例如dbbb和db*的匹配结果事实上就等于dbb和db*的匹配结果；进而又等于db和db*的匹配结果，进而又等于d和db*的匹配结果，进而又等于d和d的匹配结果
     * 在这种情况下有dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 2]；
     * 其中|| dp[i][j - 2]对应于：p[j - 2]和s[i - 1]相同、但事实上p[j - 2]、p[j - 1]并没有匹配上s[i - 1]，例如dbb和dbbb*
     *
     * 在比较字符是否相同时，需要考虑"."的存在，并注意i和j不能越界
     *
     * @param s .
     * @param p .
     * @return .
     */
    public boolean isMatchSolution1(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pChars[j - 1] == '*') {
                    if (j > 1) {
                        // 如果p中前一个字符和s的当前字符不匹配，意味着p中前一个字符与*的组合只匹配了0次
                        // 例如：s为da，p为db*，当前i=0，j=2，"b*"无法与s中的"a"进行匹配
                        dp[i][j] |= dp[i][j - 2];
                        if (i > 0 && (sChars[i - 1] == pChars[j - 2] || pChars[j - 2] == '.')) {
                            // 如果p中前一个字符和s的当前字符匹配：
                            // 例如，s为daaab，p为da*b，dp[i - 1][j]即此处的a*匹配了多次a
                            // 例如，s为da，p为daa*，虽然j-1字符和i是匹配的，但是事实上a*没有匹配到任何的a，所以选择dp[i][j - 2]
                            dp[i][j] |= dp[i - 1][j];
                        }
                    }
                } else if (i > 0 && (pChars[j - 1] == '.' || pChars[j - 1] == sChars[i - 1])) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
