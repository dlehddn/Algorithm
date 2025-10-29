package Comb_Per.프로그래머스_비밀코드해독;

import java.util.*;
class Solution {
    private int answer = 0;

    public int solution(int n, int[][] q, int[] ans) {
        comb(0, 1, n, new int[5], q, ans);
        return answer;
    }

    private void check(int[] selected, int[][] q, int[] ans) {
        Set<Integer> set = new HashSet<>();
        for (int s: selected) {
            set.add(s);
        }
        for (int i = 0; i < q.length; i++) {
            int cnt = 0;
            for (int num: q[i]) {
                if (set.contains(num)) {
                    cnt++;
                }
            }
            if (ans[i] != cnt) {
                return;
            }
        }
        answer++;
    }

    private void comb(int cnt, int start, int n, int[] selected, int[][] q, int[] ans) {
        if (cnt == 5) {
            check(selected, q, ans);
            return;
        }

        for (int i = start; i <= n; i++) {
            selected[cnt] = i;
            comb(cnt + 1, i + 1, n, selected, q, ans);
        }
    }
}
