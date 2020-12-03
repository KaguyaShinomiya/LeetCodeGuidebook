package 数组与字符串.数组基础;

/**
 * The Class: FirstMissingPositive
 * 41. 缺失的第一个正数
 *
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *  
 *
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-03 23:12
 */
public class FirstMissingPositive {
    /**
     * 解法1: "哈希表"
     *
     * 要求常数额外空间时，往往可以考虑修改原数组
     * 这里我们可以遍历一遍数组，如果有正数，就把对应的索引下的元素设为负数，
     * 比如说数组中有3，就把第3个元素标记为负数；数组中有5，就把第5个元素标记为负数
     * 这样就相当于标记出了负数，这样只要看没被标记过的元素所在的索引，就可以知道缺少哪个正数了
     * 但是、这样的标记会和数组中原有的负数或0重合，所以需要先把原有的负数或0设为N + 1；
     * 除此之外，还需要考虑在标记之前把当前遍历到的正数减去1作为索引，把1-N+1 mapping到 0 - N-1的索引范围
     *
     */
    public int firstMissingPositiveSolution1(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            nums[i] = nums[i] <= 0? N + 1 : nums[i];
        }
        for (int i = 0; i < N; i++) {
            int num = Math.abs(nums[i]);
            if (num <= N) {
                nums[num - 1] = - Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < N; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return N + 1;
    }
}
