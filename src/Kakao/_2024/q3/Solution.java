package Kakao._2024.q3;

import java.util.*;
class Solution {
    static int n, maxWin;
    static int[] resultPair;
    static int[] diceA, diceB;
    static List<Integer> sumA, sumB;

    public int[] solution(int[][] dice) {
        n = dice.length;
        diceA = new int[n / 2];
        diceB = new int[n / 2];
        resultPair = new int[n / 2];
        comb(0, 1, dice);
        return resultPair;
    }

    static void comb(int cnt, int start, int[][] origin) {
        if (cnt == n / 2) {
            /**
             * 1. 주사위 A 조합 구했으니 B 조합 나머지로 구해주기
             * 2. A, B 각각 여러개의 주사위를 하나의 총점 배열로 만들어주기
             * 3. 두 일차원 배열을 비교하면 승수를 구할 수 있음
             */
            findB();
            sumA = new ArrayList<>();
            sumB = new ArrayList<>();
            makeSumArr(0, 0, origin, diceA, sumA);
            makeSumArr(0, 0, origin, diceB, sumB);
            countWin();
            return;
        }

        for (int i = start; i <= n; i++) {
            diceA[cnt] = i;
            comb(cnt + 1, i + 1, origin);
        }
    }

    static void findB() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        for (int n : diceA) {
            list.remove((Integer) n);
        }
        for (int i = 0; i < list.size(); i++) {
            diceB[i] = list.get(i);
        }
    }

    static void makeSumArr(int cnt, int sum, int[][] origin, int[] diceX, List<Integer> sumX) {
        if (cnt == n / 2) {
            sumX.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            makeSumArr(cnt + 1, sum + origin[diceX[cnt] - 1][i], origin, diceX, sumX);
        }
    }

    static void countWin() {
        Collections.sort(sumA);
        Collections.sort(sumB);

        int result = 0;
        int beforeN = 0;
        int beforeCnt = 0;

        /**
        * Integer 끼리 == 연산하면 일정 상수 범위를 넘어갔을 때
        * 같은 상수값이어도 주소가 다르니 다르다고 판단.
        * Integer <-> int 까지는 상관없다.
        * -> 웬만하면 int 형으로 비교하자.
        */
        for(int num : sumA) {
            if (num == beforeN) {
                result += beforeCnt;
                continue;
            }
            beforeN = num;
            if (sumB.get(sumB.size()-1) < num) {
                result += sumB.size();
                beforeCnt = sumB.size();
                continue;
            }
            for (int i = 0; i < sumB.size(); i++) {
                if (sumB.get(i) == num || sumB.get(i) > num) {
                    result += i;
                    beforeCnt = i;
                    break;
                }
            }
        }
        if (result > maxWin) {
            maxWin = result;
            for (int i = 0; i < diceA.length; i++) {
                resultPair[i] = diceA[i];
            }
        }
    }
}
