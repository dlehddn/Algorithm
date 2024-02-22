package Kakao._2023.q3;

import java.util.*;
class FirstSolve {
    static int[] result, pick;
    static int uN, eN;

    public int[] solution(int[][] users, int[] emoticons) {
        uN = users.length;
        eN = emoticons.length;
        result = new int[2];
        pick = new int[eN];
        pickPercent(0, users, emoticons);
        return result;
    }

    static void pickPercent(int cnt, int[][] users, int[] emoticons) {
        if(cnt == eN) {
            calculate(users, emoticons);
            return;
        }
        for(int i = 1; i <= 4; i++) {
            pick[cnt] = i * 10;
            pickPercent(cnt + 1, users, emoticons);
        }
    }

    static void calculate(int[][] users, int[] emoticons) {
        int[] tmp = new int[2];
        for(int i = 0; i < uN; i++) {
            int disCount = users[i][0];
            int price = users[i][1];
            int totalPrice = 0;
            for(int j = 0; j < eN; j++) {
                if(pick[j] >= disCount) {
                    totalPrice += emoticons[j] * (100 - pick[j]) / 100 ;
                }
            }
            if(totalPrice >= price) tmp[0] += 1;
            else tmp[1] += totalPrice;
        }
        if (tmp[0] > result[0]) {
            result[0] = tmp[0];
            result[1] = tmp[1];
        } else if (tmp[0] == result[0] && tmp[1] > result[1]) {
            result[0] = tmp[0];
            result[1] = tmp[1];
        }
    }
}