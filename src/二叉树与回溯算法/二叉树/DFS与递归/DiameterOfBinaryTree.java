package 二叉树与回溯算法.二叉树.DFS与递归;

import utils.TreeNode;

/**
 * The Class: DiameterOfBinaryTree
 * 543. 二叉树的直径
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * @author: Kaguya Y
 * @since: 2020-11-30 23:29
 */
public class DiameterOfBinaryTree {
    /**
     * 解法1: 定义节点深度为左节点深度、右节点深度的最大值加1，则经过每个节点的最长直径就是左节点深度加上右节点深度
     */
    private int maxDiameter = 0;
    public int diameterOfBinaryTreeSolution1(TreeNode root) {
        maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(TreeNode curr) {
        if (curr == null) {
            return 0;
        }
        int left = maxDepth(curr.left);
        int right = maxDepth(curr.right);
        maxDiameter = Math.max(left + right, maxDiameter);
        // 2、则经过每个节点的最大长度就是左节点深度加上右节点深度
        return Math.max(left, right) + 1;
        // 1、定义节点深度为左节点深度、右节点深度的最大值加1,
    }

}
