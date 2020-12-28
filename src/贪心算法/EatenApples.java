package 贪心算法;

import java.util.*;

/**
 * The Class: EatenApples
 * 1705. 吃苹果的最大数目
 *
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，
 * 这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，
 * 此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 *
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 *
 * 示例 1：
 *
 * 输入：apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * 输出：7
 * 解释：你可以吃掉 7 个苹果：
 * - 第一天，你吃掉第一天长出来的苹果。
 * - 第二天，你吃掉一个第二天长出来的苹果。
 * - 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
 * - 第四天到第七天，你吃的都是第四天长出来的苹果。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-eaten-apples
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya yu
 * @since: 2020-12-27 10:55
 */
public class EatenApples {
    /**
     * 解法1: 贪心算法
     * 每次只吃到期时间离当期最近的苹果，
     * 用一个哈希表维护 苹果到期时间--苹果数量的键值对，
     * 但是这样的话我们需要遍历哈希表的各个键，判断对应的到期时间是否符合要求（即到期时间是否晚于当前日期）；
     * 所以，可以用一个优先队列保存到期时间的滑动窗口，排除掉已经无效的到期时间
     *
     */
    public int eatenApplesSolution1(int[] apples, int[] days) {
        PriorityQueue<Integer> expiredDays = new PriorityQueue<>();
        Map<Integer, Integer> remainingApples = new HashMap<>();
        int rightMost = 0;
        int eatenApples = 0;
        int currDay = 0;
        while (currDay <= Math.max(apples.length, rightMost)) {
            if (currDay < apples.length) {
                int expiredDay = currDay + days[currDay] - 1;
                rightMost = Math.max(expiredDay, rightMost);
                remainingApples.put(expiredDay, remainingApples.getOrDefault(expiredDay, 0) + apples[currDay]);
                expiredDays.offer(expiredDay);
            }
            // 在优先队列中排除掉早于当前日期的苹果到期日期
            while (!expiredDays.isEmpty() && expiredDays.peek() < currDay) {
                expiredDays.poll();
            }
            if (!expiredDays.isEmpty()) {
                // 吃下到期时间最小的1个苹果
                int closestExpiredDay = expiredDays.peek();
                remainingApples.put(closestExpiredDay, remainingApples.get(closestExpiredDay) - 1);
                eatenApples++;
                if (remainingApples.get(closestExpiredDay) <= 0) {
                    expiredDays.poll();
                }
            }
            currDay++;
        }
        return eatenApples;
    }

    /**
     * 解法2: 贪心算法
     * 直接把到期时间--苹果数量的键值对加入到优先队列中，进一步优化空间和时间复杂度
     *
     */
    public int eatenApplesSolution2(int[] apples, int[] days) {
        PriorityQueue<int[]> expiredDays = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        int eatenApples = 0;
        for (int i = 0; ; i++) {
            if (i < apples.length && apples[i] > 0) {
                int expiredDay = i + days[i] - 1;
                expiredDays.offer(new int[]{expiredDay, apples[i]});
            } else if (i >= apples.length && expiredDays.isEmpty()) {
                break;
            }
            // 在优先队列中排除掉早于当前日期的键值对、或者苹果数量已经小于0的键值对
            while (!expiredDays.isEmpty() && (expiredDays.peek()[0] < i || expiredDays.peek()[1] <= 0)) {
                expiredDays.poll();
            }
            if (!expiredDays.isEmpty()) {
                // 吃下到期时间最小的1个苹果
                expiredDays.peek()[1]--;
                eatenApples++;
            }
        }
        return eatenApples;
    }
}
