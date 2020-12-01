package 二叉树与回溯算法.二叉树.DFS与递归;

import utils.TreeNode;

/**
 * The Class: HasPathSum
 * 112. 路径总和
 * https://leetcode-cn.com/problems/path-sum/
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @author: Kaguya Y
 * @since: 2020-12-02 01:01
 */
public class HasPathSum {
    /**
     * 解法1: 递归
     * 拆解为子问题hasPathSum(root.left, sum - root.val)和hasPathSum(root.right, sum - root.val)即可
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
