package DFSBFS.백준16933번_벽부수고이동하기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, result;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        result = -1;
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0, 1));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();

            if (poll.y == N - 1 && poll.x == M - 1) {
                result = poll.move;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (map[nY][nX] == 0 && !visited[nY][nX][poll.wall]) {
                    visited[nY][nX][poll.wall] = true;
                    q.add(new Node(nY, nX, poll.move + 1, poll.wall, poll.sun * -1));
                } else if (map[nY][nX] == 1 && poll.wall + 1 <= K && !visited[nY][nX][poll.wall + 1] && poll.sun == 1) {
                    visited[nY][nX][poll.wall + 1] = true;
                    q.add(new Node(nY, nX, poll.move + 1, poll.wall + 1, -1));
                } else if (map[nY][nX] == 1 && poll.wall + 1 <= K && !visited[nY][nX][poll.wall + 1] && poll.sun == -1) {
                    q.add(new Node(poll.y, poll.x, poll.move + 1, poll.wall, 1));
                }
            }
        }
    }

    static class Node {
        int y, x;
        int move, wall;
        int sun;

        public Node(int y, int x, int move, int wall, int sun) {
            this.y = y;
            this.x = x;
            this.move = move;
            this.wall = wall;
            this.sun = sun;
        }
    }
}
