package 动态规划.背包问题;

/**
 * The Class: FindTargetSumWays
 * 494. 目标和
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 *
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 *  
 *
 * 示例：
 *
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * 一共有5种方法让最终目标和为3。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/ga4o2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-02 22:06
 */
public class FindTargetSumWays {
    /**
     * 记数组所有元素总和为numSum，减号后的元素总和为target，则
     * numSum - 2 * target = S。
     * 问题转化为背包问题：从nums数组中挑选若干元素构成目标数组，使之总和为target，求方法总数。
     * 用dp[i][j]记录前i个元素总和为j的方法数。
     * 转移方程为dp[i][j] = nums[i] > j ? dp[i - 1][j] : dp[i - 1][j] + dp[i - 1][j - nums[i]]
     * 注意边界条件，dp[i][0]至少为1，对应着所有数都不放到背包里。
     * 另外，所有的0都有两种取法，无论是否放入目标数组都不影响总和，所以当前元素为0时需要单独讨论
     */
    public int findTargetSumWays(int[] nums, int S) {
        int numSum = 0;
        for (int num : nums) {
            numSum += num;
        }
        int target = (numSum - S) / 2;
        if (target < 0 || (numSum - S) % 2 != 0) {
            return 0;
        }
        int[][] dp = new int[nums.length][2001];
        dp[0][0] = nums[0] == 0? 2 : 1;
        for (int j = 1; j < 1001; j++) {
            dp[0][j] = nums[0] == j? 1 : 0;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 1001; j++) {
                // 当元素为0时，0可以被加入到目标数组中，也可以不被加入到目标数组中，因此方法数需要乘以2
                if (nums[i] == 0) {
                    dp[i][j] = dp[i - 1][j] * 2;
                    continue;
                }
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][target];
    }
}
