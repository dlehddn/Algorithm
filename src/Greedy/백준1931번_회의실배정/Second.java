package Greedy.백준1931번_회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Second {
    static int N;
    static PriorityQueue<Meeting> pq = new PriorityQueue<>((m1, m2) -> {
        if (m1.end != m2.end) {
            return m1.end - m2.end;
        } else {
            return m1.start - m2.start;
        }
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Meeting(s, e));
        }

        int lastEnd = 0;
        int cnt = 0;

        while (!pq.isEmpty()) {
            Meeting poll = pq.poll();
            if (poll.start < lastEnd) {
                continue;
            }
            cnt++;
            lastEnd = poll.end;
        }
        System.out.println(cnt);
    }

    static class Meeting {
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
