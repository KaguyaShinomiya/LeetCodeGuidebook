package 贪心算法;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class: NumSub
 * 1513. 仅含 1 的子串数
 *
 * 给你一个二进制字符串 s（仅由 '0' 和 '1' 组成的字符串）。
 *
 * 返回所有字符都为 1 的子字符串的数目。
 *
 * 由于答案可能很大，请你将它对 10^9 + 7 取模后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "0110111"
 * 输出：9
 * 解释：共有 9 个子字符串仅由 '1' 组成
 * "1" -> 5 次
 * "11" -> 3 次
 * "111" -> 1 次
 * 示例 2：
 *
 * 输入：s = "101"
 * 输出：2
 * 解释：子字符串 "1" 在 s 中共出现 2 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-substrings-with-only-1s
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-13 00:56
 */
public class NumSub {
    /**
     * 每次向前匹配到最长的连续的1，如果有n个1，那么这连续的n个1可以形成n(n + 1)/2个新增子字符串
     * 此外反复利用 (a + b) mos c = a mod c + b mod c，不要先累加子字符串总数再取余，容易溢出
     */
    private final static int MOD = 1000000007;
    public int numSub(String s) {
        char[] sArrays = s.toCharArray();
        // 注意这里的subCounts和onesLength都要设为long，否则容易溢出
        long subCounts = 0;
        long onesLength = 0;
        for (int i = 0; i < sArrays.length; i++) {
            if (sArrays[i] == '0') {
                subCounts += onesLength * (onesLength + 1) / 2;
                subCounts %= MOD;
                onesLength = 0;
            } else {
                onesLength++;
            }
        }
        subCounts += onesLength * (onesLength + 1) / 2;
        subCounts %= MOD;
        return (int) subCounts;
    }
}
