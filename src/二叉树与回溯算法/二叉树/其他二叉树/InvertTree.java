package 二叉树与回溯算法.二叉树.其他二叉树;

import utils.TreeNode;

/**
 * The Class: InvertTree
 * 226. 翻转二叉树
 * https://leetcode-cn.com/problems/invert-binary-tree/
 *
 * 翻转一棵二叉树。
 *
 * @author: Kaguya Y
 * @since: 2020-11-30 23:22
 */
public class InvertTree {
    /**
     * 解法1: 先交换当前节点下的左右子节点，接下来依次对左子节点和右子节点进行递归，逐层向下
     */
    public TreeNode invertTreeSolution1(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tempLeft = root.left;
        root.left = root.right;
        root.right = tempLeft;
        invertTreeSolution1(root.left);
        invertTreeSolution1(root.right);
        return root;
    }

    /**
     * 解法2: 先对左子树和右子树进行递归，等下面的节点交换完成后，再对当前层的节点交换左右子节点
     */
    public TreeNode invertTreeSolution2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTreeSolution2(root.left);
        TreeNode right = invertTreeSolution2(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


}
