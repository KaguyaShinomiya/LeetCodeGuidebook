package DFS与回溯算法.回溯算法;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class: SolveSudoku
 * 37. 解数独
 * https://leetcode-cn.com/problems/sudoku-solver/
 *
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 一个数独的解法需遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * @author: Kaguya Y
 * @since: 2020-11-24 22:24
 */
public class SolveSudoku {
    private final int M = 9;

    /**
     * 解法1: 回溯算法
     *
     * 在回溯方法的开头判断是否到达返回最终结果或开启下一轮回溯的条件；
     * 随后，按照回溯算法的经典四步走：
     * 第一步，遍历所有的选择A1、A2、A3...；
     * 第二步，选择当前节点A下能够使用的一个选择节点A1作为子节点或后继节点；
     * 第三步，对子节点或后继节点递归调用回溯方法；
     * 第四步，返回到当前方法后，擦去刚才的选择，进入遍历中的其他可以选择的元素A2，重复第二步；
     *
     * @param board .
     */
    public void solveSudokuSolution1(char[][] board) {
        backTrack(board, 0, 0);
    }

    private boolean backTrack(char[][] board, int i, int j) {
        // 如果列指针大于边长，就移动到下一行的起始位置
        if (j == M) {
            return backTrack(board, i + 1, 0);
        }
        // 如果行指针等于9，意味着已经得到可行的数独结果
        if (i == M) {
            return true;
        }
        if (board[i][j] != '.') {
            return backTrack(board, i, j + 1);
        }
        // 列出选择
        for (char ch = '1'; ch <= '9'; ch++) {
            //校验当前字符选择是否合法
            if (!isValid(board, ch, i, j)) {
                continue;
            }
            // 做出选择
            board[i][j] = ch;
            // 继续穷举下一个，如果得到了可行的结果，则返回true
            if (backTrack(board, i, j + 1)) {
                // 注意程序返回true时，上个节点的条件判断也会返回true，然后上个节点的backTrack也返回true
                // 进入到上上个节点的backTrue方法，反复地在这两行代码中返回true，最终直接跳出递归
                return true;
            }
            // 如果最后得到的是不可行的结果，将这次选择擦去，进入下一轮for循环，试试新的字符
            board[i][j] = '.';
        }
        // 如果上一个for循环中所有字符都不行，说明在当前的位置上已经不能得出可行的结果了
        // 程序会回到上一个节点的if (backTrack(board, i, j + 1))条件判断，然后进入board[i][j] = '.'，继续下一轮循环和递归
        return false;
    }

    private boolean isValid(char[][] board, char ch, int i, int j) {
        for (int k = 0; k < M; k++) {
            // 校验同行是否有重复元素
            if (board[i][k] == ch) return false;
            // 校验同列是否有重复元素
            if (board[k][j] == ch) return false;
            // 校验同一个小正方形是否有重复元素
            if (board[(i / 3) * 3 + k / 3][(j / 3) * 3 + k % 3] == ch) return false;
        }
        return true;
    }

    List<Set> rowSetList = new ArrayList<>();
    List<Set> colSetList = new ArrayList<>();
    List<Set> blockSetList = new ArrayList<>();

    /**
     * 解法2: 回溯算法
     *
     * 通过维护9个行集合、列集合、方块集合，用来判断各个索引上元素是否重复
     *
     * @param board .
     */
    public void solveSudokuSolution2(char[][] board) {
        initializeSets(board);
        backTracking(board, 0, 0);

    }

    private void initializeSets(char[][] board) {
        for (int i = 0; i < 9; i++) {
            rowSetList.add(new HashSet<>());
            colSetList.add(new HashSet<>());
            blockSetList.add(new HashSet<>());
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    rowSetList.get(i).add(ch);
                    colSetList.get(j).add(ch);
                    blockSetList.get(getBlockIndex(i, j)).add(ch);
                }
            }
        }
    }

    // 根据行索引和列索引获得方块索引
    private int getBlockIndex(int i, int j) {
        return (i / 3) * 3 + j / 3;
    }

    private boolean backTracking(char[][] board, int i, int j) {
        // 如果列指针大于边长，就移动到下一行的起始位置
        if (j == M) {
            return backTracking(board, i + 1, 0);
        }
        // 如果行指针等于9，意味着已经得到可行的数独结果
        if (i == M) {
            return true;
        }
        if (board[i][j] != '.') {
            return backTracking(board, i, j + 1);
        }
        Set rowSet = rowSetList.get(i);
        Set colSet = colSetList.get(j);
        Set blockSet = blockSetList.get(getBlockIndex(i, j));
        // 列出选择
        for (char ch = '1'; ch <= '9'; ch++) {
            //用各个set校验当前字符选择是否合法
            if (rowSet.contains(ch) || colSet.contains(ch) || blockSet.contains(ch)) {
                continue;
            }
            // 做出选择
            board[i][j] = ch;
            rowSet.add(ch);
            colSet.add(ch);
            blockSet.add(ch);
            // 继续穷举下一个，如果得到了可行的结果，则返回true
            if (backTracking(board, i, j + 1)) {
                // 注意程序返回true时，上个节点的条件判断也会返回true，然后上个节点的backTrack也返回true
                // 进入到上上个节点的backTrue方法，反复地在这两行代码中返回true，最终直接跳出递归
                return true;
            }
            // 如果最后得到的是不可行的结果，将这次选择擦去，进入下一轮for循环，试试新的字符
            board[i][j] = '.';
            // 注意从当前的各个set中也要去掉之前选择的字符
            rowSet.remove(ch);
            colSet.remove(ch);
            blockSet.remove(ch);
        }
        // 如果上一个for循环中所有字符都不行，说明在当前的位置上已经不能得出可行的结果了
        // 程序会回到上一个节点的if (backTrack(board, i, j + 1))条件判断，然后进入board[i][j] = '.'，继续下一轮循环和递归
        return false;
    }
}
