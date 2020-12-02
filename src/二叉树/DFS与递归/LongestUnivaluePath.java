package 二叉树.DFS与递归;

import utils.TreeNode;

/**
 * The Class: LongestUnivaluePath
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 *
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 示例 1:
 *
 * 输入:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 * 输出:
 *
 * 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-01 21:15
 */
public class LongestUnivaluePath {
    int longestPath = 0;
    public int longestUnivaluePath(TreeNode root) {
        longestPath = 0;
        longestPathFromCurr(root);
        return longestPath;
    }

    private int longestPathFromCurr(TreeNode curr) {
        if (curr == null) {
            return 0;
        }
        // 注意这里不能直接写成longestPathFromLeft=0，然后再在条件语句里进行递归；
        // 为了使递归能够覆盖到叶子节点，一定要在条件判断之前就进行递归调用，
        // 而不是直接为longestPathFromLeft赋值为0，满足条件了才进行递归；
        // 否则，只有当满足条件的时候才会进行递归，导致树没有遍历到叶子节点就直接返回
        int longestPathFromLeft = longestPathFromCurr(curr.left); // 记录从左子节点出发的最长路径
        int longestPathFromRight = longestPathFromCurr(curr.right); // 记录从右子节点出发的最长路径
        if (curr.left != null && curr.left.val == curr.val) {
            // 如果当前节点数值等同于左子节点数值，则从左子节点出发的最长路径到当前节点的距离为longestPathFromCurr(curr.left) + 1
            longestPathFromLeft++;
        } else {
            // 否则为0
            longestPathFromLeft = 0;
        }
        if (curr.right != null && curr.right.val == curr.val) {
            longestPathFromRight++;
        } else {
            longestPathFromRight = 0;
        }
        longestPath = Math.max(longestPath, longestPathFromLeft + longestPathFromRight);
        return Math.max(longestPathFromLeft, longestPathFromRight);

    }
}
