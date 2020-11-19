package 动态规划.区间规划.回文;

/**
 * The Class: LongestPalindrome
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author: Kaguya Y
 * @since: 2020-11-19 22:34
 */
public class LongestPalindrome {
    /**
     * 方法1: 动态规划
     * dp[i][j]记录第i个元素到第j个元素之间的子串是否为回文串
     * 显然，我们有dp[i][j] = (charArray[i] == charArray[j]) && dp[i + 1][j - 1]
     * 步长从0开始依次增大
     *
     * @param s .
     * @return .
     */
    public String longestPalindromeSolution1(String s) {
        int sLen = s.length();
        if (sLen < 2) {
            return s;
        }
        boolean[][] dp = new boolean[sLen][sLen];
        int maxLen = 0;
        int start = 0;
        char[] charArray = s.toCharArray();

        for (int len = 0; len < sLen; len++) {
            // 步长从0开始依次增大
            for (int i = 0; i + len < sLen; i++) {
                // i代表子串的起点
                int j = i + len;
                // j代表子串的终点
                if (len == 0) {
                    dp[i][j] = true;
                } else if (len == 1) {
                    dp[i][j] = charArray[i] == charArray[j];
                } else {
                    dp[i][j] = (charArray[i] == charArray[j]) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && len > maxLen) {
                    start = i;
                    len = maxLen;
                }
            }
        }
        return s.substring(start, start + maxLen + 1);
    }

    /**
     * 方法2: 中心扩展
     * 从第0个元素开始，依次以该元素为中心向两边扩展，直至不为回文串，记录以该元素为中心的回文串的最大长度
     *
     * @param s .
     * @return .
     */
    public String longestPalindromeSolution2(String s) {
        int sLen = s.length();
        if (sLen < 2) {
            return s;
        }
        char[] charArray = s.toCharArray();
        int start = 0;
        int end = 0;

        for (int i = 0; i < sLen; i++) {
            int len1 = expandFromCenter(charArray, sLen, i, i);
            int len2 = expandFromCenter(charArray, sLen, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                // 当回文串以第i个元素自身展开时，start = i - (len - 1) / 2, end = i + (len - 1) / 2
                // 当回文串以第i个元素和第i+1个元素展开时， start = i - (len - 2) / 2, end = i + 1 + (len - 2) / 2
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);

    }

    private int expandFromCenter(char[] charArray, int sLen, int left, int right) {
        while (left >= 0 && right < sLen && charArray[left] == charArray[right]) {
            left--;
            right++;
        }
        // 显然，当我们跳出循环时，循环条件已经被打破了一次，意味着此时的left和right已经被多计了一次
        // 因此需要把当前区间长度right - left + 1减去2才能得到真实的区间长度
        // 也有可能是在执行left-- right++操作之前，就已经不满足charArray[left] == charArray[right]
        // 这种情况下返回的区间长度为-1，不过不影响我们的结果
        return right - left - 1;
    }

}
