package Kakao._2023.q3;

class Solution {

    static int[] result;
    static int N, M;

    public int[] solution(int[][] users, int[] emoticons) {
        result = new int[2];
        N = users.length;
        M = emoticons.length;
        comb(0, new int[M], users, emoticons);
        return result;
    }

    static void comb(int cnt, int[] picks, int[][] users, int[] emoticons) {
        if (cnt == M) {
            calculate(picks, users, emoticons);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            picks[cnt] = i * 10;
            comb(cnt + 1, picks, users, emoticons);
        }
    }

    static void calculate(int[] picks, int[][] users, int[] emoticons) {
        int[] tmp = new int[2];

        for (int i = 0; i < N; i++) {
            int cutRate = users[i][0];
            int cutPrice = users[i][1];
            int tmpPrice = 0;
            for (int j = 0; j < M; j++) {
                if (picks[j] >= cutRate) {
                    tmpPrice += emoticons[j] * (100 - picks[j]) / 100;
                }
            }
            if (tmpPrice >= cutPrice) {
                tmp[0]++;
            } else {
                tmp[1] += tmpPrice;
            }
        }

        if (tmp[0] > result[0]) {
            result = tmp;
        } else if (tmp[0] == result[0] && tmp[1] > result[1]) {
            result = tmp;
        }
    }
}