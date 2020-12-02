package 二叉树.二叉树遍历;

import utils.TreeNode;

import java.util.*;

/**
 * The Class: PostorderTraversal
 * 145. 二叉树的后序遍历
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-29 15:51
 */
public class PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> treeStack = new LinkedList<>();
        Set<TreeNode> visitedNodes = new HashSet<>(); // 记录已经经过的节点
        TreeNode cur = root;
        while (cur != null || !treeStack.isEmpty()) {
            // 3、只有当前节点已经为null，且栈顶上的节点已经被经过一次，才可以弹出并加入到结果列表
            if (cur == null && visitedNodes.contains(treeStack.peek())) {
                res.add(treeStack.pop().val);
            } else if (cur == null) {
                // 2、如果当前节点已经走到了尽头，此时栈顶节点是当前节点的父节点，但此时需要先走完右侧的子树
                visitedNodes.add(treeStack.peek());
                cur = treeStack.peek().right;
            } else {
                // 1、先把左孩子节点一直加到栈中
                treeStack.push(cur);
                cur = cur.left;
            }
        }
        return res;
    }

}
