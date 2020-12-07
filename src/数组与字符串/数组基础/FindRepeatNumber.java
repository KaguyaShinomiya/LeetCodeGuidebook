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
 * 限制：
 *
 * 2 <= n <= 100000
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
    public int findRepeatNumberSolution1(int[] nums) {
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

    /**
     * 解法2: 二分查找
     * 在不修改原有数组的同时实现O(1)额外空间
     * 每次统计数组中大于等于left小于等于mid的元素个数
     * 如果left到mid之间的元素个数大于left到mid这两个数的差额，说明重复元素的大小就在left到mid之间
     *
     */
    public int findRepeatNumberSolution2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = countRange(nums, left, mid);
            if (left == right) {
                return left;
            }
            if (count > mid - left + 1) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return 0;
    }

    /**
     * 对于数组nums，统计大小在left和right之间的元素数量
     *
     */
    private int countRange(int[] nums, int left, int right) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= left && nums[i] <= right) {
                count++;
            }
        }
        return count;
    }

}
