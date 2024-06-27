package Greedy.백준1911번_흙길보수하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Water> pq;
    static int N, L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(Comparator.comparingInt(w -> w.start));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.add(new Water(start, end - 1));
        }

        int cnt = 0;
        int last = -1;
        while (!pq.isEmpty()) {
            Water poll = pq.poll();
            if (poll.end <= last) continue;
            int start = Math.max(poll.start, last + 1);
            int tmp = (poll.end - start + 1) / L;
            if ((poll.end - start + 1) % L != 0) tmp++;
            cnt += tmp;
            last = start + tmp * L - 1;
        }
        System.out.println(cnt);
    }

    static class Water {
        int start, end;

        public Water(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
