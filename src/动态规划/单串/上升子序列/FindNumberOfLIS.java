package 动态规划.单串.上升子序列;

import java.util.Arrays;

/**
 * The Class: FindNumberOfLIS
 * 673.最长递增子序列的个数
 * https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/
 *
 * @author: Kaguya Y
 * @since: 2020-11-05 21:40
 */
public class FindNumberOfLIS {


    /**
     * 维护两个数组，一个为lengths数组，一个为counts数组，
     *
     * @param nums 数组
     * @return 数组内最长递增子序列的个数
     */
    public int findNumberOfLISSolution1(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int[] lengths = new int[nums.length]; //记录以nums[i]为结尾的最长递增子序列长度
        int[] counts = new int[nums.length]; //记录以nums[i]为结尾的最长递增子序列的数量
        Arrays.fill(lengths, 1);
        Arrays.fill(counts, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (lengths[j] >= lengths[i]) {
                        //以nums[i]为结尾的LIS长度必然比nums[j]结尾的LIS长度更长，需更新length[i]
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    } else if (lengths[j] + 1 == lengths[i]) {
                        //将这里的以nums[j]为结尾的LIS接到nums[i]前面，length[i]不需要更新，但counts[i]增加
                        counts[i] += counts[j];
                    }
                }
            }
        }

        int longest = lengths[nums.length - 1];
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (lengths[i] > longest) {
                longest = lengths[i];
                count = counts[i];
            } else if (lengths[i] == longest) {
                count += counts[i];
            }
        }
        return count;
    }

}
