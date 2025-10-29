package Greedy.프로그래머스_풍선터뜨리기;

import java.util.*;

class Solution {
    PriorityQueue<Number> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.num));
    boolean[] visited;
    final int INF = 1_000_000_001;

    public int solution(int[] a) {
        int answer = 0;
        visited = new boolean[a.length];
        for (int i = 1; i < a.length; i++) {
            pq.add(new Number(a[i], i));
        }
        int left = INF;
        Number right = pq.poll();

        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                right = new Number(INF, a.length);
            } else if (a[i] == right.num) {
                while (pq.peek().idx < i) {
                    pq.poll();
                }
                right = pq.poll();
            }
            int cnt = 0;
            if (left > a[i]) cnt++;
            if (right.num > a[i]) cnt++;

            if (cnt >= 1) {
                answer++;
            }
            left = Math.min(left, a[i]);
        }
        return answer;
    }

    static class Number {
        int num, idx;

        public Number(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

}
