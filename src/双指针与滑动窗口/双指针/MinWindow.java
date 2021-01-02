package 双指针与滑动窗口.双指针;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class: MinWindow
 * 76. 最小覆盖子串
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2021-01-03 01:17
 */
public class MinWindow {
    /**
     * 解法1: 滑动窗口、双指针、哈希表
     * 用两个指针维护滑动窗口，
     * 如果滑动窗口不包含t中所有字符，则将右指针右移；
     * 如果滑动窗口包含了t中的所有字符，则将左指针左移，缩小窗口。
     * 不断比较满足要求的滑动窗口的长度与已知最小窗口长度。
     *
     * 怎么判断滑动窗口包含了t中的所有字符？可以用两个哈希表记录字符串t中所有字符和个数、滑动窗口中存在于字符串t中的所有字符和个数；并进行比较
     *
     */
    Map<Character, Integer> tCharsCnt = new HashMap<>();
    Map<Character, Integer> windowCharsCnt = new HashMap<>();
    public String minWindow(String s, String t) {
        char[] tChars = t.toCharArray();
        char[] sChars = s.toCharArray();
        tCharsCnt = new HashMap<>();
        for (char tChar : tChars) {
            tCharsCnt.put(tChar, tCharsCnt.getOrDefault(tChar, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int minLength = sChars.length + 1;
        int leftPos = -1;
        int rightPos = -1;
        windowCharsCnt = new HashMap<>();
        while (right < sChars.length) {
            while (right < sChars.length && !tCharsCnt.containsKey(sChars[right])) {
                right++;
            }
            if (right == sChars.length) {
                break;
            }
            windowCharsCnt.put(sChars[right], windowCharsCnt.getOrDefault(sChars[right], 0) + 1);
            while (left <= right && windowContainsT()) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    leftPos = left;
                    rightPos = right;
                }
                // 当前窗口已经包含了t的所有字符，需要将left左移，缩小窗口
                // 在左移left之前，先把left所代表的字符从windowCharsCnt中去除掉
                if (windowCharsCnt.containsKey(sChars[left])) {
                    windowCharsCnt.put(sChars[left], windowCharsCnt.get(sChars[left]) - 1);
                }
                left++;
                while (left <= right && !tCharsCnt.containsKey(sChars[left])) {
                    left++;
                }
            }
            right++;
        }
        return leftPos == -1? "" : s.substring(leftPos, rightPos + 1);
    }

    private boolean windowContainsT() {
        for (Character tChar : tCharsCnt.keySet()) {
            if (windowCharsCnt.getOrDefault(tChar, 0) < tCharsCnt.get(tChar)) {
                return false;
            }
        }
        return true;
    }

}
