package DFSBFS.백준16928_뱀과사디리게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] ladders;
    static int[] snakes;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[101];
        ladders = new int[101];
        snakes = new int[101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladders[start] = end;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snakes[start] = end;
        }

        dijkstra();
    }

    static void dijkstra() {
        PriorityQueue<Movement> pq = new PriorityQueue<>(Comparator.comparingInt(m -> m.cnt));
        pq.add(new Movement(1, 0));

        while (!pq.isEmpty()) {
            Movement poll = pq.poll();
            if (visited[poll.position]) continue;
            visited[poll.position] = true;

            if (poll.position == 100) {
                System.out.println(poll.cnt);
                return;
            }

            if (ladders[poll.position] != 0) {
                pq.add(new Movement(ladders[poll.position], poll.cnt));
                continue;
            }

            if (snakes[poll.position] != 0) {
                pq.add(new Movement(snakes[poll.position], poll.cnt));
                continue;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPosition = poll.position + i;
                if (nextPosition > 100) continue;
                pq.add(new Movement(nextPosition, poll.cnt + 1));
            }
        }
    }

    static class Movement {
        int position, cnt;

        public Movement(int position, int cnt) {
            this.position = position;
            this.cnt = cnt;
        }
    }
}
