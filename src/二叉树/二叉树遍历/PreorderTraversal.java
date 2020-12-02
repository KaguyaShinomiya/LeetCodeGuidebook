package 二叉树.二叉树遍历;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class: PreorderTraversal
 * 144. 二叉树的前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * @author: Kaguya Y
 * @since: 2020-11-29 13:41
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> treeStack = new LinkedList<>();
        TreeNode cur = root;
        treeStack.push(cur);
        while (!treeStack.isEmpty()) {
            cur = treeStack.pop();
            res.add(cur.val);
            if (cur.right != null) {
                treeStack.push(cur.right);
            }
            if (cur.left != null) {
                treeStack.push(cur.left);
            }
        }
        return res;
    }
}
