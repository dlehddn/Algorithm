package Dijkstra.백준13549번_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Fourth {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Location> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.cost));
        boolean[] visited = new boolean[100001];

        pq.add(new Location(N, 0));
        while (!pq.isEmpty()) {
            Location poll = pq.poll();

            if (poll.x == K) {
                System.out.println(poll.cost);
                return;
            }
            if (visited[poll.x]) {
                continue;
            }
            visited[poll.x] = true;

            if (poll.x - 1 >= 0) {
                pq.add(new Location(poll.x - 1, poll.cost + 1));
            }
            if (poll.x + 1 <= 100_000) {
                pq.add(new Location(poll.x + 1, poll.cost + 1));
            }
            if (poll.x * 2 <= 100_000) {
                pq.add(new Location(poll.x * 2, poll.cost));
            }
        }
    }

    static class Location {
        int x, cost;

        public Location(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }
}
