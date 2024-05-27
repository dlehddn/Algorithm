package Dijkstra.백준4485번_녹색옷입은애가젤다지;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int loop = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                System.out.println(sb);
                return;
            }

            map = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem " + loop + ": " + dijkstra() + "\n");
            loop++;
        }


    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        boolean[][] visited = new boolean[N][N];
        pq.add(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (poll.y == N - 1 && poll.x == N - 1) return poll.cost;

            if (visited[poll.y][poll.x]) continue;
            visited[poll.y][poll.x] = true;

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];

                if (nY >= 0 && nX >= 0 && nY < N && nX < N && !visited[nY][nX]) {
                    pq.add(new Node(nY, nX, poll.cost + map[nY][nX]));
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
