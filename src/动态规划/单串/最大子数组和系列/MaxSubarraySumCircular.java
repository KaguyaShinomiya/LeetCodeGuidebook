package 动态规划.单串.最大子数组和系列;

/**
 * The Class: MaxSubarraySumCircular
 * 918. 环形子数组的最大和
 * https://leetcode-cn.com/problems/maximum-sum-circular-subarray/
 *
 * @author: Kaguya Y
 * @since: 2020-11-10 21:55
 */
public class MaxSubarraySumCircular {
    /**
     * 对于每个元素，最大的子序列和要么是以A[i]结尾的最大子序和，
     * 要么是从A[0]+A[1]+...+A[i](leftSum)再加上A[k]+A[k+1]+...+A[A.length-1](maxRight)
     * A[k]+A[k+1]+...+A[A.length-1]是所有以最后一个元素为结尾的连续的子序和的最大值，且k>i+1
     *
     * @param A .
     * @return .
     */
    public int maxSubarraySumCircularSolution1(int[] A) {
        if (A.length == 1) return A[0];
        if (A.length == 2) return Math.max(A[1], Math.max(A[2], A[1] + A[2]));
        int maxSum = A[0];
        int cur = A[0];
        int[] rightSum = new int[A.length];
        int[] maxRight = new int[A.length];
        rightSum[A.length - 1] = A[A.length - 1];
        maxRight[A.length - 1] = A[A.length - 1];
        for (int i = 1; i < A.length; i++) {
            cur = Math.max(cur, 0) + A[i];
            //在第一次循环中计算从各元素出发到最后一个元素的连续的子序和
            rightSum[A.length - i - 1] = rightSum[A.length - i] + A[A.length - i - 1];
            //在第一次循环中计算以各元素结尾的单区间子序和的最大值
            maxSum = Math.max(cur, maxSum);
        }

        for (int i = A.length - 2; i >= 0; i--) {
            //计算各元素为起点到最后一个元素的连续子序和的最大值
            maxRight[i] = Math.max(maxRight[i + 1], rightSum[i]);
        }

        int leftSum = 0;
        for (int i = 0; i < A.length - 1; i++) {
            leftSum += A[i];
            maxSum = Math.max(maxSum, leftSum + maxRight[i + 1]);
        }

        return maxSum;
    }
}
