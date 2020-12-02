package 二叉树.DFS与递归;

import utils.TreeNode;

/**
 * The Class: MergeTrees
 * 617. 合并二叉树
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-11-30 23:42
 */
public class MergeTrees {
    /**
     * 解法1: DFS
     */
    public TreeNode mergeTreesSolution1(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        } else if (t2 == null) {
            return t1;
        }
        // 注意，不需要单独讨论t1=null且t2=null的情形
        TreeNode t3 = new TreeNode(t1.val + t2.val);
        t3.left = mergeTreesSolution1(t1.left, t2.left);
        t3.right = mergeTreesSolution1(t1.right, t2.right);
        return t3;
    }

}
