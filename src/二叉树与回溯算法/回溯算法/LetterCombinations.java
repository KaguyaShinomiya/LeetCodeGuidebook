package 二叉树与回溯算法.回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class: LetterCombinations
 * 17. 电话号码的字母组合
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-26 01:04
 */
public class LetterCombinations {
    String[] buttons = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 解法1: 回溯算法
     *
     * @param digits 数字组合
     * @return
     */
    public List<String> letterCombinationsSolution1(String digits) {
        StringBuffer cur = new StringBuffer();
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) {
            return res;
        }
        backTrack(0, digits, res, cur);
        return res;
    }

    /**
     * @param pos 当前位于第几个按键上
     * @param digits 数字按键代表的字符串
     * @param res 结果
     * @param cur 当前已经拼接出来的字符串
     */
    private void backTrack(int pos, String digits, List<String> res, StringBuffer cur) {
        if (pos == digits.length()) {
            res.add(cur.toString());
            return;
        }
        char ch = digits.charAt(pos);
        String letters = buttons[ch - '1' - 1];
        for (int j = 0; j < letters.length(); j++) {
            cur.append(letters.charAt(j));
            backTrack(pos + 1, digits, res, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
