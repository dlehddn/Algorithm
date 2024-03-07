package Kakao._2022.코딩테스트공부;

import java.util.Arrays;

class Solution {
    static int[][] dp;

    public int solution(int alp, int cop, int[][] problems) {
        dp = new int[151][151];
        for (int i = 0; i < 151; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        int goalA = 0;
        int goalC = 0;

        for (int[] problem : problems) {
            if (problem[0] > goalA) {
                goalA = problem[0];
            }
            if (problem[1] > goalC) {
                goalC = problem[1];
            }
        }

        if (alp > goalA) alp = goalA;
        if (cop > goalC) cop = goalC;
        dp[alp][cop] = 0;

        for (int i = alp; i < 151; i++) {
            for (int j = cop; j < 151; j++) {
                try {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                } catch (ArrayIndexOutOfBoundsException e) {}

                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        try {
                            int aIdx = Math.min(goalA, i + problem[2]);
                            int cIdx = Math.min(goalC, j + problem[3]);
                            dp[aIdx][cIdx] = Math.min(dp[aIdx][cIdx], dp[i][j] + problem[4]);
                        } catch (ArrayIndexOutOfBoundsException e) {}
                    }
                }
            }
        }
        return dp[goalA][goalC];
    }
}