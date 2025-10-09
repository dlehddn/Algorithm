package Kakao._2024.주사위고르기;

// start 8:37
// end 9:12
// -> 35m 소요

import java.util.*;
class Second {
    int n;
    int result;
    int[] answer;
    public int[] solution(int[][] dice) {
        n = dice.length;
        answer = new int[n/2];
        diceSelect(dice, 0, new int[n/2], 0);
        return answer;
    }

    private void setB(int[] pickB, int[] pickA) {
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            all.add(i);
        }
        for (int a: pickA) {
            all.remove((Integer) a);
        }
        for (int i = 0; i < n/2; i++) {
            pickB[i] = all.get(i);
        }
    }

    private void addAllScore(List<Integer> list, int[][] dice, int[] dicePicks, int[] locationPicks, int cnt) {
        if (cnt == dicePicks.length) {
            int sum = 0;
            for (int i = 0; i < dicePicks.length; i++) {
                sum += dice[dicePicks[i]][locationPicks[i]];
            }
            list.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            locationPicks[cnt] = i;
            addAllScore(list, dice, dicePicks, locationPicks, cnt + 1);
        }
    }

    private void deepCopy(int[] pickA) {
        for (int i = 0; i < n/2; i++) {
            answer[i] = pickA[i] + 1;
        }
    }

    private void diceSelect(int[][] dice, int cnt, int[] pickA, int start) {
        if (cnt == n/2) {
            int[] pickB = new int[n/2];
            setB(pickB, pickA);
            List<Integer> allScoreA = new ArrayList<>();
            List<Integer> allScoreB = new ArrayList<>();

            addAllScore(allScoreA, dice, pickA, new int[n/2], 0);
            addAllScore(allScoreB, dice, pickB, new int[n/2], 0);

            allScoreB.sort(Comparator.naturalOrder());

            int aWinCnt = getAWinCnt(allScoreA, allScoreB);
            if (aWinCnt > result) {
                result = aWinCnt;
                deepCopy(pickA);
            }
            return;
        }

        for (int i = start; i < n; i++) {
            pickA[cnt] = i;
            diceSelect(dice, cnt + 1, pickA, i + 1);
        }
    }

    private int getAWinCnt(List<Integer> allScoreA, List<Integer> allScoreB) {
        int aWinCnt = 0;
        for (int a: allScoreA) {
            int left = 0;
            int right = allScoreA.size() - 1;
            int max = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (a > allScoreB.get(mid)) {
                    max = Math.max(max, mid + 1);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            aWinCnt += max;
        }
        return aWinCnt;
    }
}
