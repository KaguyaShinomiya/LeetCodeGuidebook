package 动态规划.单串.打家劫舍;

import java.util.Arrays;

/**
 * The Class: RobHouseII
 * 213. 打家劫舍 II
 * https://leetcode-cn.com/problems/house-robber-ii/
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
 *
 * @author: Kaguya Y
 * @since: 2020-11-12 23:41
 */
public class RobHouseII {
    /**
     * 视为两排单列房间，一个从0到N-1，一个从1到N
     *
     * @param nums .
     * @return .
     */
    public int robSolution1(int[] nums) {
        return Math.max(robHelper(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                robHelper(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    private int robHelper(int[] nums) {
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
