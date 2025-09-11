package Kakao._2023.이모티콘할인행사;

// start 03:22
// end 03:46
// -> 24m 소요

import java.util.*;

class Solution {
    private int[] result = new int[2];
    public int[] solution(int[][] users, int[] emoticons) {
        comb(emoticons.length, 0, new int[emoticons.length], users, emoticons);
        return result;
    }

    private void compare(int[] discounts, int[][] users, int[] emoticons) {
        int signupCnt = 0;
        int totalSales = 0;
        for(int[] user: users) {
            int salesPerUser = 0;
            for (int i = 0; i < discounts.length; i++) {
                if (discounts[i] >= user[0]) {
                    salesPerUser += emoticons[i] * (100 - discounts[i]) / 100;
                }
            }
            if (salesPerUser >= user[1]) {
                signupCnt++;
            } else {
                totalSales += salesPerUser;
            }
        }
        if (signupCnt > result[0]) {
            result[0] = signupCnt;
            result[1] = totalSales;
        } else if (signupCnt >= result[0]) {
            if (result[1] < totalSales) {
                result[1] = totalSales;
            }
        }
    }

    private void comb(int totalCnt, int cnt, int[] discounts, int[][] users, int[] emoticons) {
        if (cnt == totalCnt) {
            compare(discounts, users, emoticons);
            return;
        }

        for (int discount = 10; discount <= 40; discount += 10) {
            discounts[cnt] = discount;
            comb(totalCnt, cnt + 1, discounts, users, emoticons);
        }
    }
}
