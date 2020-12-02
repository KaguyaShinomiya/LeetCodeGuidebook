package 栈和队列.栈;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The Class: DailyTemperatures
 * 739. 每日温度
 *
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-02 20:09
 */
public class DailyTemperatures {
    /**
     * 解法1: 单调栈
     * 每次遍历时，如果当前元素T[i]小于栈顶元素prev索引下的温度T[prev]，则将当前索引i加到栈顶；
     * 显然，栈中的元素形成了一串递减的温度序列
     * 如果当前元素T[i]比当前栈顶元素prev下的温度T[prev]要大，显然第i天的温度是高于第prev天的温度的第一天
     * 第i天应该等待的时间为i - prev；
     * 弹出栈顶元素，随后继续将T[i]与栈顶元素比较；重复循环。
     * 注意最后需要把i也加进单调栈
     */
    public int[] dailyTemperatures(int[] T) {
        if (T.length == 0) {
            return null;
        }
        Deque<Integer> temperatureStack = new LinkedList<>();
        int[] days = new int[T.length];
        temperatureStack.push(0);
        for (int i = 1; i < T.length; i++) {
            while (!temperatureStack.isEmpty() && T[i] > T[temperatureStack.peek()]) {
                int prev = temperatureStack.pop();
                days[prev] = i - prev;
            }
            temperatureStack.push(i);

        }
        while (!temperatureStack.isEmpty()) {
            int prev = temperatureStack.pop();
            days[prev] = 0;
        }
        return days;
    }
}
