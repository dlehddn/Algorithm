package Heap.프로그래머스_호텔대실;

import java.util.*;
class Solution {
    private static final int MINUTE_IN_DAY = 1440;
    private static final int NON_EXIST = -1;
    public int solution(String[][] book_time) {
        List<Integer>[] reservations = new List[MINUTE_IN_DAY];
        for (int i = 0; i < MINUTE_IN_DAY; i++) {
            reservations[i] = new ArrayList<>();
        }
        for (String[] book: book_time) {
            int start = timeToMinute(book[0]);
            int end = timeToMinute(book[1]);
            reservations[start].add(end);
        }
        PriorityQueue<Integer> inProcessing = new PriorityQueue<>();
        PriorityQueue<Integer> inCleaning = new PriorityQueue<>();

        int min = 1;
        for (int i = 0; i < MINUTE_IN_DAY; i++) {
            while (!inProcessing.isEmpty() && inProcessing.peek() == i) {
                inCleaning.add(inProcessing.poll() + 10);
            }
            while (!inCleaning.isEmpty() && inCleaning.peek() == i) {
                inCleaning.poll();
            }
            if (reservations[i].size() > 0) {
                for (int endTime: reservations[i]) {
                    inProcessing.add(endTime);
                }
            }
            min = Math.max(min, inProcessing.size() + inCleaning.size());
        }
        return min;
    }

    private int timeToMinute(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    static class Reservation {
        int start, end;

        public Reservation(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
