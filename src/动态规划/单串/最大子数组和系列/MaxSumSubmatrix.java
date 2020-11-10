package 动态规划.单串.最大子数组和系列;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The Class: MaxSumSubmatrix
 * 363. 矩形区域不超过 K 的最大数值和
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 *
 * @author: Kaguya Y
 * @since: 2020-11-11 00:16
 */
public class MaxSumSubmatrix {
    /**
     * 先压缩为一维数组，每次循环计算从第i列到第j列，各行元素相加得到的列向量和的数组colSums
     * 每次从第i列始，依次计算第i列、第i列到第i+1列、...、第i列到最后一列的列向量和的数组colSums
     * 对于每次计算得到的列向量数组colSums，计算最大子序列和，记录每次最大子序列和对应的四个元素信息
     *
     * @param matrix .
     * @param k .
     * @return .
     */
    public int maxSumSubmatrixSolution1(int[][] matrix, int k) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] rowSums = new int[rows];
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < cols; i++) {
            rowSums = new int[rows];
            for (int j = i; j < cols; j++) {
                for (int m = 0; m < rows; m++) {
                    //将二维数组压缩至一维数组，求的是每列中各行的和
                    rowSums[m] += matrix[m][j];
                }
                maxSum = Math.max(rowMax(rowSums, k), maxSum);
                if (maxSum == k) {
                    return k;
                }
            }
        }
        return maxSum;
    }

    private int rowMax(int[] rowSums, int k) {
        int tempSum = 0;
        int rowMaxSum = Integer.MIN_VALUE;
        for (int i = 0; i < rowSums.length; i++) {
            tempSum = Math.max(tempSum, 0) + rowSums[i];
            rowMaxSum = Math.max(rowMaxSum, tempSum);
        }
        if (rowMaxSum <= k) {
            return rowMaxSum;
        }
        rowMaxSum = Integer.MIN_VALUE;
        for (int i = 0; i < rowSums.length; i++) {
            tempSum = 0;
            for (int j = i; j < rowSums.length; j++) {
                tempSum += rowSums[j];
                if (tempSum <= k && tempSum > rowMaxSum) {
                    rowMaxSum = tempSum;
                }
                if (rowMaxSum == k) {
                    return k;
                }
            }
        }
        return rowMaxSum;
    }

}
