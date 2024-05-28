package Dijkstra.백준1261번_알고스팟;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(dijkstra());


    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        boolean[][] visited = new boolean[N][M];
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (poll.y == N - 1 && poll.x == M - 1) {
                return poll.cost;
            }

            if (visited[poll.y][poll.x]) {
                continue;
            }
            visited[poll.y][poll.x] = true;

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (map[nY][nX] == 0) {
                    pq.add(new Node(nY, nX, poll.cost));
                } else if (map[nY][nX] == 1) {
                    pq.add(new Node(nY, nX, poll.cost + 1));
                }
            }
        }
        return 0;

    }

    static class Node {
        int y, x, cost;

        public Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
}
