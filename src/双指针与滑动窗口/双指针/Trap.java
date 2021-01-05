package 双指针与滑动窗口.双指针;

/**
 * The Class: Trap
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 *  
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 *
 * 提示：
 *
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya yu
 * @since: 2021-01-05 18:57
 */
public class Trap {
    /**
     * 解法1: 双指针
     * 用两个指针分别指向数组最左边和最右边
     * 用两个变量leftHighest、rightHighest分别记录左侧的最高值、右侧的最高值
     * 解题的关键在于【水桶能盛多少水是由最短的板决定的】
     * 可以这么思考：
     * 如果当前leftHighest <= rightHighest，
     * 那么对于left柱形能够积累的水位而言：
     * （0）如果height[left] >= leftHeighest，那么left柱形无法积累到水位，且需要更新leftHighest；
     * （1）如果height[left] <= leftHeighest，
     * （1.1）如果left右侧存在一个柱形pos，满足height[pos] >= leftHighest；那么leftHighest所对应的柱形、pos所对应的柱形
     * 一定能围成一个水桶的两侧，且这个水坑的最高水位为leftHighest，left柱形能够积累到的水位为leftHighest - height[left]；
     * （1.2）如果left右侧除了rightHighest所代表的柱形之外、所有的柱形的高度都小于leftHighest；那么leftHighest所对应的柱形、rightHighest所代表的柱形
     * 一定能围成一个水桶的两侧，且这个水坑的最高水位为leftHighest，left柱形能够积累到的水位也为leftHighest - height[left]；
     *
     * 对于leftHighest > rightHighest，同样能够得出对称的结论
     *
     */
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        int leftHighest = height[left];
        int rightHighest = height[right];
        // 注意left = right时，也是可以积水的
        while (left <= right) {
            if (leftHighest <= rightHighest) {
                res += Math.max(leftHighest - height[left], 0);
                leftHighest = Math.max(leftHighest, height[left]);
                left++;
            } else {
                res += Math.max(rightHighest - height[right], 0);
                rightHighest = Math.max(rightHighest, height[right]);
                right--;
            }
        }
        return res;
    }
}
