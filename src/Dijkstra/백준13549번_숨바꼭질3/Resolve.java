package Dijkstra.백준13549번_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Resolve {

    static int N, M; // 0 <= N, M <= 100,000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        boolean[] visited = new boolean[100001];
        pq.add(new Position(N, 0));

        while (!pq.isEmpty()) {
            Position poll = pq.poll();

            if (visited[poll.position]) {
                continue;
            }
            visited[poll.position] = true;
            if (poll.position == M) {
                System.out.println(poll.cost);
                return;
            }

            if (poll.position + 1 <= 100_000) {
                pq.add(new Position(poll.position + 1, poll.cost + 1));
            }
            if (poll.position * 2 <= 100_000) {
                pq.add(new Position(poll.position * 2, poll.cost));
            }
            if (poll.position - 1 >= 0) {
                pq.add(new Position(poll.position - 1, poll.cost + 1));
            }
        }
    }

    static class Position {
        int position, cost;

        public Position(int position, int cost) {
            this.position = position;
            this.cost = cost;
        }
    }
}
