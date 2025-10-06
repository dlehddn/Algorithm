package Kakao._2020.자물쇠와열쇠;

// start 4:14
// end 5:05
// -> 51m 소요

import java.util.*;
// 시간 복잡도 계산
// rotate() = 400
// move = 40 * 40 = 1,600
// 열쇠 맞추기 = 40 * 40 = 1,600
// total = 4번 rotate(4) * move(1,600) * 열쇠(1,600) ~= 1천만
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int size = lock.length;
        int[][] expandsKey = new int[size][size];
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                expandsKey[i][j] = key[i][j];
            }
        }
        for (int loop = 0; loop < 4; loop++) {
            for (int y = -1 * (size - 1); y <= size - 1; y++) {
                loop:
                for (int x = -1 * (size - 1); x <= size - 1; x++) {
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (lock[i][j] == 1) {
                                if (!outOfRange(i + y, j + x, size) && expandsKey[i + y][j + x] == 1) continue loop;
                            } else {
                                if (outOfRange(i + y, j + x, size) || expandsKey[i + y][j + x] == 0) continue loop;
                            }
                        }
                    }
                    return true;
                }
            }
            expandsKey = rotateKey(expandsKey, size);
        }
        return false;
    }

    private boolean outOfRange(int y, int x, int size) {
        if (y < 0 || x < 0 || y >= size || x >= size) {
            return true;
        }
        return false;
    }

    // 1 2 3    7 4 1
    // 4 5 6 -> 8 5 2
    // 7 8 9    9 6 3
    private int[][] rotateKey(int[][] key, int size) {
        int[][] rotated = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[j][size - 1 - i] = key[i][j];
            }
        }
        return rotated;
    }
}