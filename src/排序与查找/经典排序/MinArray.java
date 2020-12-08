package 排序与查找.经典排序;

/**
 * The Class: MinArray
 * 剑指 Offer 11. 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-08 22:41
 */
public class MinArray {
    /**
     * 解法1: 二分查找
     */
    public int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果mid所对应的元素大于right的元素，说明mid一定位于第一个上升序列，交界点一定在mid右边
            if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else if (numbers[mid] > numbers[right]) {
                // 如果mid所对应的元素小于right的元素，说明mid一定位于第二个上升序列，交界点一定在mid或者mid左边
                right = mid;
            } else {
                // 如果mid和right元素大小相等，那么我们无法判断mid位于哪个上升序列，但是可以把right向左移，因为right一定不是交界点
                // 哪怕right是交界点，由于mid和right元素大小相等，
                right--;
            }
        }
        return numbers[left];
    }
}
