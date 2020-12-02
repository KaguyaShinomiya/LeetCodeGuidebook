package DFS与回溯算法.DFS;

/**
 * The Class: FindCircleNum
 * 547. 朋友圈
 *
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 *
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
 * 你必须输出所有学生中的已知的朋友圈总数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/friend-circles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Kaguya Y
 * @since: 2020-12-03 00:01
 */
public class FindCircleNum {
    /**
     * 解法1: DFS
     * 用一维数组记录已经加入到朋友圈的人（即行索引或列索引）
     * 遍历没有加入朋友圈的人所在的第row行，如果发现该行中第col列元素为1，且该列元素也没有被加入到朋友圈
     * 就跳转到这个元素的列索引所表示的行第col行，重复上述操作；
     * 本质上就是寻找第row个人的朋友col，将col加入到row的朋友圈，再去寻找第col个人有联系的其他朋友，层层递归
     */
    int N = 0;
    public int findCircleNum(int[][] M) {
        N = M.length;
        int count = 0;
        // 用一维数组记录已经加入到朋友圈的元素索引
        int[] visited = new int[N];
        for (int row = 0; row < N; row++) {
            // 任何一个人只可能出现在一个朋友圈中，所以只需要对那些还没有加入朋友圈的人进行计数
            if (visited[row] == 0) {
                dfs(M, visited, row);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int[] visited, int row) {
        for (int col = 0; col < N; col++) {
            // 如果发现当前第row行中第col列元素为1，且该列没有被访问到，就将第col个元素标记为已访问
            // 跳转到第col行，重复上述操作
            if (M[row][col] == 1 && visited[col] == 0) {
                visited[col] = 1;
                dfs(M, visited, col);
            }
        }
    }
}
