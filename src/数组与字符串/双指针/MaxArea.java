package 数组与字符串.双指针;

/**
 * The Class: MaxArea
 * 11. 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: Kaguya Y
 * @since: 2020-12-03 22:40
 */
public class MaxArea {
    public int maxArea(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftHighest = height[left];
        int rightHighest = height[right];
        int maxArea = Math.min(leftHighest, rightHighest) * (right - left);
        while (left < right) {
            if (leftHighest < rightHighest) {
                while (left < right && height[left] < leftHighest) {
                    left++;
                }
                if (left >= right) {
                    break;
                }
                leftHighest = height[left];
                maxArea = Math.max(maxArea, Math.min(leftHighest, rightHighest) * (right - left));
            } else {
                while (left < right && height[right] < rightHighest) {
                    right--;
                }
                if (left >= right) {
                    break;
                }
                rightHighest = height[right];
                maxArea = Math.max(maxArea, Math.min(leftHighest, rightHighest) * (right - left));
            }
        }
        return maxArea;

    }
}
