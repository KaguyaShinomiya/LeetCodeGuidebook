package DFS与回溯算法.DFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Class: NumIslands
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-02 20:34
 */
public class NumIslands {
    /**
     * 解法1: DFS
     * 每次遍历到一个'1'，就进行深度优先搜索，将这个1上下左右的的1都标记为0
     * 这样，从第一个1开始，每个和这个1相邻的1都被标记为0；
     * 接下来，再进行遍历；重复上述操作
     * 统计遇到1的次数
     *
     */
    public int numIslandsSolution1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }

        }
        return count;

    }

    private void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    /**
     * 解法2: BFS
     * 每次将相邻元素加到队列中
     */
    public int numIslandsSolution2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        Queue<Integer> adjacentIsland = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == '1') {
                    count++;
                    grid[row][col] = '0';
                    adjacentIsland.offer(row * n + col);
                }
                while (!adjacentIsland.isEmpty()) {
                    int pos = adjacentIsland.poll();
                    int r = pos / n;
                    int c = pos % n;
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        adjacentIsland.offer((r - 1) * n + c);
                        grid[r - 1][c] = '0';
                    }
                    if (r + 1 < m && grid[r + 1][c] == '1') {
                        adjacentIsland.offer((r + 1) * n + c);
                        grid[r + 1][c] = '0';
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        adjacentIsland.offer(r * n + c - 1);
                        grid[r][c - 1] = '0';
                    }
                    if (c + 1 < n && grid[r][c + 1] == '1') {
                        adjacentIsland.offer(r * n + c + 1);
                        grid[r][c + 1] = '0';
                    }
                }
            }
        }
        return count;
    }
}
