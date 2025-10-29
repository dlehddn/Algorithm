package Greedy.프로그래머스_요격시스템;

import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<Attack> pq  = new PriorityQueue<>(Comparator.comparingInt(a -> a.start));
        for (int[] target: targets) {
            pq.add(new Attack(target[0], target[1]));
        }

        int lastIdx = -1;
        int cnt = 0;
        while (!pq.isEmpty()) {
            Attack poll = pq.poll();
            if (poll.start >= lastIdx) {
                cnt++;
                lastIdx = poll.end;
            } else {
                lastIdx = Math.min(lastIdx, poll.end);
            }
        }
        return cnt;
    }

    static class Attack {
        int start, end;

        public Attack(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
