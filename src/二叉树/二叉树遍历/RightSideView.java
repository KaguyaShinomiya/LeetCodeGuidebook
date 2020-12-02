package 二叉树.二叉树遍历;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Class: RightSideView
 * 199. 二叉树的右视图
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-01 23:17
 */
public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightNodes = new ArrayList<>();
        if (root == null) {
            return rightNodes;
        }
        Queue<TreeNode> thisLevel = new LinkedList<>();
        thisLevel.offer(root);
        TreeNode curr = root;
        int thisLevelSize = 1;
        while (!thisLevel.isEmpty()) {
            for (int i = 0; i < thisLevelSize; i++) {
                curr = thisLevel.poll();
                if (curr.left != null) {
                    thisLevel.offer(curr.left);
                }
                if (curr.right != null) {
                    thisLevel.offer(curr.right);
                }
            }
            rightNodes.add(curr.val);
            thisLevelSize = thisLevel.size();
        }
        return rightNodes;
    }
}
