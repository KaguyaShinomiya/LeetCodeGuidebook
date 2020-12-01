package 二叉树与回溯算法.二叉树.DFS与递归;

import utils.TreeNode;

/**
 * The Class: MaxPathSum
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-01 23:19
 */
public class MaxPathSum {
    /**
     * 解法1: 递归
     */
    int maxPathSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPathSum = Integer.MIN_VALUE;
        maxSingleEdgeSum(root);
        return maxPathSum;
    }

    /**
     * 记以节点curr为止的最大路径和为L(curr)
     * 则L(curr) = Math.max(L(curr.left), L(curr.right), 0) + curr.val
     * 记以节点curr为顶点的最大路径和为D(curr)
     * 则D(curr) = Math.max(L(curr.left), 0) + Math.max(L(curr.right), 0) + curr.val
     *
     * @param curr 当前节点
     * @return 从当前节点的某个子孙节点开始，到当前节点为止的最大路径和
     */
    private int maxSingleEdgeSum(TreeNode curr) {
        if (curr == null) {
            return 0;
        }
        // 计算以左右子节点为终点的最大路径和
        int maxSingleEdgeSumLeft = maxSingleEdgeSum(curr.left);
        int maxSingleEdgeSumRight = maxSingleEdgeSum(curr.right);
        // 以当前节点为顶点的最大路径和就是以左右节点为终点的最大路径和再加上当前节点的值
        maxPathSum = Math.max(maxPathSum, Math.max(maxSingleEdgeSumLeft, 0) + Math.max(maxSingleEdgeSumRight, 0) + curr.val);
        // 返回以当前节点为终点的最大路径和，用于递归
        return Math.max(Math.max(maxSingleEdgeSumRight, maxSingleEdgeSumLeft), 0) + curr.val;
    }
}
