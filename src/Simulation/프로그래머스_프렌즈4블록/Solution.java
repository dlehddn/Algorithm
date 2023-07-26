package Simulation.프로그래머스_프렌즈4블록;

import java.util.*;
class Solution {

    private static char[][] coordinate;
    private static int N, M, sum;

    public int solution(int m, int n, String[] board) {
        coordinate = new char[m][n];
        N = m;  // N은 가로길이
        M = n;  // M은 세로길이
        sum = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                coordinate[i][j] = board[i].charAt(j);
            }
        }
        // 캐릭터 배열로 변환!

        while(canNext()) {
            changeToLower();
            sumAndLowerToX();
            moveToDown();
        }
        return sum;
    }

    private static boolean canNext() {
        for(int i = 0; i < N - 1; i++) {
            for(int j = 0; j < M - 1; j++) {
                char who = coordinate[i][j];
                if(who != 'X' && coordinate[i][j+1] == who && coordinate[i+1][j] == who && coordinate[i+1][j+1] == who) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void changeToLower() {
        for(int i = 0; i < N - 1; i++) {
            for(int j = 0; j < M - 1; j++) {
                if(coordinate[i][j] == 'X') continue;
                char who = Character.toUpperCase(coordinate[i][j]);
                if(Character.toUpperCase(coordinate[i][j+1]) == who &&
                   Character.toUpperCase(coordinate[i+1][j]) == who &&
                   Character.toUpperCase(coordinate[i+1][j+1]) == who) {
                        coordinate[i][j] = Character.toLowerCase(coordinate[i][j]);
                        coordinate[i][j+1] = Character.toLowerCase(coordinate[i][j+1]);
                        coordinate[i+1][j] = Character.toLowerCase(coordinate[i+1][j]);
                        coordinate[i+1][j+1] = Character.toLowerCase(coordinate[i+1][j+1]);
                }
            }
        }
    }

    private static void sumAndLowerToX() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(Character.isLowerCase(coordinate[i][j])) {
                    sum++;
                    coordinate[i][j] = 'X';
                }
            }
        }
    }

    private static void moveToDown() {
        for(int i = 0; i < M; i++) {
            for(int j = N - 2; j >= 0; j--) {
                if(coordinate[j][i] != 'X') {
                    char temp = coordinate[j][i];
                    coordinate[j][i] = 'X';
                    int currentY = j;
                    while(currentY+1 <= N-1 && coordinate[currentY+1][i] == 'X') {
                        currentY++;
                    }
                    coordinate[currentY][i] = temp;
                }

            }
        }
    }

}