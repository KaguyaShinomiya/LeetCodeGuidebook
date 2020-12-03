package 数组与字符串.字符串匹配;

/**
 * The Class: LongestCommonPrefix
 * 14. 最长公共前缀
 *
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。
 说明:

 所有输入只包含小写字母 a-z 。
 *
 * @author: Kaguya Y
 * @since: 2020-12-03 21:44
 */
public class LongestCommonPrefix {
    /**
     * 解法1: 纵向比较，每次比较字符串的第i个字符是否相同
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int m = 0;
        StringBuilder prefix = new StringBuilder();
        while (true) {
            if (m >= strs[0].length()) {
                return prefix.toString();
            }
            char c = strs[0].charAt(m);
            for (int i = 0; i < strs.length; i++) {
                if (m >= strs[i].length() || strs[i].charAt(m) != c) {
                    return prefix.toString();
                }
            }
            prefix.append(c);
            m++;
        }
    }
}
