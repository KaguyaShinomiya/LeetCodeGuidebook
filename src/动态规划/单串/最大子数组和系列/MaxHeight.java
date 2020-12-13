package 动态规划.单串.最大子数组和系列;

import java.util.Arrays;
import java.util.List;

/**
 * The Class: MaxHeight
 * 5245. 堆叠长方体的最大高度
 * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。
 * 请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
 *
 * 如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。
 * 你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
 * 返回 堆叠长方体 cuboids 可以得到的 最大高度 。
 *
 * 示例 1：
 *
 * 输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * 输出：190
 * 解释：
 * 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
 * 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
 * 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
 * 总高度是 95 + 50 + 45 = 190 。
 * 示例 2：
 *
 * 输入：cuboids = [[38,25,45],[76,35,3]]
 * 输出：76
 * 解释：
 * 无法将任何长方体放在另一个上面。
 * 选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
 * 示例 3：
 *
 * 输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * 输出：102
 * 解释：
 * 重新排列长方体后，可以看到所有长方体的尺寸都相同。
 * 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
 * 堆叠长方体的最大高度为 6 * 17 = 102 。
 *  
 *
 * 提示：
 *
 * n == cuboids.length
 * 1 <= n <= 100
 * 1 <= widthi, lengthi, heighti <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-height-by-stacking-cuboids
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-12-13 11:49
 */
public class MaxHeight {
    /**
     * 解法1: 动态规划
     * 这道题本质上还是最长上升子序列问题；
     * 我们不需要关心各个长方体哪个是原来的长宽高，只需要保证按照顺序排列长方体的边长
     * 只有当一个长方体的最短的边长、第二长的边长、最长的边长都比另外一个长方体更短，才可以把它叠放在另一个长方体上
     * 为了保证总高度最高，我们需要令最长的边长作为长方体的高
     * 如果我们将数组进行排序，令数组dp[i]表示【以第i个长方体为底】的【堆叠长方体】的最大高度，就可以把这个问题转化为最长上升子序列问题了
     */
    public int maxHeight(int[][] cuboids) {
        // 首先，将各个长方体数组内部的元素（即边长）从小到大进行排序
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        // 随后将整个长方体数组按照最短边长、次长边长、最长边长进行排序
        Arrays.sort(cuboids, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]);
            } else if (o1[1] != o2[1]) {
                return Integer.compare(o1[1], o2[1]);
            } else {
                return Integer.compare(o1[2], o2[2]);
            }
        });
        // dp[i]表示【以第i个长方体为底】的【堆叠长方体】的最大高度
        int[] dp = new int[cuboids.length];
        int maxAns = 0;
        // 显然，我们需要找出可以放在第i个长方体上方的所有【堆叠长方体】的最大高度
        // 加上第i个长方体的高度，就可以得出【以第i个长方体为底】的【堆叠长方体】的最大高度
        for (int i = 0; i < cuboids.length; i++) {
            for (int j = 0; j < i; j++) {
                // 虽然我们已经做了排序，但是还不能保证前面的长方体一定能够放在后面的长方体上方，只能保证后面的长方体一定不能放在前面的长方体上
                // 因此需要依次进行判断前面各个【堆叠长方体】能不能放置在第i个长方体上方，并找出前面各个【堆叠长方体】的最大高度的最大值
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += cuboids[i][2];
            // 更新所有【堆叠长方体】的最大高度
            maxAns = Math.max(maxAns, dp[i]);
        }
        return maxAns;
    }
}
