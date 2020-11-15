package 动态规划.双串.公共子序列;

/**
 * The Class: FindLength
 * 718. 最长重复子数组
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 *
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * @author: Kaguya Y
 * @since: 2020-11-15 22:26
 */
public class FindLength {
    /**
     * 方法1: 动态规划，dp[i][j]记录A[:i]和B[:j]中到最后一个元素为止的最大公共子数组长度
     *
     * @param A .
     * @param B .
     * @return .
     */
    public int findLengthSolution1(int[] A, int[] B) {
        int[][] dp = new int[A.length + 1][B.length + 1];
        int maxAns = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                dp[i + 1][j + 1] = A[i] == B[j] ? dp[i][j] + 1 : 0;
                maxAns = Math.max(maxAns, dp[i + 1][j + 1]);
            }
        }
        return maxAns;
    }

    /**
     * 方法2: 滑动窗口，在第一个循环中，每次用B数组的首个元素、A数组的第i个元素、长度为len的窗口进行比较；
     * 在第二个循环中，每次用A数组的首个元素、B数组的第i个元素、长度为len的窗口进行比较
     *
     * @param A .
     * @param B .
     * @return .
     */
    public int findLengthSolution2(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        int ret = 0;
        int len = 0;
        //每次用B数组的首个元素、A数组的第i个元素、长度为len的窗口进行比较
        for (int i = 0; i < n; i++) {
            //计算每次滑动窗口时的窗口长度
            len = Math.min(m, n - i);
            //只有当前滑动窗口大于已知最大结果时，才会在当前滑动窗口中计算最大公共子序列
            if (len > ret) {
                ret = Math.max(ret, maxLength(A, B, i, 0, len));
            }
        }

        //每次用A数组的首个元素、B数组的第i个元素、长度为len的窗口进行比较
        for (int i = 0; i < m; i++) {
            //计算每次滑动窗口时的窗口长度
            len = Math.min(n, m - i);
            //只有当前滑动窗口大于已知最大结果时，才会在当前滑动窗口中计算最大公共子序列
            if (len > ret) {
                ret = Math.max(ret, maxLength(A, B, 0, i, len));
            }
        }
        return ret;
    }

    private int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0;
        int k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

}
