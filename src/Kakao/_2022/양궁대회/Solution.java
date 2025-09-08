package Kakao._2022.양궁대회;

import java.util.*;

class Solution {
    static int max = Integer.MIN_VALUE;
    static int[] result = new int[11];

    public int[] solution(int n, int[] info) {
        shoot(new int[11], 0, n, 0, info);
        return max == Integer.MIN_VALUE ? new int[]{-1} : result;
    }

    static void compare(int[] info, int[] board) {
        int apeach = 0;
        int lion = 0;
        for (int i = 0; i <= 10; i++) {
            if (info[i] > board[i]) {
                apeach += 10 - i;
            } else if (info[i] < board[i]) {
                lion += 10 - i;
            } else if (info[i] != 0 && board[i] != 0 && info[i] == board[i]) {
                apeach += 10 - i;
            }
        }
        if (lion > apeach) {
            if (lion - apeach > max) {
                max = lion - apeach;
                copy(board);
            } else if (lion - apeach == max) {
                transfer(board);
            }
        }
    }

    static void transfer(int[] board) {
        for (int i = 10; i >= 0; i--) {
            if (result[i] > board[i]) {
                break;
            } else if (result[i] < board[i]) {
                copy(board);
                return;
            }
        }
    }

    static void copy(int[] board) {
        for (int i = 0; i <= 10; i++) {
            result[i] = board[i];
        }
    }

    static void shoot(int[] board, int cnt, int n, int seq, int[] info) {
        if (cnt == n) {
            compare(info, board);
            return;
        }

        for (int i = 10 - seq; i >= 0; i--) {
            if (info[10 - i] + 1 + cnt <= n) {
                board[10 - i] += info[10 - i] + 1;
                shoot(board, cnt + info[10 - i] + 1, n, seq + 1, info);
                board[10 - i] -= info[10 - i] + 1;
            } else if (i == 0) {
                board[10 - i] += n - cnt;
                shoot(board, n, n, seq + 1, info);
                board[10 - i] -= n - cnt;
            }
        }
    }
}