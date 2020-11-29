package 二叉树与回溯算法.回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class: GenerateParenthesis
 * 22. 括号生成
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * @author: Kaguya Y
 * @since: 2020-11-24 21:19
 */
public class GenerateParenthesis {
    /**
     * 解法1: 回溯算法
     *
     * @param n .
     * @return .
     */
    public List<String> generateParenthesisSolution1(int n) {
        List<String> parenthesis = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        backTrack(n, parenthesis, sb, 0, 0);
        return parenthesis;
    }

    /**
     * @param n 最大括号对数
     * @param parenthesis 结果列表
     * @param sb 当前StringBuffer
     * @param open 已经使用的左括号数量
     * @param close 已经使用的右括号数量
     */
    private void backTrack(int n, List<String> parenthesis, StringBuffer sb, int open, int close) {
        if (sb.length() == 2 * n) {
            parenthesis.add(sb.toString());
            return;
        }
        if (open < n) {
            // 在分支中选择加入左括号
            sb.append("(");
            backTrack(n, parenthesis, sb, open + 1, close);
            // 在分支走到最底端、需要回溯时，需要把最后一个元素弹出
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(")");
            backTrack(n, parenthesis, sb, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 解法2: 深度优先搜索
     *
     * @param n .
     * @return .
     */
    public List<String> generateParenthesisSolution2(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, 0, 0, "", res);
        return res;
    }

    /**
     * @param n 括号对数
     * @param left 当前字符串左括号数量
     * @param right 当前字符串右括号数量
     * @param cur 当前字符串
     * @param res 结果列表
     */
    private void dfs(int n, int left, int right, String cur, List<String> res) {
        if (left == n && right == n) {
            res.add(cur);
            return;
        }
        // 如果右括号数量大于左括号数量，说明当前分支无效，直接返回
        if (right > left) {
            return;
        }
        if (left < n) {
            dfs(n, left + 1, right, cur + "(", res);
        }
        if (right < n) {
            dfs(n, left, right + 1, cur + ")", res);
        }
    }
}
