package Kakao._2024.q3;

import java.util.*;
class Solution {
    static int N, maxWin;
    static int[] result, diceA, diceB;
    static List<Integer> scoreA, scoreB;


    public int[] solution(int[][] dice) {
        N = dice.length;
        result = new int[N / 2];
        diceA = new int[N / 2];
        diceB = new int[N / 2];
        comb(0, 0, dice);
        return result;
    }

    static void comb(int start, int depth, int[][] origin) {
        if (depth == N / 2) {
            findB();
            scoreA = new ArrayList<>();
            scoreB = new ArrayList<>();
            findSum(0, 0, 0, diceA, scoreA, origin);
            findSum(0, 0, 0, diceB, scoreB, origin);
            countWin();
            return;
        }

        for (int i = start; i < N; i++) {
            diceA[depth] = i;
            comb(i + 1, depth + 1, origin);
        }
    }

    static void countWin() {
        Collections.sort(scoreA);
        Collections.sort(scoreB);

        int beforeWin = 0;
        int winCnt = 0; // totalCnt
        int beforeIdx = 0;

        for (int i = 0; i < scoreA.size(); i++) {
            winCnt += beforeWin;
            for (int j = beforeIdx; j < scoreB.size(); j++) {
                if (scoreA.get(i) > scoreB.get(j)) {
                    beforeWin++;
                    winCnt++;
                    beforeIdx++;
                }
                else {
                    beforeIdx = j;
                    break;
                }
            }
        }

        if (winCnt > maxWin) {
            maxWin = winCnt;
            for (int i = 0; i < N / 2; i++) {
                result[i] = diceA[i] + 1;
            }
        }
    }

    static void findSum(int start, int depth, int sum, int[] dice, List<Integer> score, int[][] origin) {
        if (depth == N / 2) {
            score.add(sum);
            return;
        }

        for (int i = start; i < 6; i++) {
            sum += origin[dice[depth]][i];
            findSum(start, depth + 1, sum, dice, score, origin);
            sum -= origin[dice[depth]][i];
        }
    }


    static void findB() {
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tmp.add(i);
        }

        for (int n : diceA) {
            tmp.remove((Integer) n);
        }

        for (int i = 0; i < N / 2; i++) {
            diceB[i] = tmp.get(i);
        }
    }
}
