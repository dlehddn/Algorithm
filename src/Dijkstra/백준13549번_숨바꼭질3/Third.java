package Dijkstra.백준13549번_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Third {
    static final int MAX_SIZE = 100_001;
    static int N, K;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[MAX_SIZE];

        PriorityQueue<Movement> pq = new PriorityQueue<>(Comparator.comparingInt(m -> m.time));
        pq.add(new Movement(0, N));

        while (!pq.isEmpty()) {
            Movement poll = pq.poll();

            if (poll.location == K) {
                System.out.println(poll.time);
                return;
            }

            if (visited[poll.location]) continue;
            visited[poll.location] = true;

            if (poll.location > 0) {
                pq.add(new Movement(poll.time + 1, poll.location - 1));
            }
            if (poll.location < 100_000) {
                pq.add(new Movement(poll.time + 1, poll.location + 1));
            }
            if (poll.location * 2 <= 100_000) {
                pq.add(new Movement(poll.time, poll.location * 2));
            }
        }
    }

    static class Movement {
        int time, location;

        public Movement(int time, int location) {
            this.time = time;
            this.location = location;
        }
    }
}
