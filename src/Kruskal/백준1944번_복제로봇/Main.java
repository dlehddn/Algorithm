package Kruskal.백준1944번_복제로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] visited;
    static PriorityQueue<Node> pq;
    static int startX, startY;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        pq = new PriorityQueue<>();
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S') {
                    startY = i;
                    startX = j;
                }
            }
        }
        bfs();

        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'K') {
                    if (visited[i][j] == Integer.MAX_VALUE) {
                        cnt++;
                    }
                    sum += visited[i][j];
                }
            }
        }
        if(cnt > 0) {
            System.out.println(-1);
        } else System.out.println(sum);

    }

    static void bfs() {
        pq.add(new Node(startY, startX, 0, -1, -1));
        visited[startY][startX] = 0;
        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (map[nY][nX] == '1') continue;
                if (map[nY][nX] == '0' && visited[nY][nX] > poll.move + 1) {
                    visited[nY][nX] = poll.move + 1;
                    pq.add(new Node(nY, nX, poll.move + 1, poll.y, poll.x));
                } else if (map[nY][nX] == 'K' && visited[nY][nX] > poll.move + 1) {
                    if(nY == poll.bY && nX == poll.bX) continue;
                    visited[nY][nX] = poll.move + 1;
                    pq.add(new Node(nY, nX, 0, poll.y, poll.x));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int y, x, move, bY, bX;


        public Node(int y, int x, int move, int bY, int bX) {
            this.y = y;
            this.x = x;
            this.move = move;
            this.bY = bY;
            this.bX = bX;
        }

        @Override
        public int compareTo(Node o) {
            return this.move - o.move;
        }
    }
}
