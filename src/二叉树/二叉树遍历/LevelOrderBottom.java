package 二叉树.二叉树遍历;

import utils.TreeNode;

import java.util.*;

/**
 * The Class: LevelOrderBottom
 * 107. 二叉树的层次遍历 II
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * @author: Kaguya Y
 * @since: 2020-11-30 22:56
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> thisLevel = new LinkedList<>();
        thisLevel.offer(root);
        int thisLevelSize = 1;
        while (!thisLevel.isEmpty()) {
            List<Integer> thisLevelVals = new LinkedList<>();
            for (int i = 0; i < thisLevelSize; i++) {
                TreeNode curr = thisLevel.poll();
                thisLevelVals.add(curr.val);
                if (curr.left != null) {
                    thisLevel.offer(curr.left);
                }
                if (curr.right != null) {
                    thisLevel.offer(curr.right);
                }
            }
            thisLevelSize = thisLevel.size();
            res.add(0, thisLevelVals);
        }
        return res;
    }
}
