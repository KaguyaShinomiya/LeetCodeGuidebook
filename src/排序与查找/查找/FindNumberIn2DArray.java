package 排序与查找.查找;

/**
 * The Class: FindNumberIn2DArray
 *
 * 240. 搜索二维矩阵II 剑指 Offer 04. 二维数组中的查找
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 *
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 * @author: Kaguya Y
 * @since: 2020-11-23 22:52
 */
public class FindNumberIn2DArray {
    /**
     * 解法1:
     * 从矩阵的左下角看，上方的数字都比其小，右方的数字都比其大，所以依据该规律去判断数字是否存在
     * 设当前数字为 cur，目标数字为 target，当 target < cur 时，cur 更新为其上面的数字，当 target > cur 时，cur 更新为其右侧的数字，
     * 直到相等则返回 true，否则到了矩阵边界返回 false
     * 时间复杂度：O(m+n)
     *
     * @param matrix .
     * @param target .
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int rowNum = matrix.length - 1;
        int colNum = 0;
        while (rowNum >= 0 && colNum < matrix[0].length) {
            if (matrix[rowNum][colNum] < target) {
                colNum++;
            } else if (matrix[rowNum][colNum] > target) {
                rowNum--;
            } else {
                return true;
            }
        }
        return false;
    }
}
