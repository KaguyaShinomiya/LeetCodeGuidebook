package 二叉树.DFS与递归;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class: IsSubStructure
 * 剑指 Offer 26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 *
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * 示例 2：
 *
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 * 限制：
 *
 * 0 <= 节点个数 <= 10000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-10 00:46
 */
public class IsSubStructure {

    /**
     * 解法1: 递归
     * 判断A是否【包含】B，需要遍历A的各个节点，然后比较A的各个节点为根节点的子树是否能与B这棵树【匹配】
     *
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // isMatch(A, B)只能告诉我们当前的A节点为根节点的子树是否能够【匹配】B
        // 如果不匹配的话，我们还可以继续看A节点的左节点A.left为根节点的子树是否【包含】B、A的右节点A.right为根节点的子树是否【包含】B
        // 注意我们这里只需要左子树、右子树能够【包含】而非严格【匹配】B，所以调用了isSubStructure()而非isMatch()
        return (A != null && B != null) && (isMatch(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    /**
     * 判断以A节点为根节点的子树，能否【匹配】以B节点为根点的子树
     * 匹配指B子树上各个子节点的值都能在A子树的相同位置上找到
     */
    private boolean isMatch(TreeNode A, TreeNode B) {
        // 如果此时B节点为空，不论此时A的子树所对应的节点是否有值，在当前分支下B都是包含于A的
        if (B == null) {
            return true;
        }
        // 如果A节点为空、B节点不为空，或者二者值不相等，则此时A树无法与B树匹配
        if (A == null || A.val != B.val) {
            return false;
        }
        return isMatch(A.left, B.left) && isMatch(A.right, B.right);
    }




}
