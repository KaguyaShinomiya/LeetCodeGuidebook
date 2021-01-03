package 数组与字符串.前缀和;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Class: WaysToSplit
 * 5643. 将数组分成三个子数组的方案数
 * 我们称一个分割整数数组的方案是 好的 ，当它满足：
 *
 * 数组被分成三个 非空 连续子数组，从左至右分别命名为 left ， mid ， right 。
 * left 中元素和小于等于 mid 中元素和，mid 中元素和小于等于 right 中元素和。
 * 给你一个 非负 整数数组 nums ，请你返回 好的 分割 nums 方案数目。由于答案可能会很大，请你将结果对 109 + 7 取余后返回。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1]
 * 输出：1
 * 解释：唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。
 * 示例 2：
 *
 * 输入：nums = [1,2,2,2,5,0]
 * 输出：3
 * 解释：nums 总共有 3 种好的分割方案：
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * 示例 3：
 *
 * 输入：nums = [3,2,1]
 * 输出：0
 * 解释：没有好的分割方案。
 *  
 *
 * 提示：
 *
 * 3 <= nums.length <= 105
 * 0 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ways-to-split-array-into-three-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya yu
 * @since: 2021-01-03 11:49
 */
public class WaysToSplit {
    private static final int MOD = 1000000007;
    /**
     * 解法1: 前缀和+二分查找
     * 先计算前缀和数组prefixSum，
     * 然后依次以1,2,3...N-1作为left数组和mid数组的边界i（i是left数组中最后一个元素的位置）；
     * 固定了left数组和mid数组的边界i之后，需要判断mid数组和right数组的边界midRight(midRight是mid数组中最后一个元素的位置)；
     * 显然mid数组和right数组的边界midRight需要满足：
     * （1）prefixSum[midRight] - prefixSum[i] >= prefixSum[i]，即mid数组元素和大于等于left数组元素和；
     * （2）prefixSum[N - 1] - prefixSum[midRight] >= prefixSum[midRight] - prefixSum[i]，即right数组元素和大于等于mid数组元素和；
     * 显然midRight越往左，mid数组之和越小，right数组之和越大.
     * 所以我们先用二分法找到midRight的下边界，即最小的能够满足条件（1）的midRight，
     * 随后用二分法找到midRight的上边界，即最大的能够满足条件（2）的midRight，
     * 则固定了left数组和mid数组的边界i之后，我们一共有midRight上边界 - midRight下边界 + 1种分割方法
     *
     */
    public int waysToSplitSolution1(int[] nums) {
        int N = nums.length;
        int[] prefixSum = new int[N];
        prefixSum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        long waysCnt = 0;
        for (int i = 0; i < N; i++) {
            // left和mid的边界为i，如果前i个数字的前缀和大于数组总和的1/3，则一定不满足要求
            if (prefixSum[i] * 3 > prefixSum[N - 1]) {
                break;
            }
            // 接下来首先找到mid与Right的边界midRight的下界，需要满足条件（1）
            int lowerLeft = i + 1;
            int lowerRight = N - 2;
            while (lowerLeft <= lowerRight) {
                int mid = lowerLeft + (lowerRight - lowerLeft) / 2;
                if (prefixSum[mid] < 2 * prefixSum[i]) {
                    lowerLeft = mid + 1;
                } else {
                    lowerRight = mid - 1;
                }
            }
            // 注意经过上面的二分查找之后，符合条件的midRight的下界为lowerLeft；
            // 接下来找到midRight的上界，需要满足条件(2)
            int upperLeft = i + 1;
            int upperRight = N - 2;
            while (upperLeft <= upperRight) {
                int mid = upperLeft + (upperRight - upperLeft) / 2;
                if (prefixSum[N - 1] - prefixSum[mid] < prefixSum[mid] - prefixSum[i]) {
                    upperRight = mid - 1;
                } else {
                    upperLeft = mid + 1;
                }
            }
            // 注意符合条件的midRight的上界为upperLeft - 1
            waysCnt += (upperLeft - lowerLeft) % MOD;
        }
        return (int) waysCnt % MOD;
    }

    /**
     * 解法2: 前缀和+三指针
     * 我们注意到解法1中mid数组和right数组的边界midRight(midRight是mid数组中最后一个元素的位置)的上界和下界都是单调递增的
     * 这就意味着我们可以通过三指针解决这个问题：
     * 第一个指针和解法1一样，指向left数组中的最后一个元素所在的位置i；
     * 固定了第一个指针之后，我们用lowerMidRight和higherMidRight两个指针用于寻找midRight的下边界和上边界；
     * 这次通过【移动lowerMidRight指针】寻找满足最小的满足条件（1）的元素，作为midRight下边界；
     * 通过【移动higherMidRight指针】寻找满足最大的满足条件（2）的元素，作为midRight上边界；
     * 则固定了left数组和mid数组的边界i之后，我们一共有midRight上边界 - midRight下边界 + 1种分割方法
     *
     */
    public int waysToSplitSolution2(int[] nums) {
        int N = nums.length;
        int[] prefixSum = new int[N];
        prefixSum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        long waysCnt = 0;
        int lowerMidRight = 0;
        int higherMidRight = 0;
        for (int i = 0; i < N; i++) {
            // left和mid的边界为i，如果前i个数字的前缀和大于数组总和的1/3，则一定不满足要求
            if (prefixSum[i] * 3 > prefixSum[N - 1]) {
                break;
            }
            lowerMidRight = Math.max(lowerMidRight, i + 1);
            higherMidRight = Math.max(higherMidRight, i + 1);
            // 一直向右移动指针直到出现最小的满足条件（1）的元素，作为midRight的下边界
            while (lowerMidRight < N - 1 && prefixSum[lowerMidRight] < 2 * prefixSum[i]) {
                lowerMidRight++;
            }
            // 一直向右移动指针直到找到最大的满足条件（2）的元素，作为midRight的上边界
            while (higherMidRight < N - 1 && prefixSum[N - 1] - prefixSum[higherMidRight] >= prefixSum[higherMidRight] - prefixSum[i]) {
                higherMidRight++;
            }
            // 注意在跳出上面的while循环时，higherMidRight是最小的不满足条件（2）的元素，符合条件的midRight的上界应该为higherMidRight - 1
            if (lowerMidRight <= higherMidRight) {
                waysCnt += (higherMidRight - lowerMidRight) % MOD;
            }
        }
        return (int) (waysCnt % MOD);
    }
}
