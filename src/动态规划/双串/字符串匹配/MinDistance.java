package 动态规划.双串.字符串匹配;

/**
 * The Class: MinDistance
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-29 21:00
 */
public class MinDistance {
    /**
     * 解法1: 动态规划
     *
     * 用dp[i][j]表示words1[:i]和words2[:j]之间的编辑距离，怎么推出dp[i][j]和低阶元素之间的关系呢？
     * 我们可以思考一下如果只修改前面的字符串，让前面的字符串匹配之后，加上最后一个元素，需要增加的操作数量：
     * （1）通过一顿操作，使得【words1[: i - 1]与words2[: j - 1]相同】，然后【修改words1的第i个字符，使之和words2的第j个字符相同】:
     * 使得words1[: i - 1]与words2[: j - 1]相同需要的总操作数是dp[i - 1][j - 1]
     * 如果words1的第i个字符和words2的第j个字符相同：那么不需要进行多余的修改操作，直接把words1和words2的最后一个字符粘贴到前面的结果上就行了；
     * 如果words1的第i个字符和words2的第j个字符不同：需要修改words1的最后一个字符，总操作数为dp[i - 1][j - 1] + 1；
     * （2）通过一顿操作，使得【words1[: i]与words2[: j - 1]相同】，然后在words2上加上第j个字符，即dp[i][j - 1] + 1;
     * （3）通过一顿操作，使得【words1[: i - 1]与words2[: j]相同】，然后在words1上加上第i个字符，即dp[i - 1][j] + 1;
     * 转移时取上面三者最小值即可。
     */
    public int minDistanceSolution1(String word1, String word2) {
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        int M = chars1.length;
        int N = chars2.length;

        if (M == 0 || N == 0) {
            return M + N;
        }
        int[][] dp = new int[M + 1][N + 1];

        // words1匹配空的words2字符串
        for (int i = 0; i < M + 1; i++) {
            dp[i][0] = i;
        }
        // words2匹配空的words1字符串
        for (int i = 0; i < N + 1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < M + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                int lastLetterMatched = chars1[i - 1] == chars2[j - 1]? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + lastLetterMatched);
            }
        }
        return dp[M][N];
    }
}
