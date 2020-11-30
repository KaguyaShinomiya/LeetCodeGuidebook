package 二叉树与回溯算法.二叉树.二叉树遍历;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Class: LevelOrderTraversal
 * 102. 二叉树的层序遍历
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * @author: Kaguya Y
 * @since: 2020-11-30 22:24
 */
public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> thisLevel = new LinkedList<>();
        thisLevel.offer(root);
        int thisLevelSize = 1;
        while (!thisLevel.isEmpty()) {
            List<Integer> thisLevelVals = new ArrayList<>();
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
            res.add(thisLevelVals);
        }
        return res;
    }
}
