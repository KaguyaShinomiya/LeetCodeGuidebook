package 排序与查找.经典排序;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Class: MinNumber
 * 剑指 Offer 45. 把数组排成最小的数
 *
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *  
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-17 22:14
 */
public class MinNumber {
    /**
     * 解法1：
     * 很显然，让越”小“的数字排在前面就好了
     * 比如虽然9<123，但是1239比9123要小，所以在排序后的数组里需要把123放在9的后面
     * 不难推理出：
     * 1、尽可能让首位数字较小的元素排在前面，能够减小拼接数的大小。
     * 2、如果首位数字相同，且长度相同，例如1235和1361，很明显，直接比较两个数字的大小，把小的数字放在前面就可以了；
     * 3、但是如果首位数字相同，而长度不同呢？比如12和121，12112小于12112，所以121放在12的前面；
     * 但是10和101,10110大于10101，所以101放在10的后面。
     * 这种情况很难直接从数字本身推导出规律，不如直接把两个数字一前一后拼在一起，比较一下，就能发现哪个数字更适合放在前面了。
     *
     */
    public String minNumber(int[] nums) {
        PriorityQueue<String> numQueue = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                // 1、尽可能让首位数字较小的元素排在前面，能够减小拼接数的大小
                if (o1.charAt(0) != o2.charAt(0)) {
                    return Character.compare(o1.charAt(0), o2.charAt(0));
                } else if (o1.length() == o2.length()) {
                    // 2、如果首位数字相同，且长度相同，例如1235和1361，很明显，直接比较两个数字的大小，把小的数字放在前面就可以了
                    return Integer.compare(Integer.parseInt(o1), Integer.parseInt(o2));
                } else {
                    // 3、如果首位数字相同，而长度不同，直接把两个数字一前一后拼在一起，比较一下，就能发现哪个数字更适合放在前面了
                    return Integer.compare(Integer.parseInt(o1 + o2), Integer.parseInt(o2 + o1));
                }
            }
        });
        for (int num : nums) {
            numQueue.add(String.valueOf(num));
        }
        StringBuffer merged = new StringBuffer();
        while (!numQueue.isEmpty()) {
            merged.append(numQueue.poll());
        }
        return merged.toString();
    }
}
