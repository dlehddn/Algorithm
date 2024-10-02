package Greedy.백준1911번_흙길보수하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {

    static int N, L, cnt, endPoint;
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
            pq.add(new Water(s, e - 1));
        }

        while (!pq.isEmpty()) {
            Water poll = pq.poll();
            if (poll.end <= endPoint) continue;

            if (endPoint < poll.start) {
                int len = poll.end - poll.start + 1;
                int tmp = len / L;
                if (len % L != 0) tmp++;
                cnt += tmp;
                endPoint = poll.start - 1 + tmp * L;
            } else {
                int len = poll.end - (endPoint + 1) + 1;
                int tmp = len / L;
                if (len % L != 0) tmp++;
                cnt += tmp;
                endPoint = endPoint + tmp * L;
            }
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
