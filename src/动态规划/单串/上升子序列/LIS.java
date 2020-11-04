package 动态规划.单串.上升子序列;

/**
 * 最长上升子序列LIS
 */
public class LIS {

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
        int[] dp = new int[nums.length];
        int len = 1;
        dp[0] = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > dp[len - 1]) {
                dp[len - 1] = nums[i];
                len++;
            }
            else {
                int right = len - 1;
                int left = 0;
                int pos = 0;
                while (left <= right) {
                    //用位运算计算均值
                    int mid = (left + right) >> 1;
                    if (dp[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    }
                    else {
                        right = mid - 1;
                    }
                }
                dp[pos] = nums[i];
            }
        }

        return len;
    }
}
