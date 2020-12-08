package 二叉树.DFS与递归;

import utils.TreeNode;

import java.util.HashMap;

/**
 * The Class: BuildTreeFromPreorderAndInorder
 * 105. 从前序与中序遍历序列构造二叉树
 * 剑指 Offer 07. 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya yu
 * @since: 2020-12-08 21:09
 */
public class BuildTreeFromPreorderAndInorder {
    private HashMap<Integer, Integer> inorderIndices;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        // 为了能够在中序遍历的列表中找到根节点的位置，需要通过哈希表记录值到中序遍历索引之间的关系
        inorderIndices = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndices.put(inorder[i], i);
        }
        return buildTreeHelper(preorder, inorder, 0, preorder.length - 1, 0, preorder.length - 1);
    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (inLeft > inRight) {
            return null;
        }
        // 每次递归时，前序遍历列表的第一个元素就是当前子树的根节点
        int rootVal = preorder[preLeft];
        TreeNode curr = new TreeNode(rootVal);
        // 根据当前子树根节点的值找到中序遍历列表中根节点的索引inRoot
        int inRoot = inorderIndices.get(rootVal);
        // 中序遍历中位于inLeft到inRoot之间的节点对应着当前子树的左子树，可以计算出左子树的节点数量
        int leftNums = inRoot - inLeft;
        // 显然，前序遍历列表中紧随第一个元素（当前子树根节点）之后的leftNums个节点就是当前节点的左子树
        // 中序遍历列表中inLeft到inRoot - 1之间的节点也对应着当前节点的左子树，所以可以利用这两个子数组递归寻找左子树的根节点
        curr.left = buildTreeHelper(preorder, inorder, preLeft + 1, preLeft + leftNums, inLeft, inRoot - 1);
        curr.right = buildTreeHelper(preorder, inorder, preLeft + leftNums + 1, preRight, inRoot + 1, inRight);
        return curr;
    }
}
