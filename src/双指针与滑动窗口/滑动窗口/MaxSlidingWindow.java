package 双指针与滑动窗口.滑动窗口;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The Class: MaxSlidingWindow
 * 239. 滑动窗口最大值
 *
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-04 00:33
 */
public class MaxSlidingWindow {
    private Deque<Integer> windowIndices = new LinkedList<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        if (N == 0 || k == 0) {
            return new int[0];
        }
        windowIndices = new LinkedList<>();
        // 构造滑动窗口的索引所对应的队列，队首至队尾的索引依次增大，但数组中的值依次降低

        int[] res = new int[N - k + 1];
        for (int i = 0; i < k - 1; i++) {
            // 如果新的索引i所对应的元素nums[i]大于队尾j所对应的数组元素nums[j]，就循环弹出队尾，直到新的元素i能够成为队尾
            // 因为nums[j] < nums[i]且j < i，只要窗口继续向右移，nums[j]就一定会被nums[i]压在下面，不会成为窗口最大元素
            while (!windowIndices.isEmpty() && nums[i] >= nums[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }
            windowIndices.offerLast(i);
        }
        for (int i = k - 1; i < N; i++) {
            // 如果新的索引i所对应的元素nums[i]大于队尾j所对应的数组元素nums[j]，就循环弹出队尾，直到新的元素i能够成为队尾
            // 因为nums[j] < nums[i]且j < i，只要窗口继续向右移，nums[j]就一定会被nums[i]压在下面，不会成为窗口最大元素
            while (!windowIndices.isEmpty() && nums[i] >= nums[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }
            windowIndices.offerLast(i);
            if (windowIndices.peekFirst() < i - k + 1) {
                windowIndices.pollFirst();
            }
            res[i - k + 1] = nums[windowIndices.peekFirst()];
            if (i >= k - 1) {
                if (windowIndices.peekFirst() < i - k + 1) {
                    windowIndices.pollFirst();
                }
                res[i - k + 1] = nums[windowIndices.peekFirst()];
            }
        }
        return res;
    }
}
