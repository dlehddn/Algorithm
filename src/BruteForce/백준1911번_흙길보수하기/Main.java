package BruteForce.백준1911번_흙길보수하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, L, idx, result;
    static PriorityQueue<Water> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.start));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        idx = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Water(s, e - 1));
        }

        while (!pq.isEmpty()) {
            Water poll = pq.poll();
            if (poll.start > idx) {
                int cnt = (poll.end - poll.start + 1) / L;
                if ((poll.end - poll.start + 1) % L != 0) cnt++;
                result += cnt;
                idx = poll.start + L * cnt - 1;
            } else if (poll.end > idx) {
                int cnt = (poll.end - (idx + 1) + 1) / L;
                if ((poll.end - (idx + 1) + 1) % L != 0) cnt++;
                result += cnt;
                idx = idx + 1 + L * cnt - 1;
            }
        }

        System.out.println(result);
    }

    static class Water {
        int start, end;

        public Water(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
