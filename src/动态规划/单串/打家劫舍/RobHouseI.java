package 动态规划.单串.打家劫舍;

/**
 * The Class: RobHouseI
 * 198. 打家劫舍
 * https://leetcode-cn.com/problems/house-robber/
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * @author: Kaguya Y
 * @since: 2020-11-12 23:32
 */
public class RobHouseI {
    /**
     * 记dp[i]为截止到第i间房子能够得到的最大金额，
     * 如果抢第i间房子，则不能抢第i-1间房子，收益为dp[i - 2] + nums[i]；
     * 如果不抢第i-1间房子，则收益为dp[i - 1]
     * 可以用两个变量和一个临时变量代替dp[i - 2]到dp[i]
     *
     * @param nums .
     * @return .
     */
    public int robISolution1(int[] nums) {
        if (nums.length == 0) return 0;
        int temp = 0;
        int pre = 0;
        int cur = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = cur;
            cur = Math.max(cur, pre + nums[i]);
            pre = temp;
        }
        return cur;
    }
}
