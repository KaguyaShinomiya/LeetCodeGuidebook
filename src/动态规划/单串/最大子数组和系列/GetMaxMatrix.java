package 动态规划.单串.最大子数组和系列;

import java.util.Arrays;

/**
 * The Class: GetMaxMatrix
 * 面试题 17.24. 最大子矩阵
 * https://leetcode-cn.com/problems/max-submatrix-lcci/
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，
 * r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-11 00:02
 */
public class GetMaxMatrix {

    /**
     * 降维，每次循环计算从第i行到第j行，各列元素相加得到的行向量数组rowSums
     * 每次从第i行开始，依次计算第i行、第i行到第i+1行、...、第i行到最后一行的行向量数组
     * 对于每次计算得到的行向量数组rowSums，计算最大子序列和，记录每次最大子序列和对应的四个元素信息
     *
     * @param matrix .
     * @return .
     */
    public int[] getMaxMatrixSolution1(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] rowSums = new int[cols];
        int tempSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int bestc1 = 0;
        int[] ans = new int[4];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(rowSums, 0);
            //每次都计算从第i行到第i+1行、第i+2行。。。第rows-1行的rowSums
            for (int j = i; j < rows; j++) {
                tempSum = 0;
                for (int k = 0; k < cols; k++) {
                    rowSums[k] += matrix[j][k];
                    if (tempSum > 0) {
                        tempSum += rowSums[k];
                    } else {
                        //对于同一个rowSums数组，左上角元素的行数始终为i，不需要记录
                        bestc1 = k;
                        tempSum = rowSums[k];
                    }
                    if (tempSum > maxSum) {
                        maxSum = tempSum;
                        ans[0] = i;
                        ans[1] = bestc1;
                        ans[2] = j;
                        ans[3] = k;
                    }
                }
            }
        }
        return ans;

    }
}
