package Kakao._2024.q5;

import java.util.*;
class Solution {
    static int[][] dp;
    static int result;

    public int solution(int n, int[] tops) {

        dp = new int[4][n + 1];
        for (int i = 0; i < 4; i++) {
            if(tops[0] == 0 && i == 2) continue;
            dp[i][1] = 1;
        }

        for (int i = 2; i < n + 1; i++) {
            dp[0][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1]) % 10007;
            dp[1][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1] + dp[3][i - 1]) % 10007;
            dp[3][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1] + dp[3][i - 1]) % 10007;
            if(tops[i - 1] == 0) continue;
            dp[2][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1] + dp[3][i - 1]) % 10007;
        }

        for (int i = 0; i < 4; i++) {
            result += dp[i][n];
        }

        return result % 10007;
    }

}