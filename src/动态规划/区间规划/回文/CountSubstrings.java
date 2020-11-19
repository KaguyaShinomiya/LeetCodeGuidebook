package 动态规划.区间规划.回文;

/**
 * The Class: CountSubstrings
 * 647. 回文子串
 * https://leetcode-cn.com/problems/palindromic-substrings/
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 * @author: Kaguya Y
 * @since: 2020-11-20 00:54
 */
public class CountSubstrings {
    /**
     * 解法1: 动态规划
     * 一个很自然的想法就是直接套用最长回文串，每次出现回文就增加一次计数
     *
     * @param s .
     * @return .
     */
    public int countSubstringsSolution1(String s) {
        int sLen = s.length();
        if (sLen < 2) {
            return sLen;
        }
        boolean[][] dp = new boolean[sLen][sLen];
        int count = 0;
        char[] charArray = s.toCharArray();

        for (int len = 0; len < sLen; len++) {
            for (int i = 0; i + len < sLen; i++) {
                int j = i + len;
                if (len == 0) {
                    dp[i][j] = true;
                } else if (len == 1) {
                    dp[i][j] = charArray[i] == charArray[j];
                } else {
                    dp[i][j] = (charArray[i] == charArray[j]) && dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
