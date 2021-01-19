package 数组与字符串.前缀和;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

/**
 * The Class: SubarraySum
 * 560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 *
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya yu
 * @since: 2021-01-20 00:13
 */
public class SubarraySum {
    /**
     * 解法1: 前缀和 + 哈希表
     * 计算数组nums[:i]的前缀和prefixSum，可以把连续子数组的求和转化为两个前缀和之间的差
     * 用一个哈希表prefixSumMap统计不同前缀和大小出现的次数；
     * 对于当前元素i的前缀和prefixSum，以当前元素i为终点、且和为k的连续子数组的数量；事实上就是前缀和prefixSum - k的数量；
     * 即prefixSumMap.get(prefixSum - k)；
     * 需要注意的是前缀和prefixSum - k需要排除掉元素i后面的那些元素，因此我们是在从前向后遍历nums、计算前缀和的同时检查哈希表中prefixSum - k的数量；
     * 不能先遍历一遍nums统计prefixSumMap，再遍历一遍nums、计算子数组个数，因为这样的话prefixSumMap已经包含了元素i后面的元素的前缀和；
     * 另外需要注意将前缀和为0的出现次数为1，初始化到prefixSumMap中
     */
    public int subarraySumSolution1(int[] nums, int k) {
        // 将前缀和为0的出现次数为1，初始化到prefixSumMap中
        Map<Integer, Integer> prefixSumMap = new HashMap<>(){{put(0, 1);}};
        int prefixSum = 0;
        int count = 0;
        for (int num : nums) {
            prefixSum += num;
            count += prefixSumMap.getOrDefault(prefixSum - k, 0);
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
