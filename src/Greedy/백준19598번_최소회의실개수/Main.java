package Greedy.백준19598번_최소회의실개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static PriorityQueue<Meeting> waitingQ = new PriorityQueue<>(Comparator.comparingInt(m -> m.start));
    static PriorityQueue<Meeting> proceedingQ = new PriorityQueue<>(Comparator.comparingInt(m -> m.end));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            waitingQ.add(new Meeting(start, end));
        }

        int max = Integer.MIN_VALUE;
        while (!waitingQ.isEmpty()) {
            Meeting poll = waitingQ.poll();

            while (!proceedingQ.isEmpty() && proceedingQ.peek().end <= poll.start) {
                proceedingQ.poll();
            }

            proceedingQ.add(poll);
            max = Math.max(max, proceedingQ.size());
        }

        System.out.println(max);
    }

    static class Meeting {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
