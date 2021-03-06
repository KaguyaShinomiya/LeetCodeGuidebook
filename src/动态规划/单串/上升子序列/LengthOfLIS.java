package 动态规划.单串.上升子序列;

/**
 * 300. 最长上升子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 */
public class LengthOfLIS {

    /**
     * 解法1 动态规划，利用数组记录以nums[i]为最后一位数的最长上升子序列长度
     *
     * @param nums
     * @return
     */
    public int lengthOfLISSolution1(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int maxans = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(dp[i], maxans);
        }
        
        return maxans;
    }

    /**
     * 解法2 动态规划+贪心+二分法
     *
     * @param nums
     * @return
     */
    public int lengthOfLISSolution2(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        int len = 1;
        dp[1] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > dp[len]) {
                len++;
                dp[len] = nums[i];
            }
            else {
                int right = len;
                int left = 1;
                int pos = 0;
                while (left <= right) {
                    // 用位运算计算均值
                    int mid = (left + right) >> 1;
                    if (dp[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
                dp[pos + 1] = nums[i];
            }
        }

        return len;
    }
}
