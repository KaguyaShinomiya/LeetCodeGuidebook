package 二叉树与回溯算法.二叉树.其他二叉树;

import utils.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The Class: Flatten
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-01 22:52
 */
public class Flatten {
    /**
     * 解法1: 通过层次遍历生成链表
     */
    public void flattenSolution1(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> treeStack = new LinkedList<>();
        TreeNode prev = root;
        if (root.right != null) {
            treeStack.push(root.right);
        }
        if (root.left != null) {
            treeStack.push(root.left);
        }
        while (!treeStack.isEmpty()) {
            TreeNode curr = treeStack.pop();
            prev.right = curr;
            prev.left = null;
            if (curr.right != null) {
                treeStack.push(curr.right);
            }
            if (curr.left != null) {
                treeStack.push(curr.left);
            }
            prev = curr;
        }
    }

    /**
     * 解法2: 递归
     */
    public void flattenSolution2(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenSolution2(root.left); // 对左子树进行递归，将左子树变成链表
        TreeNode tempRight = root.right; // 因为左子树就要变成当前节点的右子树了，需要用临时变量保留右子节点
        root.right = root.left;
        root.left = null;
        // 把递归后的左子树形成的链表接到当前节点，并一直遍历到左子树形成的链表的最底端
        while (root.right != null) {
            root = root.right;
        }
        // 将右子树变形形成链表，然后接到左子树形成的链表的最低端的下面
        flattenSolution2(tempRight);
        root.right = tempRight;
    }

    /**
     * 解法3:
     * 如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。
     * 该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。
     * 因此，问题转化成寻找当前节点的前驱节点。
     * 具体做法是：
     * 1、对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，
     * 2、将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。
     * 3、对当前节点处理结束后，继续处理链表中的下一个节点。
     *
     */
    public void flattenSolution3(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }


}
