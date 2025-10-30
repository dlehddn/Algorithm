package DP.프로그래머스_거스름돈;

import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        Arrays.sort(money);
        int[][] dp = new int[money.length][n + 1];
        for (int i = 0; i < money.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < money.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (i > 0) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (j - money[i] >= 0) {
                    dp[i][j] += dp[i][j - money[i]];
                }
            }
        }

        return dp[money.length - 1][n];
    }
}
