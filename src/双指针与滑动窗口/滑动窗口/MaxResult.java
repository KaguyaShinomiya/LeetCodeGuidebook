package 双指针与滑动窗口.滑动窗口;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * The Class: MaxResult
 * 1696. 跳跃游戏 VI
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。
 * 也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)] 包含 两个端点的任意位置。
 *
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 * 请你返回你能得到的 最大得分 。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,-1,-2,4,-7,3], k = 2
 * 输出：7
 * 解释：你可以选择子序列 [1,-1,4,3] （上面加粗的数字），和为 7 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-vi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-20 11:24
 */
public class MaxResult {

    /**
     * 本题的题解已经发布在leetcode网站上，详细请见：
     * https://leetcode-cn.com/problems/jump-game-vi/solution/tiao-yue-you-xi-cong-dong-tai-gui-hua-da-3hc3/
     *
     * 解法1: 暴力动态规划，直接超时
     * 我们用dp[i]表示以第i个元素结尾的最大得分，
     * 那么很明显，每次我们只需要找到dp[i - 1]、dp[i - 2]、...、dp[i - k]的最大值，然后加上当前元素的大小，就可以得到dp[i]。
     * 最后只要求出dp[nums.length - 1]即可
     *
     */
    public int maxResultSolution1(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            for (int j = Math.max(0, i - k); j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i] += nums[i];
        }
        return dp[nums.length - 1];
    }

    /**
     * 解法2: 结合239.滑动窗口最大值的做法，在索引i，将所有能够到达第i个元素的前面的元素放入滑动窗口
     * 使用双端队列windowIndices保存滑动窗口中的最大值索引的同时，把最大值索引的【候补】也装进来
     * dp[i] = 当前滑动窗口中的最大值 + nums[i]
     *
     */
    public int maxResultSolution2(int[] nums, int k) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        // 构造滑动窗口的索引所对应的队列，队首至队尾的索引依次增大，但对应dp数组中的值依次降低
        Deque<Integer> windowIndices = new LinkedList<>();
        for (int i = 1; i < nums.length; i++) {
            // 如果新的索引i所对应的元素dp[i - 1]大于队尾rear所对应的数组元素dp[rear]，就循环弹出队尾，直到新的元素i - 1能够成为队尾
            // 因为dp[rear] < dp[i - 1]且rear < i - 1，只要窗口继续向右移，dp[rear]就一定会被dp[i - 1]压在下面，不会成为窗口最大元素
            while (!windowIndices.isEmpty() && dp[i - 1] >= dp[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }
            windowIndices.offerLast(i - 1);
            if (windowIndices.peekFirst() < i - k) {
                windowIndices.pollFirst();
            }
            dp[i] = dp[windowIndices.peekFirst()] + nums[i];
        }
        return dp[nums.length - 1];
    }
}
