package Heap.프로그래머스_야근지수;

import java.util.*;

class Solution {
    PriorityQueue<Integer> workHours = new PriorityQueue<>(Comparator.reverseOrder());
    public long solution(int n, int[] works) {
        long answer = 0;
        for (int h: works) {
            workHours.add(h);
        }

        while (n > 0) {
            int h = workHours.poll();
            if (h == 0) return 0;
            workHours.add(h - 1);
            n--;
        }

        while (!workHours.isEmpty()) {
            answer += (long) Math.pow(workHours.poll(), 2);
        }
        return answer;
    }
}
