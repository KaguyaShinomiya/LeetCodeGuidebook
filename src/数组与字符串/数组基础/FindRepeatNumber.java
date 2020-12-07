package 数组与字符串.数组基础;

/**
 * The Class: FindRepeatNumber
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-08 00:14
 */
public class FindRepeatNumber {
    /**
     * 解法1: 通过修改原数组实现O(1)空间复杂度
     * 每次交换都必然会使一个元素m满足nums[m]=m，故最多需要进行n次交换，时间复杂度为O(n)
     *
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //0、记nums[i]=m，每次如果nums[i]!=i就将i和m处的元素进行交换
            while (nums[i] != i) {
                // 2、如果nums[m]=nums[i]，事实上就意味着这个元素是重复的
                if (nums[nums[i]] == nums[i]) {
                    return nums[i];
                } else {
                    // 1、那么每次交换，都会使第m位的元素满足nums[m]=m
                    swap(nums, i, nums[i]);
                }
            }
        }
        return 0;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
