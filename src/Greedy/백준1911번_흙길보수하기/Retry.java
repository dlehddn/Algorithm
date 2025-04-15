package Greedy.백준1911번_흙길보수하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Retry {
    static int N, L;
    static PriorityQueue<Water> pq = new PriorityQueue<>(Comparator.comparingInt(w -> w.start));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Water(s, e));
        }

        int lastIdx = -1;
        int used = 0;
        while (!pq.isEmpty()) {
            Water poll = pq.poll();
            if (poll.end <= lastIdx) continue;
            int start = calStart(lastIdx, poll.start);
            int end = poll.end - 1;
            int targetAmount = end - start + 1;
            used += targetAmount / L;
            lastIdx = start + targetAmount / L * L - 1;
            if (targetAmount % L != 0) {
                used++;
                lastIdx += L;
            }
        }
        System.out.println(used);
    }

    static int calStart(int lastIdx, int pollStart) {
        if (lastIdx < pollStart) {
            return pollStart;
        }
        return lastIdx + 1;
    }

    static class Water {
        int start, end;

        public Water(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
