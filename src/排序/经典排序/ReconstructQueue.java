package 排序.经典排序;

import javax.sound.sampled.Line;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class: ReconstructQueue
 * 406. 根据身高重建队列
 *
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 * 示例 1：
 *
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-03 01:16
 */
public class ReconstructQueue {
    public int[][] reconstructQueue(int[][] people) {
        // 对原数组按照h和k两个属性先后进行排序，身高相同时，按k排序
        Arrays.sort(people, (o1, o2) -> {
            // 对身高倒序排序，使身高较高的排在前面
            if (o1[0] != o2[0]) {
                return Integer.compare(o2[0], o1[0]);
            } else {
                // 对k顺序排序，使k值较小的排在前面
                return Integer.compare(o1[1], o2[1]);
            }
        });
        List<int[]> reconsQueue = new LinkedList<>();
        // 对于每个元素，安插在链表中的第k个位置即可
        for (int[] person : people) {
            reconsQueue.add(person[1], person);
        }
        return reconsQueue.toArray(new int[people.length][]);
    }
}
