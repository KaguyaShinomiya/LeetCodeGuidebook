package 二叉树与回溯算法.二叉树.其他二叉树;

import java.util.HashMap;

/**
 * The Class: NumTrees
 * 96. 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * @author: Kaguya Y
 * @since: 2020-11-30 00:56
 */
public class NumTrees {
    /**
     * 解法1: 动态规划
     * 二叉搜索树个数G(n)事实上就是以1,...,n各个节点为根节点的二叉搜索树的个数之和
     * 以i为根节点的二叉搜索树，其左子树为1,...,i-1；右子树为i+1,i+2,..,n
     * 显然其左子树有G(i - 1)个，右子树有G(n - i)个，故以i为根节点的二叉搜索树共有G(i - 1)*G(n - i)个
     *
     */
    private HashMap<Integer, Integer> treeNums = new HashMap<>(){
        {
            put(0, 1);
            put(1, 1);
            put(2, 2);
        }
    };
    public int numTreesSolution1(int n) {
        if (treeNums.containsKey(n)) {
            return treeNums.get(n);
        }
        int numTrees = 0;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                numTrees += treeNums.get(j - 1) * treeNums.get(n - j);
            }
            treeNums.put(i, numTrees);
            numTrees = 0;
        }
        return numTrees;
    }

    /**
     * 解法2: 动态规划
     * dp[n]记录以 1 ... n 为节点组成的二叉搜索树的个数
     */
    public int numTreesSolution2(int n) {
        int[] dp = new int[n + 1];
        if (n < 2) {
            return n;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
