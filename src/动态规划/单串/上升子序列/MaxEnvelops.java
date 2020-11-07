package 动态规划.单串.上升子序列;

import java.util.Arrays;

/**
 * The Class: MaxEnvelops
 * 354.俄罗斯套娃信封问题
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * @author: Kaguya Y
 * @since: 2020-11-07 18:49
 */
public class MaxEnvelops {

    /**
     * 按第一个元素升序排序，第一个元素相同时按第二个元素降序排序，因为第一个元素相同时无法嵌套
     * 随后，计算第二个元素组成的数组的LIS长度
     * @param envelopes 信封数组
     * @return 信封套娃数量
     */
    public int maxEnvelopsSolution1(int[][] envelopes) {

        if (envelopes.length <= 1) {
            return envelopes.length;
        }
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o2[1], o1[1]);
            } else {
                return Integer.compare(o1[0], o2[0]);
            }
        }); //按第一个元素升序排序，第一个元素相同时按第二个元素降序排序，因为第一个元素相同时无法嵌套
        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }

        return lengthOfLISSolution2(heights);

    }

    /**
     * 动态规划+贪心+二分法
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
            if (nums[i] > dp[len - 1]) {
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
