package 动态规划.双串.矩阵;

import java.util.Arrays;

/**
 * The Class: CalculateMinimumHP
 * 174. 地下城游戏
 * https://leetcode-cn.com/problems/dungeon-game/
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始HP为一个正整数。如果他的HP在某一时刻降至 0 或以下，他会立即死亡。
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去HP（若房间里的值为负整数，则表示骑士将损失HP）；
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士HP的魔法球（若房间里的值为正整数，则表示骑士将增加HP）。
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始HP。
 *
 * @author: Kaguya Y
 * @since: 2020-11-21 22:28
 */
public class CalculateMinimumHP {
    /**
     * 解法1: 动态规划
     * 
     * 如果从左上角到右下角进行推导，容易发现最大路径和、最小初始HP未必选择的是同一条路径
     * 从(0, 0)走到(i, j)的最小HP所对应的路径可能拥有更低的最大路径和，导致了走到终点时可能反而需要更大的初始HP
     * 因此，用dp[i][j]记录从右下角走到(i, j)所需要的最低初始HP
     * 显然，这里的最低初始HP一方面能够保证该点HP最小，也能保证骑士能够走到终点
     * 每次从(i + 1, j)和(i, j + 1)中需要更低初始HP的点出发，保证骑士从该点走到(i, j)不会死，并且使初始HP大于1
     * 对于边界上无法取到的点，将其初始HP设置为最大值；对于推导(m - 1, n - 1)所需要的的两个点，设置其初始HP为1
     * 
     * @param dungeon .
     * @return .
     */
    public int calculateMinimumHPSolution1(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[m][n - 1] = dp[m - 1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return dp[0][0];
    }

}
