package 动态规划.单串.买卖股票;

/**
 * The Class: TranslateNum
 * 剑指 Offer 46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 *
 * 提示：
 *
 * 0 <= num < 231
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-18 00:26
 */
public class TranslateNum {
    /**
     * 解法1: 动态规划
     * 本质上是打家劫舍和买卖股票的变形题
     * 每个数字有两种选择，和前一个数字组合在一起翻译、或者不和前一个数字组合在一起翻译
     *
     */
    public int translateNum(int num) {
        String numString = String.valueOf(num);
        int numLength = numString.length();
        if (numLength == 1) {
            return 1;
        }
        // dp[i][0]表示以第i个数字结尾，且第i个数字单独翻译时的组合数
        // dp[i][1]表示以第i个数字结尾，且第i-1个数字、第i个数字放在一起翻译时的组合数
        int[][] dp = new int[numLength][2];
        dp[0][0] = 1;
        dp[1][0] = 1;
        // 注意不要直接将Integer.parseInt(numString.substring(0, 2)) < 26作为条件，会翻译07、08等不应该翻译的字符
        dp[1][1] = numString.charAt(0) == '1' || (numString.charAt(0) == '2' && numString.charAt(1) - '0' < 6)? 1 : 0;
        for (int i = 2; i < numLength; i++) {
            if (numString.charAt(i - 1) == '1' || (numString.charAt(i - 1) == '2' && numString.charAt(i) - '0' < 6)) {
                dp[i][1] = dp[i - 2][0] + dp[i - 2][1];
            } else {
                dp[i][1] = 0;
            }
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
        }
        return dp[numLength - 1][0] + dp[numLength - 1][1];
    }
}
