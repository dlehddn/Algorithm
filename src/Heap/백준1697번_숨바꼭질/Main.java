package Heap.백준1697번_숨바꼭질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.time));
        boolean[] visited = new boolean[100001];
        pq.add(new Position(N, 0));

        while (!pq.isEmpty()) {
            Position poll = pq.poll();

            if (poll.idx == K) {
                System.out.println(poll.time);
                return;
            }

            if (visited[poll.idx]) continue;
            visited[poll.idx] = true;

            if (poll.idx - 1 >= 0 && !visited[poll.idx - 1]) {
                pq.add(new Position(poll.idx - 1, poll.time + 1));
            }

            if (poll.idx + 1 <= 100000 && !visited[poll.idx + 1]) {
                pq.add(new Position(poll.idx + 1, poll.time + 1));
            }

            if (poll.idx * 2 <= 100000 && !visited[poll.idx * 2]) {
                pq.add(new Position(poll.idx * 2, poll.time + 1));
            }
        }

    }

    static class Position {
        int idx, time;

        public Position(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }
}
