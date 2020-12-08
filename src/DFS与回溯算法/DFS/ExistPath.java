package DFS与回溯算法.DFS;

import java.util.HashSet;
import java.util.Set;

/**
 * The Class: ExistPath
 * 79. 单词搜索
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  
 *
 * 示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 *
 * 提示：
 *
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-09 00:10
 */
public class ExistPath {
    /**
     * 解法1: DFS
     * 从符合条件的第一个元素开始向相邻元素探索
     * 用集合记录
     * 注意，在从递归返回结果前，一定要从集合中删除使用过的元素
     * 否则，递归中走过的一条路线可能会把其他递归中并没有使用到的元素加入到了集合里
     * 导致其他递归没办法使用相应的元素
     *
     */
    private int M = 1;
    private int N = 1;
    private Set<Integer> visited = new HashSet<>();
    public boolean existPath(char[][] board, String word) {
        M = board.length;
        N = board[0].length;
        char[] wordArrays = word.toCharArray();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == wordArrays[0]) {
                    if (dfs(board, wordArrays, i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] wordArrays, int row, int col, int pos) {
        if (pos == wordArrays.length) {
            return true;
        }
        if (row >= M || row < 0 || col >= N || col < 0 || board[row][col] != wordArrays[pos]
                || visited.contains(row * N + col)) {
            return false;
        }
        visited.add(row * N + col);
        boolean hasPath = dfs(board, wordArrays, row + 1, col, pos + 1) || dfs(board, wordArrays, row - 1, col, pos + 1)
                || dfs(board, wordArrays, row, col + 1, pos + 1) || dfs(board, wordArrays, row, col - 1, pos + 1);
        visited.remove(row * N + col);
        return hasPath;
    }


}
