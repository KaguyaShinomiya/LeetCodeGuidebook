package DFS与回溯算法.回溯算法;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class: RestoreIpAddress
 * 93. 复原IP地址
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-25 21:29
 */
public class RestoreIpAddress {
    List<String> res = new ArrayList<>();
    static final int MAX_INTERVALS = 4;
    String[] segs = new String[4];

    /**
     * 解法1: DFS
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddressSolution1(String s) {
        dfs(s, 0, 0);
        return res;
    }

    /**
     * @param s 字符串
     * @param start 当前递归的起始索引
     * @param intervals 已经产生的分段数量
     */
    private void dfs(String s, int start, int intervals) {
        if (intervals == MAX_INTERVALS && start == s.length()) {
            // 如果已经分割为4段且起始标签指向字符串的末尾，说明已经完成分割
            StringBuffer buffer = new StringBuffer();
            for (String seg : segs) {
                buffer.append(seg);
                buffer.append(".");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            res.add(buffer.toString());
            return;
        }
        if (intervals == MAX_INTERVALS || start == s.length()) {
            // 如果有一个条件不满足，也需要在这里返回
            return;
        }
        for (int end = start; end < s.length(); end++) {
            // 选择当前分支下的子节点
            String cur = s.substring(start, end + 1);
            // 校验目前分割出的字符串是否合规
            if ((cur.startsWith("0") && end > start) || Integer.parseInt(cur) > 0xFF) {
                break;
            }
            segs[intervals] = cur;
            dfs(s, end + 1, intervals + 1);
        }
    }

    private Deque<String> paths = new LinkedList<>();

    /**
     * 解法2: 回溯算法
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddressSolution2(String s) {
        backTrack(s, 0, 0);
        return res;
    }

    private void backTrack(String s, int start, int intervals) {
        if (intervals == MAX_INTERVALS && start == s.length()) {
            // 如果已经分割为4段且起始标签指向字符串的末尾，说明已经完成分割
            res.add(String.join(".", paths));
            return;
        }
        if (intervals == MAX_INTERVALS || start == s.length()) {
            // 如果有一个条件不满足，也需要在这里返回
            return;
        }
        for (int end = start; end < s.length(); end++) {
            String curr = s.substring(start, end + 1);
            if ((curr.startsWith("0") && end > start) || Integer.parseInt(curr) > 0xFF) {
                break;
            }
            paths.offerLast(curr);
            backTrack(s, end + 1, intervals + 1);
            paths.removeLast();
        }
    }

}
