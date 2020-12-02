package DFS与回溯算法.回溯算法;

import java.util.*;

/**
 * The Class: SolveNQueens
 * 51. N 皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例：
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *  
 * 提示：
 *
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-11-27 23:11
 */
public class SolveNQueens {
    /**
     * 解法1: 回溯法，用集合记录已经放置了皇后的列、左斜线、右斜线，用数组queens记录每行放置了皇后的列索引
     *
     */
    public List<List<String>> solveNQueensSolution1(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diag1 = new HashSet<>();
        Set<Integer> diag2 = new HashSet<>();
        backTrack(res, queens, n, 0, columns, diag1, diag2);
        return res;
    }

    private void backTrack(List<List<String>> res, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diag1,
                           Set<Integer> diag2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
            return;
        } else {
            for (int col = 0; col < n; col++) {
                if (columns.contains(col) || diag1.contains(row + col) || diag2.contains(row - col)) {
                    continue;
                }
                queens[row] = col;
                columns.add(col);
                diag1.add(row + col);
                diag2.add(row - col);
                backTrack(res, queens, n, row + 1, columns, diag1, diag2);
                queens[row] = -1;
                columns.remove(col);
                diag1.remove(row + col);
                diag2.remove(row - col);
            }
        }

    }

    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] rowChar = new char[n];
            Arrays.fill(rowChar, '.');
            rowChar[queens[i]] = 'Q';
            board.add(new String(rowChar));
        }
        return board;
    }

    /**
     * 解法2: 回溯算法 + 空间优化 + 时间优化
     * 通过数组保存已经使用的列和对角线信息，节约空间
     *
     */
    private List<List<String>> res = new ArrayList<>();
    private char[][] board;
    private boolean[] usedCols;
    private boolean[] usedDiag1;
    private boolean[] usedDiag2;

    public List<List<String>> solveNQueensSolution2(int n) {
        res = new ArrayList<>();
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        usedCols = new boolean[n];
        usedDiag1 = new boolean[2 * n - 1];
        usedDiag2 = new boolean[2 * n - 1];
        backTracking(n, 0);
        return res;
    }

    private void backTracking(int n, int row) {
        if (row == n) {
            List<String> rowString = new ArrayList<>();
            for (char[] boardRow : board) {
                rowString.add(new String(boardRow));
            }
            res.add(rowString);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (usedCols[col] || usedDiag1[row + col] || usedDiag2[n - 1 - row + col]) {
                continue;
            } else {
                board[row][col] = 'Q';
                usedCols[col] = true;
                usedDiag1[row + col] = true;
                usedDiag2[n - 1 - row + col] = true;
                backTracking(n, row + 1);
                board[row][col] = '.';
                usedCols[col] = false;
                usedDiag1[row + col] = false;
                usedDiag2[n - 1 - row + col] = false;
            }
        }
    }

}
