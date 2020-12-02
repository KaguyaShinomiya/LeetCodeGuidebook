package 二叉树.二叉树遍历;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class: InorderTraversal
 * 94. 二叉树的中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
 *
 * @author: Kaguya Y
 * @since: 2020-11-29 15:04
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> treeStack = new LinkedList<>();
        TreeNode cur = root;
        treeStack.push(cur);
        while (treeStack.isEmpty() || cur != null) {
            while (cur != null) {
                treeStack.push(cur);
                cur = cur.left;
            }
            cur = treeStack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
