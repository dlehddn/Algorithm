package Kakao._2024.주사위고르기;

// start 4:03
// end 5:20

// 10C5 = 252
// 252 * 6^5 = 2,000,000
import java.util.*;

class Solution {
    int n;
    int max = Integer.MIN_VALUE;
    int[] answer;
    public int[] solution(int[][] dice) {
        n = dice.length;
        answer = new int[n/2];
        comb(0, n, 0, new ArrayList<>(), dice);
        return answer;
    }

    private void resultComb(int n, int cnt, List<Integer> picks,
                            List<Integer> tmp, List<Integer> results, int[][] dice) {
        if (cnt == n/2) {
            int sum = 0;
            for (int i = 0; i < n/2; i++) {
                sum += dice[picks.get(i)][tmp.get(i)];
            }
            results.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            tmp.add(i);
            resultComb(n, cnt + 1, picks, tmp, results, dice);
            tmp.remove(tmp.size() - 1);
        }
    }

    private void check(int n, List<Integer> diceA, List<Integer> diceB, int[][] dice) {
        int winCount = 0;
        List<Integer> resultA = new ArrayList<>();
        List<Integer> resultB = new ArrayList<>();
        resultComb(n, 0, diceA, new ArrayList<>(), resultA, dice);
        resultComb(n, 0, diceB, new ArrayList<>(), resultB, dice);
        resultA.sort(Comparator.naturalOrder());
        resultB.sort(Comparator.naturalOrder());
        for(int a: resultA) {
            int left = 0;
            int right = resultB.size() - 1;
            int tmp = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (resultB.get(mid) < a) {
                    left = mid + 1;
                    tmp = Math.max(tmp, mid + 1);
                } else {
                    right = mid - 1;
                }
            }
            winCount += tmp;
        }
        if (winCount > max) {
            max = winCount;
            for (int i = 0; i < n/2; i++) {
                answer[i] = diceA.get(i) + 1;
            }
        }
    }

    private void comb(int start, int n, int cnt, List<Integer> diceA, int[][] dice) {
        if (cnt == n/2) {
            List<Integer> diceB = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                diceB.add(i);
            }
            diceB.removeAll(diceA);
            check(n, diceA, diceB, dice);
            return;
        }

        for(int i = start; i < n; i++) {
            diceA.add(i);
            comb(i + 1, n, cnt + 1, diceA, dice);
            diceA.remove(diceA.size() - 1);
        }
    }
}
