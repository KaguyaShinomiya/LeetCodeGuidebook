package 二叉树.DFS与递归;

import utils.TreeNode;

/**
 * The Class: LowestCommonAncestor
 * 236. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-01 23:48
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 0、每次递归时，如果当前节点为空、返回空值；如果当前节点为p或q，就返回p或q
        // 事实上每次递归时我们就是把当前节点下的子树上所存在的p和q逐层向上返回
        // 如果返回值不为空值就说明当前节点下的子树中包含p或q
        if (root == null || root == p || root == q) {
            return root;
        }
        // 1、递归查找左子树下的p或q
        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        // 2、递归查找右子树下的p或q
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);
        // 3、如果当前节点的左子节点和右子节点各有p或q，则当前节点必然是最低公共祖先
        // 因为对于而言最低公共祖先的祖先节点而言，一侧子树有该最低公共祖先和p、q，另一侧则必然没有p或q
        if (leftAncestor != null && rightAncestor != null) {
            return root;
        }
        // 4、如果当前节点只有一边有p或q、另一个节点只有null，那么我们只返回有值的那个节点
        return leftAncestor != null? leftAncestor : rightAncestor;
    }
}
