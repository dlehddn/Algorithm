package DP.프로그래머스_땅따먹기;

import java.util.*;

class Solution {
    int solution(int[][] land) {
        int N = land.length;
        int[][] landSum = new int[N][4];

        landSum = makeRowNumOf1(land, landSum);
        landSum = memorizedSum(land, landSum, N);
        return findMaxValue(landSum, N);

    }

    private int[][] makeRowNumOf1(int[][] land, int[][] landSum) {
        for(int i = 0; i < 4; i++) {
            landSum[0][i] = land[0][i];
        }
        return landSum;
    }

    private int[][] memorizedSum(int[][] land, int[][] landSum, int N) {
        for(int i = 1; i < N; i++) {
            landSum[i][0] = land[i][0] + Math.max(landSum[i-1][2], landSum[i-1][3]);
            landSum[i][1] = land[i][1] + searchMax(landSum[i-1][0], landSum[i-1][2], landSum[i-1][3]);
            landSum[i][2] = land[i][2] + searchMax(landSum[i-1][0], landSum[i-1][1], landSum[i-1][3]);
            landSum[i][3] = land[i][3] + searchMax(landSum[i-1][0], landSum[i-1][1], landSum[i-1][2]);
        }
        return landSum;
        // Math.max(1,2)
    }

    private int searchMax(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }

    private int findMaxValue(int[][] landSum, int N) {
        int max = 0;
        for(int i = 0; i < 4; i++) {
            int currentValue = landSum[N-1][i];
            if(currentValue > max) {
                max = currentValue;
            }
        }
        return max;
    }
}