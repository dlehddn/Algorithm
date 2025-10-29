package DFSBFS.프로그래머스_혼자놀기의달인;

import java.util.*;

class Solution {
    private int max = 0;

    public int solution(int[] cards) {
        int answer = 0;
        for (int i = 0; i < cards.length; i++) {
            boolean[] visited = new boolean[cards.length];
            visited[i] = true;
            dfs(1, i, 1, 0, visited, cards);
        }
        return max;
    }

    private void dfs(int phase, int curIdx, int openCnt1, int openCnt2, boolean[] visited, int[] cards) {
        if (visited[cards[curIdx] - 1]) {
            if (phase == 1 && openCnt1 == cards.length) {
                return;
            } else if (phase == 1 && openCnt1 < cards.length) {
                for (int i = 0; i < cards.length; i++) {
                    if (!visited[i]) {
                        visited[i] = true;
                        dfs(2, i, openCnt1, 1, visited, cards);
                        // visited[i] = false;
                    }
                }
                return;
            } else {
                max = Math.max(max, openCnt1 * openCnt2);
                return;
            }
        }
        visited[cards[curIdx] - 1] = true;
        if (phase == 1) {
            dfs(phase, cards[curIdx] - 1, openCnt1 + 1, openCnt2, visited, cards);
        } else {
            dfs(phase, cards[curIdx] - 1, openCnt1, openCnt2 + 1, visited, cards);
        }
    }
}
