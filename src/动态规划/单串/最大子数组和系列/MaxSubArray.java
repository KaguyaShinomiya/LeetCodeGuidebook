package 动态规划.单串.最大子数组和系列;

/**
 * The Class: MaxSubArray
 * 53. 最大子序和
 * https://leetcode-cn.com/problems/maximum-subarray/
 * @author: Kaguya Y
 * @since: 2020-11-08 00:18
 */
public class MaxSubArray {

    /**
     * 解法1: 用数组dp记录以nums[i]为结尾的最大子序和
     *
     * @param nums 数组
     * @return .
     */
    public int maxSubArraySolution1(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxAns = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxAns = Math.max(dp[i], maxAns);
        }
        return maxAns;
    }
}
