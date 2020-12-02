package DFS与回溯算法.回溯算法;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class: Permute
 * 46. 全排列
 * https://leetcode-cn.com/problems/permutations/
 *
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * @author: Kaguya Y
 * @since: 2020-11-24 22:18
 */
public class Permute {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * 解法1: 回溯算法
     *
     * @param nums .
     * @return .
     */
    public List<List<Integer>> permuteSolution1(int[] nums) {
        List<Integer> track = new ArrayList<>();
        backTrack(track, nums);
        return res;
    }

    private void backTrack(List<Integer> track, int[] nums) {
        if (track.size() == nums.length) {
            res.add(new ArrayList(track));
            return;
        }

        //每到一个分支点，遍历这个分支点下面所有可能的子节点
        for (int i = 0; i < nums.length; i++) {
            // 如果当前track已经包含了当前元素，则跳到下个元素
            if (track.contains(nums[i])) {
                continue;
            }
            // 选中当前结点下面可行的子节点
            track.add(nums[i]);
            // 在子节点这一层级继续递归调用
            backTrack(track, nums);
            // 返回时，删除当前track的最后一个元素
            track.remove(track.size() - 1);
        }
    }

}
