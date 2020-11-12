package 动态规划.单串.打家劫舍;

import utils.TreeNode;

/**
 * The Class: RobHouseIII
 * 337. 打家劫舍 III
 * https://leetcode-cn.com/problems/house-robber-iii/
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * @author: Kaguya Y
 * @since: 2020-11-12 23:46
 */
public class RobHouseIII {
    /**
     * 每次用二维数组记录对于从所有叶子节点到当前节点抢和不抢的最大收益
     * 显然不抢该根节点，则最大收益就是左右节点的最大收益之和
     * 抢该节点，则最大收益就是不抢左节点的最大收益加上不抢右节点的最大收益再加上当前节点的收益
     *
     * @param root .
     * @return .
     */
    public int robSolution1(TreeNode root) {
        if (root == null) return 0;
        int[] dp = robHelper(root);
        return Math.max(dp[0], dp[1]);
    }

    private int[] robHelper(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] dpRight = robHelper(root.right);
        int[] dpLeft = robHelper(root.left);
        int[] dp = new int[2];
        dp[0] = Math.max(dpRight[0], dpRight[1]) + Math.max(dpLeft[0], dpLeft[1]);
        dp[1] = root.val + dpLeft[0] + dpRight[0];
        return dp;
    }
}
